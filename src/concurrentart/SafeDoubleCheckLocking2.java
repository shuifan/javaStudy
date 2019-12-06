package concurrentart;

/**
 * @author fandong
 * @create 2018/7/10
 */
public class SafeDoubleCheckLocking2 {

    /**
     * 不在外部申明 而是 使用内部类的方式 目的在于 只有当 调用此内部类获取其中的静态域时才会出发对应的初始化
     * 如果放在外部类中，可能会提前初始化 达不到延迟初始化的目的
     */
    private static class InstanceHolder{
        public static Instance instance = new Instance();
    }

    public static Instance getInstance(){
        return InstanceHolder.instance;
    }
}
