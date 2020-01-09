package algorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * 从n个元素中找出第 k 个最大的元素
 */
public class NKSelection {

    /**
     * 基于二叉堆 时间复杂度 n + klogn
     * 构建堆 时间复杂度 n
     * k次deletemin 时间复杂度 klogn
     * @param a
     * @param k
     * @param <T>
     * @return
     */
    public static <T extends Comparable<? super T>> T heapBaseSelect(T[] a, int k){
        BinaryHeap<T> binaryHeap = new BinaryHeap<>(a);
        for (int i = 0; i < k - 1; i++) {
            binaryHeap.deleteMin();
        }
        return binaryHeap.deleteMin();
    }

    /**
     * 类似于 quick sort
     * 每次比较 枢纽值的位置 和 k
     * 不同点在于 只需要关注 k 所在的部分
     * 最后 k值在 k - 1的位置上
     * 时间复杂度 n
     * @param a
     * @param k
     * @param <T>
     * @return
     */
    public static <T extends Comparable<? super T>> T quickSelect(T[] a, int k){
        quickSelect(a, k, 0, a.length - 1);
        return a[k - 1];
    }

    private static <T extends Comparable<? super T>> void quickSelect(T[] a, int k, int left, int right){
        if (left + 10 <= right){
            T pivot = SomeSort.median3(a, left, right);
            int i = left;
            int j = right - 1;
            while (true){
                while (a[++i].compareTo(pivot) < 0){}
                while (a[--j].compareTo(pivot) > 0){}
                if (i < j){
                    SomeSort.swapReferences(a, i , j);
                }else {
                    break;
                }
            }
            SomeSort.swapReferences(a, i, right - 1);
            if ((k - 1) < i){
                quickSelect(a, k, left, i - 1);
            }else if ((k - 1) > i){
                quickSelect(a, k, i + 1, right);
            }
        }else {
            SomeSort.insertionSort(a, left, right);
        }
    }

    public static void main(String[] args){
        int range = 10000;
        Random random = new Random();
        Integer[] integers = new Integer[range];
        for (int i = 0; i < range; i++) {
            integers[i] = random.nextInt(range);
        }
        Integer[] copy = Arrays.copyOf(integers, integers.length);
        System.out.println(heapBaseSelect(integers, 44));
        System.out.println(quickSelect(copy, 44));
    }
}
