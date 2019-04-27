package concurrent;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProducerConsumer {

    private static String LOCK = "lock";
    private Stack<String> stack = new Stack<>(10);
    private String context;
    private volatile boolean stop = false;
    private String protocol;
    private Pattern pattern = Pattern.compile("(http:|https:)?//[^\\s]*?(jpg|png|gif|JPEG|JPG|PNG)");
    private Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public static void main(String[] args) {
//        if (args.length == 0) {
//            System.out.println("Usage:\n" +
//                    "java ProducerConsumer http://example.url");
//        }else{
//            ProducerConsumer producerCosumer = new ProducerConsumer();
//            producerCosumer.crawler(args[0]);
//        }

        ProducerConsumer producerConsumer = new ProducerConsumer();
        for (int i = 1; i <= 11; i++) {
            producerConsumer.crawler("http://www.haopic.me/meinv/page/" + i);
        }

    }

    public void crawler(String url){
        protocol = url.substring(0, url.indexOf(":"));
        HttpURLConnection connection = tryConnect(url, 16);
        try {
            context = read(connection);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("context:" + context);
        new Thread(new Producer()).start();

        for (int i = 0; i < 32; i++) {
            new Thread(new Consumer()).start();
        }
    }

    private String read(HttpURLConnection connection) throws IOException {
        BufferedReader in = null;
            in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()
                    )
            );
        StringBuilder webContent = new StringBuilder();
        String line = null;
        while ((line = in.readLine()) != null) {
            webContent.append(line);
        }
        return webContent.toString();
    }

    public HttpURLConnection tryConnect(String url, int tryTimes) {
        int tryCnt = 0;
        HttpURLConnection connection = null;
        URL target = null;
        try {
            target = new URL(url);
        } catch (MalformedURLException e) {
            System.out.println("url格式错误" + url);
            return null;
        }
        while (true) {
            try {
                connection = (HttpURLConnection) target.openConnection();
                break;
            }  catch (IOException e) {
                if (tryCnt < tryTimes) {
                    System.out.println("连接失败，进行第" + tryCnt + "次尝试");
                    continue;
                } else {
                    System.out.println("多次尝试失败，请检查你的网络");
                    break;
                }
            }
        }
        return  connection;
    }

    private  static class Stack<T>{
        private  Object[] elements;
        private int top = -1;

        public Stack(int cap) {
            elements = new Object[cap];
        }


        public synchronized T  push(T e) {
            if (top  + 1 >= elements.length) {
                throw new IndexOutOfBoundsException();
            }
            elements[++top] = e;
            return e;
        }

        public synchronized T pop() {
            if (top < 0) {
                throw new IndexOutOfBoundsException();
            }
            Object e = elements[top];
            elements[top --] = null;
            return (T) e;
        }

        public synchronized boolean empty() {
            return top == -1;
        }

        public synchronized int size() {
            return top + 1;
        }

        public synchronized boolean full() {
            return top == elements.length  -1 ;
        }

    }

    /**
     * waite和notify实现生产者消费者模型
     */
    class Producer implements Runnable {


        @Override
        public void run() {
//            Pattern pattern = Pattern.compile("<img.*?src=\"(.*?)\"[ >]");
//            (http|https)?://[^\s]*?(jpg|png)
            Matcher matcher = pattern.matcher(context);
            while (matcher.find()) {
                while (stack.full()) {
                    synchronized (LOCK) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println("producer stack size :" + stack.size());
                String tmp = matcher.group(0);
                System.out.println("url:" + tmp);
                if (tmp.startsWith("/")) {
                    stack.push(protocol + ":" + tmp);
                }else{
                    stack.push( tmp);
                }
                synchronized (LOCK) {
                    LOCK.notifyAll();
                }
            }
                stop = true;
            synchronized (LOCK) {
                LOCK.notifyAll();
            }
        }

    }

    class Consumer implements Runnable {

        @Override
        public void run() {
            while (! stop || ! stack.empty()) {
                String url = null;
                synchronized (LOCK) {
                    while (stack.empty()) {
                        if (stop) {
                            break;
                        }
                        System.out.println("consumer stack size :" + stack.size());
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (stack.empty() && stop) {
                        break;
                    }
                    url = stack.pop();
                    LOCK.notifyAll();
                }
                HttpURLConnection connection = tryConnect(url, 16);
                if (connection == null) {
                    continue;
                }
                File file = new File("src/tmp/"
                        + LocalDateTime.now()
                        + url.substring(url.lastIndexOf("."), url.length())
                );
                if (! file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                FileOutputStream out = null;
                System.out.println("begin to download " + url);
                try {
                     out = new FileOutputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                byte[] buffer = new byte[2048];
                try {
                    InputStream in = connection.getInputStream();
                    try {
                        int index = -1;
                        while ( (index = in.read(buffer) ) != -1) {
                            out.write(buffer,0 , index);
                            out.flush();
                        }
                    }finally {
                        in.close();
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class LockProducer implements Runnable {

        @Override
        public void run() {
            Matcher matcher = pattern.matcher(context);
            while (matcher.find()) {
                String tmp = matcher.group(0);
                System.out.println("LockProducer url:" +tmp  );
                if (tmp.startsWith("/")) {
                    stack.push(protocol + ":" + tmp);
                }
            }
        }
    }

    class LockConsumer implements  Runnable{

        @Override
        public void run() {

        }
    }

}
