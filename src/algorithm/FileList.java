package algorithm;

import java.io.File;
import java.util.Queue;

/**
 * @author fandong
 * @create 2019/11/13
 *
 * 一级一级给出给定文件夹下所有的文件列表
 * 使用队列
 */
public class FileList {

    public static void printFileList(String name){
        File file = new File(name);
        if (file.isFile()){
            System.out.println(file.getName());
            return;
        }
        File[] files = file.listFiles();
        LinkedQueue<File> fileLinkedQueue = new LinkedQueue<>();
        fileLinkedQueue.enqueue(files);
        File theFile;
        while (!fileLinkedQueue.isEmpty()){
            theFile = fileLinkedQueue.dequeue();
            System.out.println(theFile.getAbsolutePath());
            if (theFile.isDirectory()){
                fileLinkedQueue.enqueue(theFile.listFiles());
            }
        }
    }

    public static void main(String[] args){
        printFileList("H:\\meeting");
    }
}
