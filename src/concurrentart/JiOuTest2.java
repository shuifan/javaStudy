package concurrentart;

import java.util.concurrent.Exchanger;

/**
 * @author fandong
 * @create 2018/7/25
 */
public class JiOuTest2 {

    public static void main(String[] args){
        Exchanger<Integer> exchanger = new Exchanger<>();
        new Thread(new Ji(exchanger)).start();
        new Thread(new Ou(exchanger)).start();

    }

    static class Ji implements Runnable{

        private Exchanger<Integer> exchanger;

        public Ji(Exchanger<Integer> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            int i = 1;
            while (true){
                if (i > 100){
                    break;
                }
                System.out.println(i);
                try {
                    exchanger.exchange(++i);
                    ++i;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Ou implements Runnable{

        private Exchanger<Integer> exchanger;

        public Ou(Exchanger<Integer> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            while (true){
                try {
                    Integer exchange = exchanger.exchange(0);
                    if (exchange > 100){
                        break;
                    }
                    System.out.println(exchange);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
