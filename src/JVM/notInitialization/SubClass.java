package JVM.notInitialization;

/**
 * @author fandong
 * @create 2018/7/31
 */
public class SubClass extends SuperClass{

    static {
        System.out.println("sub class");
    }
}
