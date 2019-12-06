package algorithm;

import java.util.Arrays;

/**
 * @author fandong
 * @create 2019/11/6
 * 二分查找
 * 对数据先排序，然后二分查找，找到则返回排序后的索引位置，没有则返回-1
 */
public class BinarySearch {

    public static int binarySearch(int key, int[] data){
        int start = 0;
        int end = data.length - 1;
        int mid;
        while (start <= end){
            mid = (start + end) / 2;
            int midData = data[mid];
            if (key == midData){
                return mid;
            }else if (key < midData){
                end = midData -1;
            }else {
                start = midData + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        int[] data = new int[]{1,3,4,7,8,2};
        Arrays.sort(data);
        System.out.println(Arrays.toString(data));
        System.out.println(binarySearch(10, data));
        System.out.println(binarySearch(2, data));
        System.out.println(binarySearch(3, data));
    }

}
