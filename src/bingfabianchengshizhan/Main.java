package bingfabianchengshizhan;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fandong
 * @create 2018/6/29
 */
public class Main {

    public static void main(String[] args){
        CountDownLatch countDownLatch = new CountDownLatch(12);
        Lock lock = new ReentrantLock();
        Semaphore semaphore = new Semaphore(10);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
    }
}
