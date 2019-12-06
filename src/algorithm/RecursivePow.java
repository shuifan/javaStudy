package algorithm;


import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 递归方式实现幂运算
 * x的n次方
 * n为偶数 则等同于 x*x 的 n/2 次方
 * n为奇数 则等同于 x的n-1次方 * x
 *
 * 注意  int n*n  n比较大时，会int溢出，应当全部用BigInteger处理
 */
public class RecursivePow {

    public static BigInteger pow(BigInteger x, int n){
        if (n == 0){
            return BigInteger.valueOf(1);
        }else {
            if (n % 2 == 0){
                return pow(x.multiply(x), n/2);
            }else {
                return pow(x, n - 1).multiply(x);
            }
        }
    }

    public static void main(String[] args){
        BigInteger pow = pow(BigInteger.valueOf(20), 40);
        BigInteger s = BigInteger.valueOf(20);
        System.out.println(s.pow(40).toString());
        System.out.println(pow);
    }
}
