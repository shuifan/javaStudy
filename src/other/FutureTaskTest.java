package other;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author fandong
 * @create 2018/6/8
 */
public class FutureTaskTest {

    private final FutureTask<String> futureTask = new FutureTask<>(() -> "test");
    private final Thread thread = new Thread(futureTask);

    public void run(){
        thread.start();
    }

    public String get(){
        try {
            return futureTask.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args){
        FutureTaskTest futureTaskTest = new FutureTaskTest();
        futureTaskTest.run();
        System.out.println(futureTaskTest.get());
    }

}
