package concurrentart;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author fandong
 * @create 2018/7/19
 */
public class JiOuTest {

    private boolean flag =true;
    private Integer number = 1;

    public static void main(String[] args){
        JiOuTest jiOuTest = new JiOuTest();
        new Thread(new Ji(jiOuTest)).start();
        new Thread(new Ou(jiOuTest)).start();
    }

    static class Ji implements Runnable{

        private final JiOuTest jiOuTest;

        public Ji(JiOuTest jiOuTest) {
            this.jiOuTest = jiOuTest;
        }

        @Override
        public void run() {
            while (true){
                synchronized (jiOuTest){
                    if (jiOuTest.number >= 100){
                        break;
                    }
                    if (jiOuTest.flag){
                        System.out.println(jiOuTest.number);
                        jiOuTest.number++;
                        jiOuTest.notify();
                    }else {
                        try {
                            jiOuTest.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    static class Ou implements Runnable{

        private final JiOuTest jiOuTest;

        public Ou(JiOuTest jiOuTest) {
            this.jiOuTest = jiOuTest;
        }

        @Override
        public void run() {
            while (true){
                synchronized (jiOuTest){
                    if (jiOuTest.number >= 100){
                        break;
                    }
                    if (!jiOuTest.flag){
                        System.out.println(jiOuTest.number);
                        jiOuTest.number++;
                        jiOuTest.notify();
                    }else {
                        try {
                            jiOuTest.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
