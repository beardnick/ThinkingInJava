package concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
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
            for (int i = 0; i < 100; i++) {
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
                    displayState("consumer ");
                    lock.notifyAll();
                }
            }
        }
    }

    private static void displayState(String s) {
        System.out.println(s + Thread.currentThread().getName() + ":" + size);
    }

    static class WaiteProducer implements Runnable {


        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
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
                    displayState("producer ");
                    lock.notifyAll();
                }
            }
        }

    }


    /**
     * 使用ReentrantLock实现的生产者消费者模型
     */
    private static Lock reentrantLock = new ReentrantLock();
    private static Condition notFull = reentrantLock.newCondition();
    private static Condition notEmpty = reentrantLock.newCondition();

    static class ReentrantConsumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                reentrantLock.lock();
                try {
                    while (isEmpty()) {
                        try {
                            notEmpty.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    size--;
                    displayState("ReentrantConsumer");
                    notFull.signalAll();
//#NOTE 19-04-28 放在finally中保证始终会释放锁
                } finally {
                    reentrantLock.unlock();
                }
            }
        }
    }

    static class ReentrantProducer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                reentrantLock.lock();
                try {
                    while (isFull()) {
                        try {
                            notFull.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    size++;
                    displayState("ReentrantProducer");
                    notEmpty.signalAll();
                } finally {
                    reentrantLock.unlock();
                }
            }
        }
    }






    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new ReentrantProducer()).start();
        }
        for (int i = 0; i < 100; i++) {
            new Thread(new ReentrantConsumer()).start();
        }

    }

}
