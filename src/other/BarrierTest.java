package other;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author fandong
 * @create 2018/6/8
 */
public class BarrierTest {

    private final CyclicBarrier cyclicBarrier;
    private final int cpuCount;
    public BarrierTest() {
        cpuCount = Runtime.getRuntime().availableProcessors();
        this.cyclicBarrier = new CyclicBarrier(cpuCount, new Runnable() {
            @Override
            public void run() {
                System.out.println("thr barrier is open");
                doWork();
            }
        });

    }

    private void doWork(){
        for (int i = 0; i < cpuCount; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("开始执行线程任务");
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public static void main(String[] args){
        BarrierTest barrierTest = new BarrierTest();
        barrierTest.doWork();
    }

}
