package other;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author fandong
 * @create 2018/6/7
 */
public class FileConsumer implements Runnable{

    private final BlockingDeque<String> fileQueue;

    public FileConsumer(BlockingDeque<String> fileQueue) {
        this.fileQueue = fileQueue;
    }

    @Override
    public void run() {
        while (true){
            try {
                System.out.println("阻塞前");
                System.out.println( Thread.currentThread().getName() + "  " + fileQueue.take());
                System.out.println("阻塞后");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args){
        BlockingDeque<String> fileQueue = new LinkedBlockingDeque<>(20);
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().contains("微服务");
            }
        };

        for (int i = 0; i < 5; i++) {
            new Thread(new FileProducer(fileQueue, fileFilter, new File("G:\\"))).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(new FileConsumer(fileQueue)).start();
        }

    }
}
