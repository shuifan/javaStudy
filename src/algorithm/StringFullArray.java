package algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * 字符串全排列
 * a b c
 * abc acb
 * bac bca
 * cab cba
 */
public class StringFullArray {

    public static Set<String> set =  new HashSet<>();

    public static void stringFullArray(char[] chars, int i){
        if (i == (chars.length-1)){
            set.add(String.valueOf(chars));
        }else {
            for (int j = i; j < chars.length; j++){
                swap(chars, i , j);
                stringFullArray(chars, i+1);
                swap(chars, i , j);
            }
        }
    }

    public static void swap(char[] chars, int i, int j){
        char iChar = chars[i];
        chars[i] = chars[j];
        chars[j] = iChar;
    }

    public static void main(String[] args){
        char[] chars = {'a', 'b', 'c', 'd'};
        stringFullArray(chars, 0);
        for (String s : set){
            System.out.println(s);
        }
        System.out.println("total: " + set.size());
    }
}
