package concurrent;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.xml.crypto.Data;

public class TestCallAble {

    private static Callable<String> testCallable = new Callable<String>() {
        @Override
        public String call() throws Exception {
            Date date = new Date();
            Thread.sleep(1000);
            return date.toString();
        }
    };

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        Future<String> f = exec.submit(testCallable);
        try {
            System.out.println(f.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        exec.shutdown();
    }

}
