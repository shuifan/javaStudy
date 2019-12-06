package bingfabianchengshizhan.filesearch;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.*;

/**
 * @author fandong
 * @create 2018/6/22
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        ThreadFactory nameThreadFactory = new ThreadFactory() {

            private int number = 1;

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("my-thread" + number);
                number++;
                return thread;
            }
        };

        int cpuCounts = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = new ThreadPoolExecutor(cpuCounts,
                cpuCounts * 2,
                0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1000),
                nameThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());

        BlockingQueue<String> fileBlockingQueue = new ArrayBlockingQueue<String>(100);
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.getName().contains("并发")){
                    return true;
                }
                return false;

            }
        };
        File root = new File("G:\\");
        File[] files = root.listFiles();
        System.out.println("start " + System.currentTimeMillis());
        for (File f : root.listFiles()){
            executorService.execute(new FileIndexer(fileBlockingQueue, root, fileFilter));
            executorService.execute(new FileReader(fileBlockingQueue));
            executorService.execute(new FileReader(fileBlockingQueue));
        }

        executorService.shutdown();
        executorService.awaitTermination(100, TimeUnit.SECONDS);
        System.exit(0);
    }
}
