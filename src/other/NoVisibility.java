package other;

/**
 * @author fandong
 * @create 2018/6/4
 */
public class NoVisibility {

    private static boolean ready;
    private static int a;

    private static class ReadThread extends Thread{
        @Override
        public void run() {
            while (!ready){
                Thread.yield();
            }
            if (a != 42){
                System.out.println(a);
            }

        }
    }

    public static void main(String[] args){

        for (int i = 0; i < 2000000; i++) {
            new ReadThread().start();
            a = 42;
            ready = true;
        }
    }
}
