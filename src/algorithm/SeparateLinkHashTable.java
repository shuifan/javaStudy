package algorithm;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;


/**
 * 用分离链接法 解决散列冲突的 散列表
 * @param <T>
 */
@Data
public class SeparateLinkHashTable<T> {

    private List<T>[] dataArray;
    private int currentSize;
    /**
     * 选择默认大小 各有各的考量
     * 选择 素数 可能比非素数有更好的性能
     * 选择 2的n次幂  则是为了方便 在定位到具体数组位置时 可以用 hashCode & (2^n - 1)
     */
    private static final int DEFAULT_SIZE = 10;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    public SeparateLinkHashTable() {
        this(DEFAULT_SIZE);
    }

    @SuppressWarnings("all")
    public SeparateLinkHashTable(int capacity){
        dataArray = new LinkedList[Prime.nextPrime(capacity)];
    }

    private int myHash(T object){
        int h;
        int hash = object == null ? 0 : (h = object.hashCode()) ^ h >>> 16;
        int index = hash % dataArray.length;
        if (index < 0 ){
            index =  index + dataArray.length;
        }
        return index;
    }

    public void insert(T t){
        int index = myHash(t);
        List<T> tList = dataArray[index];
        if (tList == null){
            dataArray[index] = tList = new LinkedList<>();
            tList.add(t);
            currentSize++;
        }else {
            for (T t1 : tList){
                if (t1.equals(t)){
                    return;
                }
            }
            tList.add(t);
            currentSize++;
        }
        if (currentSize > dataArray.length * DEFAULT_LOAD_FACTOR){
            reHash();
        }
    }

    public void remove(T t){
        List<T> tList = dataArray[myHash(t)];
        boolean remove = tList.remove(t);
        if (remove){
            currentSize--;
        }
    }


    public boolean contains(T t){
        List<T> list = dataArray[myHash(t)];
        if (list == null){
            return false;
        }else {
            for (T t1 : list){
                if (t1.equals(t)){
                    return true;
                }
            }
            return false;
        }
    }

    @SuppressWarnings("all")
    private void reHash(){
        List<T>[] oldData = this.dataArray;
        dataArray = new LinkedList[Prime.nextPrime(oldData.length * 2)];
        currentSize = 0;
        for (List<T> list : oldData){
            if (list != null){
                for (T t : list){
                    insert(t);
                }
            }
        }
    }

    public static void main(String[] args){
        long l = System.nanoTime();
        SeparateLinkHashTable<String> stringSeparateLinkHashTable = new SeparateLinkHashTable<>(2);
        for (int i = 0; i < 220000; i++) {
            stringSeparateLinkHashTable.insert(String.valueOf(i));
        }
        System.out.println(System.nanoTime() - l);
    }

}
