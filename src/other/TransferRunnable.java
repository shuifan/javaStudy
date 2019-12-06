//import sun.java2d.loops.GraphicsPrimitive;
//
//import java.io.FileInputStream;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ConcurrentLinkedDeque;
//import java.util.concurrent.ConcurrentSkipListMap;
//import java.util.concurrent.ConcurrentSkipListSet;
//
///**
// * @author fandong
// * @create 2018/3/16
// */
//public class TransferRunnable implements Runnable {
//
//    private Bank bank;
//
//    public TransferRunnable() {
//    }
//
//    public TransferRunnable(Bank bank) {
//        this.bank = bank;
//    }
//
//    @Override
//    public void run() {
//
//        FileInputStream
//
//        try {
//            while (true){
//                bank.transfer( 3, 2, 1.234);
//                Thread.sleep( (int)(10 * Math.random()));
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public static void main(String[] args){
//        Bank bank = new Bank(4, 4232);
//        for (int i = 0; i < 100; i++){
//            TransferRunnable transferRunnable = new TransferRunnable(bank);
//            Thread thread = new Thread(transferRunnable);
//            thread.start();
//        }
//    }
//}
