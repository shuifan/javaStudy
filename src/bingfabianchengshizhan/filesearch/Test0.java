package bingfabianchengshizhan.filesearch;

import java.io.File;
import java.io.FileFilter;

/**
 * @author fandong
 * @create 2018/6/22
 */
public class Test0 {

    public static void main(String[] args){
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.getName().contains("111")){
                    return true;
                }
                return false;
            }
        };
        File file = new File("H:\\111.exe");
        System.out.println(fileFilter.accept(file));
    }
}
