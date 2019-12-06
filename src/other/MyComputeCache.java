package other;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * @author fandong
 * @create 2018/6/8
 */
public class MyComputeCache<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> map = new ConcurrentHashMap<>();
    private final Computable<A, V> computable;

    public MyComputeCache(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(A a) throws InterruptedException {
        while (true){
            Future<V> f = map.get(a);
            if (Objects.isNull(f)){

                FutureTask<V> futureTask = new FutureTask<V>(new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        return computable.compute(a);
                    }
                });
                Future<V> v = map.putIfAbsent(a, futureTask);
                if (Objects.isNull(v)){
                    System.out.println("实际计算");
                    f = futureTask;
                    futureTask.run();
                }
            }

            try {
                return f.get();
            } catch (Exception e) {
                map.remove(a);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyComputeCache<Double, Double> myComputeCache = new MyComputeCache<>(new Computable<Double, Double>() {
            @Override
            public Double compute(Double aDouble) throws InterruptedException {
                return Math.sqrt(aDouble);
            }
        });
        myComputeCache.compute(2.32);
        myComputeCache.compute(2.32);

    }

}
