package JVM;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fandong
 * @create 2018/7/23
 */
public class HeapOOM {

    static class OOMObject{

    }

    static int i = 1;

    public static void main(String[] args){
        List<OOMObject> oomObjects = new ArrayList<>();
        int i = 0;
        try {
            while (true){
                oomObjects.add(new OOMObject());
                i++;
            }
        }catch (OutOfMemoryError outOfMemoryError){
            System.out.println(i);
            throw outOfMemoryError;
        }

    }
}
