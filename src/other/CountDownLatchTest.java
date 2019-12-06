package other;

import java.util.concurrent.CountDownLatch;

/**
 * @author fandong
 * @create 2018/6/8
 */
public class CountDownLatchTest {


    public long timeTask(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        startGate.await();
                        task.run();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        endGate.countDown();
                    }
                }
            }).start();
        }
        long startTime = System.currentTimeMillis();
        startGate.countDown();
        endGate.await();
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatchTest countDownLatchTest = new CountDownLatchTest();
        long l = countDownLatchTest.timeTask(30, () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println((l / 1000) + "秒");
    }
}
