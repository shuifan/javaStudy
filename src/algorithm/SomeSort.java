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
    public static <T extends Comparable<? super T>> void insertionSort(T[] a, int left, int right){
        int j;
        for (int i = left; i <= right ; i++){
            T tmp = a[i];
            for (j = i; j > left && tmp.compareTo(a[j - 1]) < 0; j--){
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
     * 将序列递归地一份为二，基准条件为 left<right，达到基准条件时，序列中只有两个值，每个值视为一个有序序列，将这些有序序列递归地合并为一个序列
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
    public static <T extends Comparable<? super T>> void swapReferences(T[] a, int i, int j){
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * 快速排序 基准条件为 left < tight， 先用三值法找出一个枢纽元素，然后比枢纽值小的放一边，大的放一边，两边继续递归
     * @param dataArray
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void quickSort(T[] dataArray){
        quickSort(dataArray, 0, dataArray.length - 1);
    }

    private static <T extends Comparable<? super T>> void quickSort(T[] dataArray, int left, int right){
        //长度超过10的用 quickSort 小于10的直接插入排序
        if (left + 10 <= right){
            //3值法之后 left的位置放的比枢纽值小的 right放的比枢纽值大的 枢纽值放在 right-1 的位置
            T pivot = median3(dataArray, left, right);
            int i = left;
            int j = right - 1;
            //此循环结束 i在第一个>=pivot的位置
            while (true){
                while (dataArray[++i].compareTo(pivot) < 0){}
                while (dataArray[--j].compareTo(pivot) > 0){}
                if (i < j){
                    swapReferences(dataArray, i, j);
                }else {
                    break;
                }
            }
            //将枢纽值放回原位
            swapReferences(dataArray, i, right - 1);
            quickSort(dataArray, left, i - 1);
            quickSort(dataArray, i + 1, right);
        }else {
            insertionSort(dataArray, left, right);
        }
    }

    /**
     * 3值法取一个枢纽值
     * 附加的  将left center right 中的小点的值移到left 大点的值移到 right 这样 在分割时，操作的范围就缩小了2
     * 从原来的 left right 变为  left+1 right-1
     * 另外 将 枢纽值 和 right-1交换，则操作的范围变为 right - 2
     * @param dataArray
     * @param left
     * @param right
     * @param <T>
     */
    public static <T extends Comparable<? super T>> T median3(T[] dataArray, int left, int right){
        int center = (left + right) / 2;
        if (dataArray[right].compareTo(dataArray[center]) < 0){
            swapReferences(dataArray, right, center);
        }
        if (dataArray[right].compareTo(dataArray[left]) < 0){
            swapReferences(dataArray, right, left);
        }
        if (dataArray[center].compareTo(dataArray[left]) < 0){
            swapReferences(dataArray, center, left);
        }

        swapReferences(dataArray, center, right - 1);
        //注意 此时枢纽值已经在 right - 1的位置上了 不能返回center位置的值
        return dataArray[right - 1];
    }

    public static void main(String[] args){
        int range = 100000000;
        Random random = new Random();
        Integer[] integers = new Integer[range];
        for (int i = 0; i < range; i++) {
            integers[i] = random.nextInt(range);
        }

        long l = System.nanoTime();
        quickSort(integers);
        System.out.println(System.nanoTime() - l);

    }
}
