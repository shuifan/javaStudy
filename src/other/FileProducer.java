package other;

import java.io.File;
import java.io.FileFilter;
import java.util.Objects;
import java.util.concurrent.BlockingDeque;

/**
 * @author fandong
 * @create 2018/6/7
 */
public class FileProducer implements Runnable{

    private final BlockingDeque<String> fileQueue;
    private final FileFilter fileFilter;
    private final File root;

    public FileProducer(BlockingDeque<String> fileQueue, FileFilter fileFilter, File root) {
        this.fileQueue = fileQueue;
        this.fileFilter = fileFilter;
        this.root = root;
    }

    @Override
    public void run() {
        try {
            produce(root);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void produce(File root) throws InterruptedException {
        File[] files = root.listFiles();
        if (Objects.nonNull(files)){
            for (File file : files){
                if (file.isDirectory()){
                    produce(file);
                }else if (fileFilter.accept(file)){
                    fileQueue.put(file.getName());
                }
            }
        }
    }
}
