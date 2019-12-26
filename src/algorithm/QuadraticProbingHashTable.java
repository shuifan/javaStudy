package algorithm;

import lombok.Data;

/**
 * 用 平方探测法 解决散列冲突的 散列表
 * 探测法： 线性 平方 二次散列
 * 寻址函数  i  i^2 i * hash2(x)
 *
 * 对于 f(i) = i^2 有 f(i) = f(i-1) + 2*i - 1
 * 对于任意元素 插入的位置为  (hash(x) + f(i)) % array.length
 * 注意点
 * 1、探测法中 装载因子的大小对性能影响很大  装载因子要 < 0.5
 * 2、无法直接删除元素，因为当前元素可能是寻找下一个元素的跳板，只能 将当前元素置为失效
 * 3、可能会插入失败，即 所有这些位置都用完了
 * 4、数组的大小 为 质数 较佳
 */
@Data
public class QuadraticProbingHashTable<T> {

    private int currentSize;
    private static final int DEFAULT_CAPACITY = 10;
    private static final double DEFAULT_LOAD_FACTOR = 0.5;
    private Node<T>[] dataArray;



    @Data
    private class Node<T>{
        private T data;
        private boolean isActive;

        public Node(T data, boolean isActive) {
            this.data = data;
            this.isActive = isActive;
        }
    }

    @SuppressWarnings("all")
    public QuadraticProbingHashTable() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("all")
    public QuadraticProbingHashTable(int capacity){
        dataArray = new Node[Prime.nextPrime(capacity)];
    }

    private int myHash(T t){
        int h;
        return t == null ? 0 : (h = t.hashCode())^(h >>> 16);
    }

    @SuppressWarnings("all")
    private void rehash(){
        Node<T>[] oldArray = this.dataArray;
        dataArray = new Node[Prime.nextPrime(oldArray.length * 2)];
        currentSize = 0;
        for (Node<T> node : oldArray){
            if (node != null && node.isActive){
                insert(node.data);
            }
        }
    }

    /**
     * 开放定址的关键  为当前元素找到一个 为空或者节点已经失效的位置
     * @param t
     * @return
     */
    private int findPosition(T t){
        int currentPosition = myHash(t) % dataArray.length;
        int offset = 1;

        while (dataArray[currentPosition] != null
                && dataArray[currentPosition].isActive && !dataArray[currentPosition].data.equals(t)){
            currentPosition += offset;
            offset += 2;

            if (currentPosition >= dataArray.length){
                currentPosition = currentPosition - dataArray.length;
            }
        }
        return currentPosition;
    }

    public void insert(T t){
        int position = findPosition(t);
        Node<T> tNode = dataArray[position];
        if (!isActive(tNode)){
            dataArray[position] = new Node<>(t, true);
            currentSize++;

            if (currentSize > dataArray.length / 2){
                rehash();
            }
        }
    }

    public void remove(T t){
        int position = findPosition(t);
        Node<T> tNode = dataArray[position];
        if (isActive(tNode)){
            tNode.isActive = false;
        }
    }

    public boolean contains(T t){
        return isActive(dataArray[findPosition(t)]);
    }

    private boolean isActive(Node<T> node){
        return node != null && node.isActive;
    }

    public static void main(String[] args){
        long l = System.nanoTime();
        QuadraticProbingHashTable<String> stringSeparateLinkHashTable = new QuadraticProbingHashTable<>(2);
        for (int i = 0; i < 220000; i++) {
            stringSeparateLinkHashTable.insert(String.valueOf(i));
        }
        System.out.println(System.nanoTime() - l);
    }


}
