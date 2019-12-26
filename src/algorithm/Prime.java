package algorithm;

import lombok.Data;

/**
 * 计算素数
 * 素数的定义为 只能被 1和自身整除
 * 实际计算时 判断  是否会被 2 到 根号n整除 不会则是素数
 */
@Data
public class Prime {

    public static int nextPrime(int n){
        while (true){
            n++;
            double sqrt = Math.sqrt(n);
            boolean isPrime = true;
            for (int i = 2; i <= sqrt; i++){
                if (n % i == 0){
                    isPrime = false;
                    break;
                }
            }
            if (isPrime){
                return n;
            }
        }
    }

    public static void main(String[] args){
        long l = System.nanoTime();
        System.out.println(nextPrime(109));
        System.out.println(System.nanoTime() - l);
    }
}
