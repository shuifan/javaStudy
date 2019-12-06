package JVM;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fandong
 * @create 2018/7/26
 */
public class JConsoleTest {

    static class OOMObject{
        public byte[] a = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Thread.sleep(50);
            list.add(new OOMObject());
        }
//        System.gc();
    }

    public static void createBusyThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                }
            }
        }, "busyThread").start();
    }

    public static void createWaitThread(final Object o){
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o){
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "waitThread").start();
    }

    public static void createDeadLock(Object a, Object b){
        for (int i = 0; i < 100; i++) {
            new Thread(new DoubleLock(a, b), i + "").start();
            new Thread(new DoubleLock(b, a), i + "").start();
        }
    }

    static class DoubleLock implements Runnable{
        private final Object a;
        private final Object b;

        DoubleLock(Object a, Object b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            synchronized (a){
                synchronized (b){
                    System.out.println(1);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(3000);
        createBusyThread();
        createWaitThread(new Object());
        createDeadLock(new Object(), new Object());
        Thread.sleep(3000);
    }
}
