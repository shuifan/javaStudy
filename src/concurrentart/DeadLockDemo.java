package concurrentart;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author fandong
 * @create 2018/7/3
 */
public class DeadLockDemo {

    private final String A = "A";
    private final String B = "B";

    public static void main(String[] args){
//        new DeadLockDemo().deadLock();
//        String a = "2018-06-22 15:13:11";
//        System.out.println(a.length());
//        System.out.println(null + "");
//        String[] a = new String[10];
//        String[] b = new String[10];
//        System.out.println("ZY010101100451".substring(4, 14));
        String a = "{\"description\":\"zhh创高级医生\",\"job_title\":\"副主任\",\"team_role\":null,\"address\":null}";
        System.out.println(a.replace("null", "\"\""));
    }

    private void deadLock(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B){
                        System.out.println("1");
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (A){
                        System.out.println("1");
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }
}
