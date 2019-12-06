package JVM;

import java.util.List;

/**
 * @author fandong
 * @create 2018/8/1
 */
public class ClInitTest {

    public static class Parent{
        public static Integer a = 1;
        static {
            a = 2;
            System.out.println("parent init");
        }
    }

    public static class Sub extends Parent{
        public static Integer b = a;
    }

    private int a;
    private Integer b;

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public static void main(String[] args){
//        System.out.println(Sub.b);
        ClInitTest clInitTest = new ClInitTest();
        System.out.println(clInitTest.getA());
        System.out.println(clInitTest.getB());
    }
}
