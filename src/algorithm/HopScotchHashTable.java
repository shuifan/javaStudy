package algorithm;

import lombok.Data;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 跳房子散列
 * 线性开放定址的扩展，规定一个MAX_DIST hash处的值可以存放在 hash 到 hash + MAX_DIST -1 的位置上
 * 由此得到最坏情况下的查找时间为 O(MAX_DIST)
 * @param <T>
 */
@Data
public class HopScotchHashTable<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final double DEFAULT_LOAD_FACTOR = 0.8;
    private static final int MAX_DIST = 5;

    private int currentSize;
    private Node<T>[] dataArray;

    @SuppressWarnings("all")
    public HopScotchHashTable() {
        dataArray = (Node<T>[])new Object[Prime.nextPrime(DEFAULT_CAPACITY)];
    }

    @Data
    private class Node<T>{
        private T data;
        //这个字段记录 当前位置 到 当前位置 + MAX_DIST - 1
        // 范围内hash值为当前位置值的 相对位置
        private int hop;
    }

    public boolean contains(T t){
        return findPos(t) != -1;
    }

    public void remove(T t){
        int pos = findPos(t);
        if (pos > 0){
            dataArray[pos] = null;
            int index = myHash(t);
            //偏移存储的情况下需要维护 原有位置的 hop值
            if (index != pos){
                dataArray[index].setHop(setBinaryIndexValue(dataArray[index].getHop(), pos - index, 0));
            }
            currentSize--;
        }
    }

    public void insert(T t){
        if (contains(t)){
            return;
        }
        if (currentSize > DEFAULT_CAPACITY * DEFAULT_LOAD_FACTOR){
            rehash();
        }

        int index = myHash(t);
        for (int i = 0; i < MAX_DIST; i++) {
            int currentIndex = index + i;
            Node<T> tNode = dataArray[currentIndex];
            if (tNode == null){

            }
        }
    }

    private void rehash(){
        Node<T>[] oldArray = this.dataArray;
        dataArray = (Node<T>[])new Object[Prime.nextPrime(oldArray.length * 2)];
        for (Node<T> tNode : oldArray){
            if (tNode != null){
                insert(tNode.data);
            }
        }
    }


    private int getHop(int hash){
        int hop = 0;
        for (int i = 0; i < MAX_DIST; i++) {
            Node<T> tNode = dataArray[hash + i];
            if (tNode != null){
                if (myHash(tNode.data) == hash){
                    hop = setBinaryIndexValue(hop, i, 1);
                }
            }
        }
        return hop;
    }

    private int findPos(T t){
        int index = myHash(t);
        Node<T> tNode = dataArray[index];
        if (tNode != null){
            List<Integer> binaryToList = binaryToList(tNode.getHop());
            for (Integer i : binaryToList){
                int cur = i + index;
                if (dataArray[cur].equals(t)){
                    return cur;
                }
            }
        }
        return -1;
    }

    private int myHash(T t){
        int h;
        int hash = t == null ? 0 : (h = t.hashCode()) ^ (h >>> 16);
        hash = hash % dataArray.length;
        if (hash < 0){
            hash = hash + dataArray.length;
        }
        return hash;
    }


    /**
     * 找到二进制位置上为 1 的索引
     * 比如 10101 返回  0 2 4
     * @param hop
     * @return
     */
    private List<Integer> binaryToList(int hop){
        if (hop == 0){
            return Collections.emptyList();
        }
        List<Integer> indexList = new LinkedList<>();
        int i = 0;
        while (hop > 0){
            int mod = hop % 2;
            if (mod > 0){
                indexList.add(i);
            }
            hop = hop / 2;
            i++;
        }
        return indexList;
    }

    private int setBinaryIndexValue(int hop, int index, int value){
        int pow = (int) Math.pow(2, index);
        List<Integer> binaryToList = binaryToList(hop);
        boolean contains = binaryToList.contains(index);
        if (value == 0 && contains){
            return hop - pow;
        }else if (value == 1 && !contains){
            return hop + pow;
        }
        return hop;
    }

}
