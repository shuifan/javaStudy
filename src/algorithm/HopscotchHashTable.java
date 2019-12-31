package algorithm;

import lombok.Data;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 跳房子散列
 * 线性开放定址的扩展，规定一个MAX_DIST hash处的值可以存放在 hash 到 hash + MAX_DIST -1 的位置上
 * 由此得到最坏情况下的查找时间为 O(MAX_DIST)
 * @param <T>
 */
@Data
public class HopscotchHashTable<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final double DEFAULT_LOAD_FACTOR = 0.8;
    private static final int MAX_DIST = 5;

    private int currentSize;
    private Node<T>[] dataArray;

    @SuppressWarnings("all")
    public HopscotchHashTable() {
        dataArray = new Node[Prime.nextPrime(DEFAULT_CAPACITY)];
    }

    @Data
    private class Node<T>{
        private T data;
        //这个字段记录 当前位置 到 当前位置 + MAX_DIST - 1
        // 范围内hash值为当前位置值的 相对位置
        private int hop;

        public Node() {
        }

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, int hop) {
            this.data = data;
            this.hop = hop;
        }
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
        if (currentSize > dataArray.length * DEFAULT_LOAD_FACTOR){
            rehash();
        }

        int tHashIndex = myHash(t);
        for (int i = 0; i < MAX_DIST; i++) {
            int currentIndex = tHashIndex + i;
            if (currentIndex < dataArray.length){
                Node<T> tNode = dataArray[currentIndex];
                if (tNode == null){
                    dataArray[currentIndex] = new Node<>(t);
                    /**
                     * 找到位置后 既要完善新插入元素的hop
                     * 也要 完善 hash位置的元素的hop
                     * 
                     * 插入一个元素其实影响了两个位置的 hop  插入位置 和 元素 hash值位置
                     *
                     */
                    perfectHop(currentIndex);
                    perfectHop(tHashIndex);
                    currentSize++;
                    return;
                }
            }
        }
        int firstEmptyIndex = MAX_DIST;
        while (dataArray[firstEmptyIndex] != null){
            firstEmptyIndex++;
        }
        int maxDistMinus1 = MAX_DIST - 1;
        while ((firstEmptyIndex - tHashIndex) > maxDistMinus1){
            int startIndex = firstEmptyIndex - maxDistMinus1;
            for (int i = 0; i < maxDistMinus1; i++) {
                int startPlusI = startIndex + i;
                Node<T> tNode = dataArray[startPlusI];
                if (tNode != null &&tNode.getHop() > 0){
                    List<Integer> integers = binaryToList(tNode.getHop());
                    int replaceIndex = integers.get(0) + startPlusI;
                    dataArray[firstEmptyIndex] = new Node<>(dataArray[replaceIndex].getData());
                    perfectHop(firstEmptyIndex);
                    dataArray[replaceIndex] = null;
                    firstEmptyIndex = replaceIndex;
                    break;
                }
            }
            /**
             * 执行到此处 说明前面可到达范围内的值均无法往后再移动，此时需要再hash
             */
            rehash();
        }
        dataArray[firstEmptyIndex] = new Node<>(t);
        perfectHop(firstEmptyIndex);
    }

    private void rehash(){
        Node<T>[] oldArray = this.dataArray;
        dataArray = new Node[Prime.nextPrime(oldArray.length * 2)];
        currentSize = 0;
        for (Node<T> tNode : oldArray){
            if (tNode != null){
                insert(tNode.data);
            }
        }
    }


    private void perfectHop(int index){
        int hop = 0;
        for (int i = 0; i < MAX_DIST; i++) {
            int indexPlusI = index + i;
            if (indexPlusI < dataArray.length){
                Node<T> tNode = dataArray[indexPlusI];
                if (tNode != null){
                    System.out.println(myHash(tNode.data));
                    System.out.println(index);
                    if (myHash(tNode.data) == index){
                        hop = setBinaryIndexValue(hop, i, 1);
                    }
                }
            }
        }
        dataArray[index].setHop(hop);
    }

    private int findPos(T t){
        int index = myHash(t);
        Node<T> tNode = dataArray[index];
        if (tNode != null){
            if (tNode.data.equals(t)){
                return index;
            }
            List<Integer> binaryToList = binaryToList(tNode.getHop());
            for (Integer i : binaryToList){
                int cur = i + index;
                if (dataArray[cur].data.equals(t)){
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

    public static void main(String[] args){
        Random random = new Random();
        List<String> strings = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            strings.add(String.valueOf(random.nextInt()));
        }
        HopscotchHashTable<String> hopscotchHashTable = new HopscotchHashTable<>();
        for (String s : strings){
            hopscotchHashTable.insert(s);
        }
        for (String s : strings){
            System.out.println(hopscotchHashTable.contains(s));;
        }
    }

}
