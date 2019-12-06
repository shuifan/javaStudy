package bingfabianchengshizhan.filesearch;

import java.util.concurrent.BlockingQueue;

/**
 * @author fandong
 * @create 2018/6/22
 */
public class FileReader implements Runnable{

    private final BlockingQueue<String> fileBlockingQueue;

    public FileReader(BlockingQueue<String> fileBlockingQueue) {
        this.fileBlockingQueue = fileBlockingQueue;
    }

    @Override
    public void run() {
        while (true){
            try {
                System.out.println(Thread.currentThread().getName() + "  " + System.currentTimeMillis() + " " + fileBlockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
