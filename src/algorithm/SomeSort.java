package algorithm;

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


    public static void main(String[] args){
        Random random = new Random();
        Integer[] integers = new Integer[10];
        for (int i = 0; i < 10; i++) {
            integers[i] = random.nextInt(1000);
        }

        insertionSort(integers);
        System.out.println(Arrays.toString(integers));
    }
}
