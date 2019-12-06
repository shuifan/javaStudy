package algorithm;

import java.io.File;

/**
 * 先序  后序  打印文件目录
 * 先序后序的区别在于
 * 先序先处理当前节点，然后处理子节点
 * 后序先处理子节点，后处理当前节点 最先处理的为叶子节点，最后处理的为根节点
 */
public class PrintFileSystem {

    public static void first(String path, int i){
        File file = new File(path);
        for (int j = 0; j < i; j++){
            System.out.print("    ");
        }
        System.out.println(file.getName());
        if (file.isDirectory()){
            File[] files = file.listFiles();
            if (files != null){
                int k = i + 1;
                for (File f : files){
                    first(f.getAbsolutePath(), k);
                }
            }

        }
    }

    public static void last(String path, int i){
        File file = new File(path);
        if (file.isDirectory()){
            File[] files = file.listFiles();
            if (files != null){
                int k = i + 1;
                for (File f : files){
                    last(f.getAbsolutePath(), k);
                }
            }

        }
        for (int j = 0; j < i; j++){
            System.out.print("    ");
        }
        System.out.println(file.getName());
    }

    public static void main(String[] args){
        first("F:\\apache-maven-3.5.0", 0);
        System.out.println();
        last("F:\\apache-maven-3.5.0", 0);
    }
}
