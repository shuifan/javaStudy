package JVM.notInitialization;

/**
 * @author fandong
 * @create 2018/7/31
 */
public class NotInitialization {

    public static void main(String... args){

        /**
         * 通过子类引用父类的 static 变量 只会导致 父类的初始化
         */
//        System.out.println(SubClass.a);

        /**
         * T[] 不会导致 T的初始化
         */
//        SubClass[] subClasses = new SubClass[10];

        /**
         *  常量 在 编译期 会 存入 调用的类的 常量池中,不会导致定义常量的类的初始化
         */

        try {
            System.out.println(ConstantClass.A);
        }catch (Exception e){
            System.out.println("1");
        }finally {
            System.out.println("2");
        }
    }
}
