package bingfabianchengshizhan;

import java.util.Random;
import java.util.concurrent.*;

public class BarrierTest {


    public static void main(String[] args){
        Worker[] workers = new Worker[5];
        ExecutorService executorService = new ThreadPoolExecutor(5,
                10,
                0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(30),
                new ThreadFactory() {
                    int i = 0;
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName("my-thread" + i);
                        i++;
                        return thread;
                    }
                });
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("barrier is open");
                for (Worker worker : workers){
                    executorService.execute(worker);
                }
            }
        });

        for (int i = 0; i < 5; i++) {
            workers[i] = new Worker(cyclicBarrier);
        }
        for (Worker worker : workers){
            executorService.execute(worker);
        }
    }



    public static class Worker implements Runnable{

        private final CyclicBarrier cyclicBarrier;
        private final Random random;

        Worker(CyclicBarrier cyclicBarrier){
            this.cyclicBarrier = cyclicBarrier;
            this.random = new Random();
        }

        @Override
        public void run() {
            try {
                int i = random.nextInt(10) * 1000;
                System.out.println(Thread.currentThread().getName() + " 睡 " + i + "s");
                Thread.sleep(i);
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
