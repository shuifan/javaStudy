package bingfabianchengshizhan.cachecompute;

import java.util.concurrent.*;

public class CacheCompute<T, V> implements Compute<T, V> {

    private final ConcurrentHashMap<T, Future<V>> valueCache = new ConcurrentHashMap<>(16);

    private final Compute<T, V> compute;

    CacheCompute(Compute<T, V> compute){
        this.compute = compute;
    }

    @Override
    public V compute(T a) throws ExecutionException, InterruptedException {


        Future<V> vFuture = valueCache.get(a);
        if (vFuture == null){
            FutureTask<V> future = new FutureTask<V>(new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return compute.compute(a);
                }
            });
            Future<V> lastFuture = valueCache.putIfAbsent(a, future);
            if (lastFuture == null){
                //并没有提交给某个线程 或者是 线程池
                //需要手动启动计算
                future.run();
                return future.get();
            }else {
                lastFuture.get();
            }
        }
        return null;
    }
}
