package bingfabianchengshizhan.filesearch;

import java.io.File;
import java.io.FileFilter;
import java.util.Objects;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author fandong
 * @create 2018/6/22
 */
public class FileIndexer implements Runnable{

    private final BlockingQueue<String> fileBlockingQueue;
    private final File root;
    private final FileFilter fileFilter;

    public FileIndexer(BlockingQueue<String> fileBlockingQueue, File root, FileFilter fileFilter) {
        this.fileBlockingQueue = fileBlockingQueue;
        this.root = root;
        this.fileFilter = fileFilter;
    }

    @Override
    public void run() {
        try {
            search(root);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void search(File root) throws InterruptedException {
        File[] files = root.listFiles();
        if (Objects.nonNull(files)){
            for (File file : files){
                if (file.isDirectory()){
                    search(file);
                }else {
                    if (fileFilter.accept(file)){
                        fileBlockingQueue.put(file.getAbsolutePath());
                    }
                }
            }
        }
    }
}
