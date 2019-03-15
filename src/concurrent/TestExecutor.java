package concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestExecutor {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0 ;  i < 100 ; i ++){
            final  int tmp = i;
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    int name = tmp;
                    int cnt = 0;
                    while (cnt < 100){
                        System.out.println("thread " + name + " cnt " + cnt);
                        cnt ++;
                    }
                }
            });
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        exec.shutdown();
    }
}
