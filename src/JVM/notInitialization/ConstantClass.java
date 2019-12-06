package JVM.notInitialization;

/**
 * @author fandong
 * @create 2018/7/31
 */
public class ConstantClass {

    static {
        System.out.println("constant class");
    }

    public static final String A = "1234";
}
