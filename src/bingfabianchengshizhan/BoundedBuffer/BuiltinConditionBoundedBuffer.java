package bingfabianchengshizhan.BoundedBuffer;

/**
 * @author fandong
 * @create 2018/6/26
 */
public class BuiltinConditionBoundedBuffer<T> extends BaseBoundedBuffer<T> {

    public BuiltinConditionBoundedBuffer(int capacity) {
        super(capacity);
    }

    public synchronized void put(T t) throws InterruptedException {
        while (isFull()){
            wait();
        }
        doPut(t);
        notifyAll();
    }

    public synchronized T take() throws InterruptedException {
        while (isEmpty()){
            wait();
        }
        T t = doTake();
        notifyAll();
        return t;
    }

    public static void main(String[] args) throws InterruptedException {
        BuiltinConditionBoundedBuffer<String> conditionBoundedBuffer = new BuiltinConditionBoundedBuffer<>(16);
        conditionBoundedBuffer.put("qa");
        conditionBoundedBuffer.put("qa");
        System.out.println(conditionBoundedBuffer.take());
        System.out.println(conditionBoundedBuffer.take());
    }
}
