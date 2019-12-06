package other;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * @author fandong
 * @create 2018/6/8
 */
public class SemaphoreHashSet<T> {

    private final Semaphore semaphore;
    private final Set<T> set;

    public SemaphoreHashSet(int count) {
        semaphore = new Semaphore(count);
        set = Collections.synchronizedSet(new HashSet<>());
    }

    public boolean add(T t) throws InterruptedException {

        semaphore.acquire();
        System.out.println("获取许可成功");
        boolean isAdded = false;
        try {
            boolean add = set.add(t);
            isAdded = true;
        }catch (Exception ignored){
        }
        if (!isAdded){
            System.out.println("释放许可");
            semaphore.release();
        }
        return isAdded;
    }

    public boolean remove(T t){
        boolean remove = set.remove(t);
        if (remove){
            System.out.println("释放许可");
            semaphore.release();
        }
        return remove;
    }

    public static void main(String[] args) throws InterruptedException {
        SemaphoreHashSet<String> semaphoreHashSet = new SemaphoreHashSet<>(5);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    semaphoreHashSet.add(finalI + "");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        Thread.sleep(3000);

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                    semaphoreHashSet.remove(finalI + "");
            }).start();
        }

        Thread.sleep(3000);
    }
}
