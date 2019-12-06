package concurrentart;

/**
 * @author fandong
 * @create 2018/7/10
 */
public class SafeDoubleCheckLocking1 {

    private volatile static Instance instance;

    public static Instance getInstance() {
        if (instance == null){
            synchronized (SafeDoubleCheckLocking1.class){
                if (instance == null){
                    instance = new Instance();
                }
            }
        }
        return instance;
    }

}
