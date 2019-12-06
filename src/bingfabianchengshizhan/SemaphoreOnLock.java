package bingfabianchengshizhan;

/**
 * @author fandong
 * @create 2018/6/26
 */
public class SemaphoreOnLock {

    private int permits;
    private final int totalPermits;

    public SemaphoreOnLock(int permits) {
        this.permits = permits;
        totalPermits = permits;
    }

    public synchronized void acqure() throws InterruptedException {
        while (permits <= 0){
            wait();
        }
        permits--;
    }

    public synchronized void release(){
        if (permits >= totalPermits){
            System.out.println("规避无效的 release");
            return;
        }
        permits++;
        notifyAll();
    }

    public static void main(String[] args) throws InterruptedException {
//        SemaphoreOnLock semaphoreOnLock = new SemaphoreOnLock(5);
//        semaphoreOnLock.release();
//        semaphoreOnLock.acqure();
//        semaphoreOnLock.acqure();
//        semaphoreOnLock.acqure();
//        semaphoreOnLock.acqure();
//        semaphoreOnLock.acqure();
//        semaphoreOnLock.acqure();
//        semaphoreOnLock.release();
        System.out.println(".1234".startsWith("."));

    }
}
