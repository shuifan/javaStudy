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
            swapReferences(dataArray, 0, i);
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

    /**
     * 归并排序
     * @param dataArray
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void mergeSort(T[] dataArray){
        T[] tmpArray = (T[]) new Comparable[dataArray.length];
        mergeSort(dataArray, tmpArray, 0, dataArray.length - 1);
    }

    private static <T extends Comparable<? super T>> void mergeSort(T[] dataArray, T[] tmpArray, int left, int right){
        //基准条件  left = right - 1  其实这种就是 两组 每组一个数字，将他们排序并放入数组中相同的位置上
        if (left < right){
            int center = (left + right) / 2;
            mergeSort(dataArray, tmpArray, left, center);
            mergeSort(dataArray, tmpArray, center + 1, right);
            merge(dataArray, tmpArray, left, center + 1, right);
        }
    }

    /**
     * 将两个有序序列合并为一个 序列
     * @param dataArray
     * @param tmpArray
     * @param left
     * @param rightPos
     * @param rightEnd
     * @param <T>
     */
    private static <T extends Comparable<? super T>> void merge(T[] dataArray, T[] tmpArray, int left, int rightPos, int rightEnd){

        int leftEnd = rightPos - 1;
        int tmpIndex = left;
        int size = rightEnd - left + 1;

        //依次从头开始比较 将较小的放入临时数组中
        //这一步做完之后 一定有一个数组空了 将另外一个数组中剩下的元素（都是较大的元素）搬入临时数组即可
        while (left <= leftEnd && rightPos <= rightEnd){
            if (dataArray[left].compareTo(dataArray[rightPos]) < 0){
                tmpArray[tmpIndex++] = dataArray[left++];
            }else {
                tmpArray[tmpIndex++] = dataArray[rightPos++];
            }
        }

        while (left <= leftEnd){
            tmpArray[tmpIndex++] = dataArray[left++];
        }

        while (rightPos <= rightEnd){
            tmpArray[tmpIndex++] = dataArray[rightPos++];
        }

        for (int i = 0; i < size; i++, rightEnd--) {
            dataArray[rightEnd] = tmpArray[rightEnd];
        }
    }

    /**
     * 把将数组中两个位置的值交换  封装起来 用的很多
     * @param a
     * @param i
     * @param j
     * @param <T>
     */
    private static <T extends Comparable<? super T>> void swapReferences(T[] a, int i, int j){
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args){
        int range = 10000000;
        Random random = new Random();
        Integer[] integers = new Integer[range];
        for (int i = 0; i < range; i++) {
            integers[i] = random.nextInt(range);
        }

        long l = System.nanoTime();
        mergeSort(integers);
        System.out.println(System.nanoTime() - l);

    }
}
