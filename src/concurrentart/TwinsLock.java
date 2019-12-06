package concurrentart;

import java.time.LocalDate;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author fandong
 * @create 2018/7/13
 */
public class TwinsLock {

    private final Sync sync = new Sync(2);

    public TwinsLock() throws IllegalAccessException {
    }

    private class Sync extends AbstractQueuedSynchronizer{
        public Sync(int count) throws IllegalAccessException {
            if (count <= 0){
                throw new IllegalAccessException("参数不可小于等于0");
            }
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int arg) {
                int state = getState();
                int newState = state - arg;
                if (newState < 0 || compareAndSetState(state, newState)){
                    return newState;
                }else {
                    return -1;
                }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            for (;;){
                int state = getState();
                int newState = state + arg;
                if (compareAndSetState(state, newState)){
                    return true;
                }
            }
        }
    }

    public void lock(){
        sync.acquireShared(1);
    }

    public void unLock(){
        sync.releaseShared(1);
    }

    public static void main(String... args) throws IllegalAccessException {
        TwinsLock twinsLock = new TwinsLock();
        Lock lock = new ReentrantLock();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    twinsLock.lock();
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        twinsLock.unLock();
                    }
                }
            }).start();
        }

    }
}
