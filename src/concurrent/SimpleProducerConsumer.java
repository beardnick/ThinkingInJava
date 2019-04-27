package concurrent;

import java.util.regex.Matcher;

public class SimpleProducerConsumer {

    private static final int FULL = 1000;
    private static final int EMPTY = 0;
    private static volatile Integer size = EMPTY;
    private static String lock = "lock";

    public static boolean isFull() {
        return size.equals(FULL);
    }

    public static boolean isEmpty() {
        return size.equals(EMPTY);
    }

    public Integer getSize() {
        return size;
    }

    static class WaiteConsumer implements Runnable {

        @Override
        public void run() {
//#IMP 19-04-27 这里使用第三变量String作为锁是因为这个String一直没有被修改过
//，如果使用的是size（Integer）每次++都会生成新的Integer实例，与锁住的size不是同一对象，就会造成java.lang.IllegalMonitorStateException
            synchronized (lock) {
//#IMP 19-04-27 永远都在循环中使用wait,因为某种原因，wait和能在条件不满足的情况下被唤醒
//                wait 只能在同步块中使用
                while (isEmpty()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.interrupted();
                    }
                }
                size--;
                System.out.println("consumer " + Thread.currentThread().getName() + ":" + size);
                lock.notifyAll();
            }
        }
    }

    static class WaiteProducer implements Runnable {


        @Override
        public void run() {
            synchronized (lock) {
                while (isFull()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.interrupted();
                    }
                }
                size++;
                System.out.println("producer " + Thread.currentThread().getName() + ":" + size);
                lock.notifyAll();
            }
        }

    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new WaiteProducer()).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(new WaiteConsumer()).start();
        }

    }
}
