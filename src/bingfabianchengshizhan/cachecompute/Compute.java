package bingfabianchengshizhan.cachecompute;

import java.util.concurrent.ExecutionException;

public interface Compute<T, V> {
    /**
     * 计算方法 入参为 A类型 返回 V类型
     * @param a
     * @return
     */
    V compute(T a) throws ExecutionException, InterruptedException;
}
