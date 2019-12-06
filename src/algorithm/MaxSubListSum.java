package algorithm;

import java.util.Random;

/**
 * 求一个序列的最大子序列的和，有正有负，和应为正数
 */
public class MaxSubListSum {

    /**
     * 只遍历一次，从头开始，不断记录算出的最大和
     * 如果某个序列的和为负，则跳过此序列
     */
    public static int oneTimeScan(int[] a){
        int maxSum = 0;
        int thisSum = 0;
        for(int i : a){
            thisSum += i;
            if (thisSum > maxSum){
                maxSum = thisSum;
            }else if (thisSum < 0){
                thisSum = 0;
            }
        }
        return maxSum;
    }

    /**
     * 分治法递归  每次将序列一分为二，分别求 左右最大子序列和 以及 跨中间的最大和,每次的最大值为 三者最大值
     */
    public static int maxThisTime(int[] a, int left, int right){
        if (left == right){
            return Math.max(a[left], 0);
        }

        int mid = (left + right) / 2;
        int maxLeft = maxThisTime(a, left, mid - 1);
        int maxRight = maxThisTime(a, mid , right);

        int midLeftMax = 0;
        int midLeftThis = 0;
        for (int i= mid; i >= left; i--){
             midLeftThis = midLeftThis + a[i];
            if (midLeftThis > midLeftMax){
                midLeftMax = midLeftThis;
            }
        }
        int midRightMax = 0;
        int midRightThis = 0;
        for (int i= mid + 1; i <= right; i++){
            midRightThis = midRightThis + a[i];
            if (midRightThis > midRightMax){
                midRightMax = midRightThis;
            }
        }

        return Math.max(maxLeft, Math.max(maxRight, midLeftMax + midRightMax));
    }

    public static void main(String[] args){
        Random random = new Random(System.currentTimeMillis());
        int[] a = new int[55];
        for (int i = 0; i < 55; i++) {
            a[i] = random.nextInt(1000);
            if (random.nextBoolean()){
                a[i] = - a[i];
            }
        }
        System.out.println(oneTimeScan(a));
        System.out.println(maxThisTime(a, 0 , a.length - 1));
    }
}
