/**
 * @author fandong
 * @create 2019/7/25
 */
public class Singleton {

    /**
     * 构造方法私有
     */
    private Singleton(){
    }

    private static class SingletonHolder{
        private static Singleton singleton = new Singleton();
    }

    public static Singleton getInstance(){
        return SingletonHolder.singleton;
    }

}
