package concurrentart;


/**
 * @author fandong
 * @create 2018/7/11
 */
public class TenJoin {

    public static void main(String[] args){
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new TheThread(previous), String.valueOf(i));
            thread.start();
            previous = thread;
        }
        System.out.println(Thread.currentThread().getName());
    }

    private static class TheThread implements Runnable{

        private final Thread thread;

        public TheThread(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "结束！");
        }
    }
}
