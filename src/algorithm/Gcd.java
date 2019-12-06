package algorithm;

/**
 * @author fandong
 * @create 2019/11/6
 * 计算两个非负整数的最大公约数
 * 公约数 p，q的最大公约数，若q为0，则p为最大公约数；
 * 否则p除以q的余数为r，p和q的最大公约数为q和r的最大公约数
 */
public class Gcd {

    /**
     * p > q
     * @param p
     * @param q
     * @return
     */
    public static int gcd(int p, int q){
        if (q == 0){
            return p;
        }else {
            int r = p % q;
            return gcd(q, r);
        }
    }

    public static int gcdFor(int p, int q){
        int r;
        while (q != 0){
            r = p % q;
            p = q;
            q = r;
        }
        return p;
    }

    public static void main(String[] args){
        long start = System.nanoTime();
        System.out.println(gcdFor(2,3));
        System.out.println(gcdFor(20,30));
        System.out.println(gcdFor(21,30));
        System.out.println(gcdFor(25,33));
        long end = System.nanoTime();
        System.out.println((end - start));

        long start1 = System.nanoTime();
        System.out.println(gcd(2,3));
        System.out.println(gcd(20,30));
        System.out.println(gcd(21,30));
        System.out.println(gcd(25,33));
        long end1 = System.nanoTime();
        System.out.println((end1 - start1));
    }
}
