package JVM.notInitialization;

/**
 * @author fandong
 * @create 2018/7/31
 */
public class SuperClass {

    static {
        System.out.println("super class");
    }

    public static int a = 0;
}
