package bingfabianchengshizhan;

import java.util.concurrent.*;

/**
 * @author fandong
 * @create 2018/6/22
 */
public class LatchTest {

    public static long timeTasks(int nThreads) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        ThreadFactory nameThreadFactory = new ThreadFactory() {
            int number = 0;
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("my-thread-" + number);
                number++;
                return thread;
            }
        };
        ExecutorService executorService = new ThreadPoolExecutor(5,
                10,
                0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(200),
//                new SynchronousQueue<>(),
                nameThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < nThreads; i++) {
            executorService.execute(new SomeTask(startGate, endGate));
        }

        long startTime = System.currentTimeMillis();
        startGate.countDown();
        endGate.await();
        long endTime = System.currentTimeMillis();
        long timeSecond = (endTime - startTime) / 1000;
        return timeSecond;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(timeTasks(10));
    }

    static class SomeTask implements Runnable{

        final CountDownLatch startGate;
        final CountDownLatch endGate;

        public SomeTask(CountDownLatch startGate, CountDownLatch endGate) {
            this.startGate = startGate;
            this.endGate = endGate;
        }

        @Override
        public void run() {
            try {
                startGate.await();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                endGate.countDown();
            }
            System.out.println(Thread.currentThread().getName() + " 完成任务");

        }
    }


}
