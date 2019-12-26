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

    private List<T>[] data;
    private int currentSize;
    /**
     * 选择默认大小 各有各的考量
     * 选择 素数 可能比非素数有更好的性能
     * 选择 2的n次幂  则是为了方便 在定位到具体数组位置时 可以用 hashCode & (2^n - 1)
     */
    private static final int DEFAULT_SIZE = 100;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    public SeparateLinkHashTable() {
        this(DEFAULT_SIZE);
    }

    @SuppressWarnings("all")
    public SeparateLinkHashTable(int capacity){
        data = new LinkedList[Prime.nextPrime(capacity)];
    }

    private int myHash(T object){
        int h;
        return object == null ? 0 : (h = object.hashCode()) ^ h >>> 16;
    }

    public void insert(T t){
        int hash = myHash(t);
        int index = hash % data.length;
        List<T> tList = data[index];
        if (tList == null){
            data[index] = tList = new LinkedList<>();
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
        if (currentSize > data.length * DEFAULT_LOAD_FACTOR){
            reHash();
        }
    }

    public void remove(T t){
        int hash = myHash(t);
        int index = hash % data.length;
        List<T> tList = data[index];
        boolean remove = tList.remove(t);
        if (remove){
            currentSize--;
        }
    }


    public boolean contains(T t){
        int hash = myHash(t);
        List<T> list = data[hash % data.length];
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
        List<T>[] oldData = this.data;
        data = new LinkedList[Prime.nextPrime(oldData.length * 2)];
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
        SeparateLinkHashTable<String> stringSeparateLinkHashTable = new SeparateLinkHashTable<>(2);
        for (int i = 0; i < 22; i++) {
            stringSeparateLinkHashTable.insert(String.valueOf(i));
        }
    }

}
