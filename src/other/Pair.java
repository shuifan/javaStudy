package other;

import java.io.Serializable;

/**
 * @author fandong
 * @create 2018/4/2
 */
public class Pair<T> extends SerialCloneable{
    private T first;
    private T second;

    public Pair() {
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    public static <T extends Comparable&Serializable> T getSomething(T... a){
        return a[0];
    }
}
