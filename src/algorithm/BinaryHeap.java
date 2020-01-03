package algorithm;


import java.util.List;
import java.util.Random;

/**
 * 用二叉堆实现优先级队列
 * 二叉堆是一颗完全二叉树，两个性质如下：
 * 1、结构性质，元素按照从上到下，从左到右的顺序摆放
 * 因此，可用数组存放元素，i位置的元素的儿子分别在 2i和2i+1的位置，父节点在 i/2的位置
 * 2、堆序性质，每个节点都小于或大于其子节点
 * 由此，根节点处存放了 最大或最小的节点
 *
 * 数组中元素的存放从 1 开始，因为从1开始方便计算父子节点的位置
 * @param <T>
 */
public class BinaryHeap<T extends Comparable<? super T>> {

    private static final int DEFAULT_CAPACITY = 10;
    private int currentSize = 0;

    private T[] dataArray;

    @SuppressWarnings("all")
    public BinaryHeap() {
        dataArray = (T[]) new Comparable[Prime.nextPrime(DEFAULT_CAPACITY)];
    }

    @SuppressWarnings("all")
    public BinaryHeap(T[] tArray){
        currentSize = tArray.length;
        dataArray = (T[]) new Comparable[Prime.nextPrime(currentSize + 1)];
        int i = 1;
        for (T t : tArray){
            if (t != null){
                dataArray[i] = t;
                i++;
            }
        }
        buildHeap();
    }

    public void insert(T t){
        if (t == null){
            return;
        }
        currentSize++;
        if (currentSize >= dataArray.length){
            expand();
        }

        dataArray[currentSize] = t;
        percolateUp(currentSize);
    }

    public T deleteMin(){
        if (currentSize <= 0){
            return null;
        }
        T t = dataArray[1];
        dataArray[1] = dataArray[currentSize];
        dataArray[currentSize] = null;
        currentSize--;
        percolateDown(1);
        return t;
    }

    @SuppressWarnings("all")
    private void expand(){
        T[] oldArray = this.dataArray;
        dataArray = (T[]) new Comparable[Prime.nextPrime(oldArray.length * 2)];
        for (int i = 0; i < oldArray.length; i++) {
            T t = oldArray[i];
            if (t != null){
                dataArray[i] = t;
            }
        }
    }

    private void percolateUp(int index){
        int tmpFather = index / 2;
        while (dataArray[tmpFather] != null && dataArray[tmpFather].compareTo(dataArray[index]) > 0){
            T t = dataArray[index];
            dataArray[index] = dataArray[tmpFather];
            dataArray[tmpFather] = t;
            index = tmpFather;
            tmpFather = index / 2;
        }
    }

    private void percolateDown(int index){
        int tmpLeft = index * 2;
        while (tmpLeft <= currentSize && dataArray[tmpLeft] != null){
            int tmpIndex = tmpLeft;
            int tmpRight = tmpLeft + 1;
            if (tmpRight <= currentSize && dataArray[tmpRight] != null){
                tmpIndex = dataArray[tmpLeft].compareTo(dataArray[tmpRight]) < 0 ? tmpLeft : tmpRight;
            }
            if (dataArray[tmpIndex].compareTo(dataArray[index]) < 0){
                T t = dataArray[tmpIndex];
                dataArray[tmpIndex] = dataArray[index];
                dataArray[index] = t;
                index = tmpIndex;
                tmpLeft = index * 2;
            }else {
                break;
            }
        }
    }

    private void buildHeap(){
        for (int i = currentSize / 2; i > 0 ;i--) {
            percolateDown(i);
        }
    }

    public static void main(String[] args){
        Random random = new Random();
        BinaryHeap<Integer> binaryHeap = new BinaryHeap<>();
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(100);
            System.out.println(index);
            binaryHeap.insert(index);
        }

        for (int i = 0; i < 11; i++) {
            System.out.println(binaryHeap.deleteMin());
        }

    }
}
