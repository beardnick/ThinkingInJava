package test;

public class TestVolatile {

    public  static volatile int race = 0;

    public static void increase() {
        race ++;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        increase();
                    }
                }
            }).start();
        }
//        System.out.println("thread:" + Thread.activeCount());
//        activeCount返回估计值
//        注意按照机器上的实际值调整一下
        while (Thread.activeCount() > 2) {
//            System.out.println("count:" + Thread.activeCount());
            Thread.yield();
        }
//        每次输出的结果都不一样
        System.out.println("race:" + race);
    }
}
