package bingfabianchengshizhan.BoundedBuffer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fandong
 * @create 2018/6/26
 */
public class ConditionBoundedBuffer<T>{

    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();
    private final T[] items;
    private int head, tail, count;

    public ConditionBoundedBuffer(int capacity) {
        this.items = (T[]) new Object[capacity];
    }

    public void put(T t) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length){
                notFull.await();
            }
            items[tail] = t;
            tail++;
            if (tail == items.length){
                tail = 0;
            }
            count++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0){
                notEmpty.await();
            }
            T item = items[head];
            head++;
            if (head == items.length){
                head = 0;
            }
            count--;
            notFull.signal();
            return item;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionBoundedBuffer<String> conditionBoundedBuffer = new ConditionBoundedBuffer<>(16);
        conditionBoundedBuffer.put("a");
        conditionBoundedBuffer.put("a");
        System.out.println(conditionBoundedBuffer.take());
        System.out.println(conditionBoundedBuffer.take());
        System.out.println(conditionBoundedBuffer.take());
    }
}
