package algorithm;

import lombok.Data;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Data
public class HopScotchHashTable<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final double DEFAULT_LOAD_FACTOR = 0.8;
    private static final int MAX_DIST = 5;

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
        throw new IllegalArgumentException();
    }

}
