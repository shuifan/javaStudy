package other;

/**
 * @author fandong
 * @create 2018/6/8
 */
public interface Computable<A, V> {
    V compute(A a) throws InterruptedException;
}
