package concurrent;

import java.util.regex.Matcher;

public class SimpleProducerConsumer {

    private static final int FULL = 1000;
    private static final int EMPTY = 0;
    private static Integer size = EMPTY;

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
            synchronized (size) {
//#IMP 19-04-27 永远都在循环中使用wait,因为某种原因，wait和能在条件不满足的情况下被唤醒
//                wait 只能在同步块中使用
                while (isEmpty()) {
                    try {
                        size.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.interrupted();
                    }
                }
                size++;
                System.out.println("consumer " + Thread.currentThread().getName() + ":" + size);
                size.notifyAll();
            }
        }
    }

    static class WaiteProducer implements Runnable {


        @Override
        public void run() {
            synchronized (size) {
                while (isFull()) {
                    try {
                        size.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.interrupted();
                    }
                }
                size--;
                System.out.println("producer " + Thread.currentThread().getName() + ":" + size);
                size.notifyAll();
            }
        }

    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new WaiteConsumer()).start();
            new Thread(new WaiteProducer()).start();
        }
    }
}
