package concurrentart;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fandong
 * @create 2018/7/3
 */
public class ConcurrencyTest {

    public static final long COUNT = 10000;

    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
//        Lock lock = new ReentrantLock();
//        Thread thread = Thread.currentThread();
//        thread.join();
//        Map<String, String> map = new ConcurrentHashMap<>();
    }

    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (int i = 0; i < COUNT; i++) {
                    a++;
                }
            }
        });
        thread.start();
        int b = 0;
        for (int i = 0; i < COUNT; i++) {
            b--;
        }
        thread.join();
        long time = System.currentTimeMillis() - start;
        System.out.println("concurrency time: " + time + " ms");
    }

    private static void serial(){
        long start = System.currentTimeMillis();
        int a = 0;
        for (int i = 0; i < COUNT; i++) {
            a++;
        }
        int b = 0;
        for (int i = 0; i < COUNT; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial time: " + time + " ms");
    }
}
