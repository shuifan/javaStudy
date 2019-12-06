//import java.text.SimpleDateFormat;
//import java.util.SimpleTimeZone;
//import java.util.concurrent.locks.ReentrantLock;
//
///**
// * @author fandong
// * @create 2018/3/16
// */
//public class Bank {
//
//    private double[] accounts;
//
//    private ReentrantLock reentrantLock;
//
//    private static final ThreadLocal<SimpleDateFormat> dateFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
//
//    public Bank() {
//
//        reentrantLock.lock();
//        try {
//            methodWithLock();
//        }finally {
//            reentrantLock.unlock();
//        }
//    }
//
//    public Bank(int counts, double initalBalance) {
//
//        accounts = new double[counts];
//        for (int i = 0; i < counts; i++){
//            accounts[i] = initalBalance;
//        }
//
//    }
//
//    synchronized public void transfer(int to, int from, double amount){
//        if (accounts[from] < amount){
//            System.out.println("账户中没有这么多钱咯");
//            return;
//        }
//        System.out.println(Thread.currentThread());
//        accounts[from] -= amount;
//        accounts[to] += amount;
//        System.out.printf("Total balance:%10.2f" , getTotal());
//        System.out.println();
//    }
//
//    public double getTotal(){
//        double sum = 0;
//        for (double a : accounts){
//            sum += a;
//        }
//        return sum;
//    }
//}
