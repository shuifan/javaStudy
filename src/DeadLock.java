import java.util.concurrent.locks.Lock;

/**
 * @author fandong
 * @create 2019/10/24
 */
public class DeadLock {

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    private static Thread thread0 = new Thread(new Runnable() {
        @Override
        public void run() {
            synchronized (lock1){
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2){
                    System.out.println("get lock2");
                }
            }
        }
    });

    private static Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {
            synchronized (lock2){
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1){
                    System.out.println("get lock1");
                }
            }
        }
    });

    public static void main(String[] args){
        thread0.start();
        thread1.start();
    }
}
