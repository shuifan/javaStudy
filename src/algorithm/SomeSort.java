package algorithm;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Random;

/**
 * 各种排序
 */
public class SomeSort {

    /**
     * 插入排序
     * 从 1 - N-1，挨个将对应位置上的元素放置到此元素之前序列中合适的位置
     * 从 1 - N-1 的位置 每次迭代开始前 之前的序列已经是排好序的
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void insertionSort(T[] a){
        int j;
        for (int i = 1; i < a.length ; i++){
            T tmp = a[i];
            for (j = i; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--){
                a[j] = a[j - 1];
            }
            a[j] = tmp;
        }
    }

    /**
     * 使用shell建议序列的 shell排序
     * 有一组序列 h1 h2 h3 .... ht
     * 对于 hk 有 从 hk 到 n-1的位置上i，将i 放入 i i-hk .. 中的合适位置上
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void shellSortWithShellArray(T[] a){
        for (int gap = a.length / 2; gap > 0; gap /= 2){
            for (int i = gap; i < a.length; i++){
                T tmp = a[i];
                int j;
                for (j = i; j  >= gap && tmp.compareTo(a[j - gap]) < 0; j -= gap){
                    a[j] = a[j - gap];
                }
                a[j] = tmp;
            }
        }
    }

    /**
     * 基于二叉堆的 排序
     * @param dataArray
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void heapSort(T[] dataArray, int size){
        for (int i = size / 2; i >= 0; i--){
            percolateDown(dataArray, i, size);
        }
        for (int i = size - 1; i > 0; i--) {
            T t = dataArray[0];
            dataArray[0] = dataArray[i];
            dataArray[i] = t;
            percolateDown(dataArray, 0, i);
        }
    }

    private static <T extends Comparable<? super T>> void percolateDown(T[] dataArray, int i, int currentSize){

        int leftChildIndex = i * 2 + 1;
        if (i < currentSize){
            T tmp = dataArray[i];
            while (leftChildIndex < currentSize){
                int rightChildIndex = leftChildIndex + 1;
                int littleIndex = leftChildIndex;
                if (rightChildIndex < currentSize){
                    if (dataArray[rightChildIndex].compareTo(dataArray[leftChildIndex]) < 0){
                        littleIndex = rightChildIndex;
                    }
                }
                if (tmp.compareTo(dataArray[littleIndex]) > 0){
                    dataArray[i] = dataArray[littleIndex];
                    i = littleIndex;
                    leftChildIndex = i * 2 + 1;
                }else {
                    break;
                }
            }
            dataArray[i] = tmp;
        }

    }


    public static void main(String[] args){
        Random random = new Random();
        Integer[] integers = new Integer[1000000];
        for (int i = 0; i < 1000000; i++) {
            integers[i] = random.nextInt(1000000);
        }

        long l = System.nanoTime();
        shellSortWithShellArray(integers);
        System.out.println(System.nanoTime() - l);
    }
}
