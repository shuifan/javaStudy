package algorithm.cuckoo;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 一系列生成string的hash值的函数 改变乘的那个数
 */
public class StringHashFamily implements HashFamily<String>{

    private Random random = new Random();
    private List<Integer> factors = new LinkedList<>();

    @Override
    public void generateNewHashFunction() {
        int factor = random.nextInt(200);
        while (factors.contains(factor)){
            factor = random.nextInt(200);
        }
        factors.add(factor);
    }

    @Override
    public int getFunctionCount() {
        return factors.size();
    }

    @Override
    public int hash(String t, int functionIndex) {
        int factor = factors.get(functionIndex);
        int hash = 0;
        for (int i = 0; i < t.length(); i++) {
            hash = hash * factor + t.charAt(i);
        }
        return hash;
    }
}
