package algorithm;

/**
 * @author fandong
 * @create 2019/11/14
 *
 * 递归 单次打印一个数 顺序打印 76432 正序或倒序
 */
public class PrintNumber {

    public static void printNumber(int n){
        System.out.println(n % 10);
        n = n / 10;
        if (n > 0){
            printNumber(n);
        }
    }

    public static void printNumberAsc(int n){
        if (n > 0){
            printNumberAsc(n / 10);
            System.out.println(n % 10);
        }
    }

    public static void main(String[] args){
        printNumber(76432);
        printNumberAsc(76432);
    }
}
