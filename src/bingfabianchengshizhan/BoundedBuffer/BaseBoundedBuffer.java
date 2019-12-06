package bingfabianchengshizhan.BoundedBuffer;

/**
 * @author fandong
 * @create 2018/6/26
 */
public abstract class BaseBoundedBuffer<T> {

    private final T[] buf;
    private int head;
    private int tail;
    private int count;

    public BaseBoundedBuffer(int capacity) {
        this.buf = (T[]) new Object[capacity];
    }

    public synchronized final void doPut(T t){
        buf[tail] = t;
        ++tail;
        if (tail == buf.length){
            tail = 0;
        }
        ++count;
    }

    public synchronized final T doTake(){
        T t = buf[head];
        buf[head] = null;
        ++head;
        if (head == buf.length){
            head = 0;
        }
        --count;
        return t;
    }

    public synchronized final boolean isFull(){
        return count == buf.length;
    }

    public synchronized final boolean isEmpty(){
        return count == 0;
    }
}
