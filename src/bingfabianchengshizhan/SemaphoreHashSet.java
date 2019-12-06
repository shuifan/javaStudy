package bingfabianchengshizhan;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author fandong
 */
public class SemaphoreHashSet<T> {

    private final Set<T> hashSet;
    private final Semaphore semaphore;

    SemaphoreHashSet(int initCount){
        hashSet = Collections.synchronizedSet(new HashSet<>());
        semaphore = new Semaphore(initCount);
    }

    public boolean add(T t) throws InterruptedException {
        System.out.println("准备获取");
        semaphore.acquire();
        System.out.println("获取成功");
        boolean wasAdded = false;
        try {
            wasAdded = hashSet.add(t);
            return wasAdded;
        }finally {
            if (!wasAdded){
                semaphore.release();
            }
        }
    }

    public boolean remove(T t){
        boolean remove = hashSet.remove(t);
        if (remove){
            semaphore.release();
        }
        return remove;
    }

    public static void main(String[] args) throws InterruptedException {
//        SemaphoreHashSet<String> semaphoreHashSet = new SemaphoreHashSet<>(4);
//        for (int i = 0; i < 6; i++) {
//            System.out.println(semaphoreHashSet.add(String.valueOf(i)));
//        }
        String regex = "^[\\u4e00-\\u9fa5]*$";
        Matcher m = Pattern.compile(regex).matcher("ddd测试");
        System.out.println(m.matches());
    }
}
