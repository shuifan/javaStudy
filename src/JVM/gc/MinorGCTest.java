package JVM.gc;

/**
 * @author fandong
 * @create 2018/7/25
 */
public class MinorGCTest {

    public static final int _1M = 1024 * 1024;

    public static void testGC(){
        byte[] allocation1,allocation2,allocation3,allocation4,allocation5;
        allocation1 = new byte[2 * _1M];
        allocation2 = new byte[2 * _1M];
        allocation3 = new byte[2 * _1M];
        allocation4 = new byte[2 * _1M];
    }

    public static void testPretenureSizeThreshold(){
        byte[] a;
        a = new byte[4 * _1M];
    }

    public static void testTenuringThreshold(){
        byte[] allocation1,allocation2,allocation3;
        allocation1 = new byte[_1M / 4];
        allocation2 = new byte[4 * _1M];
        allocation3 = new byte[4 * _1M];
        allocation3 = null;
        allocation3 = new byte[4 * _1M];
    }

    public static void testTenuringThreshold2(){
        byte[] allocation1,allocation2,allocation3,allocation4;
        allocation1 = new byte[_1M / 4];
        allocation2 = new byte[_1M / 4];
        allocation3 = new byte[4 * _1M];
        allocation4 = new byte[4 * _1M];
        allocation4 = null;
        allocation4 = new byte[4 * _1M];
    }

    public static void main(String[] args){
//        testGC();
//        testPretenureSizeThreshold();
        testTenuringThreshold2();
    }
}
