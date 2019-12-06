package algorithm;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 后缀表达式 转化为 表达式树
 * 另 因为中缀可以转为后缀，则 二次转换为 表达式树
 * abc*+de*f+g*+
 */
public class SuffixToTree {


    private static Map<String, Integer> operatorMap = new HashMap<>(8);
    static {
        operatorMap.put("+", 1);
        operatorMap.put("-", 1);
        operatorMap.put("*", 2);
        operatorMap.put("/", 2);
    }
    @Data
    public static class BinaryNode<T>{
        private T data;
        private BinaryNode<T> left;
        private BinaryNode<T> right;

        public BinaryNode(T data) {
            this.data = data;
        }

        public BinaryNode(T data, BinaryNode<T> left, BinaryNode<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static BinaryNode<String> suffixToTree(String suffix){
        String temp;
        LinkedStack<BinaryNode<String>> linkedStack = new LinkedStack<>();
        for (int i = 0; i < suffix.length(); i++) {
            temp = suffix.substring(i, i + 1);
            if (operatorMap.containsKey(temp)){
                BinaryNode<String> p1 = linkedStack.pop();
                BinaryNode<String> p2 = linkedStack.pop();
                BinaryNode<String> p3 = new BinaryNode<>(temp, p2, p1);
                linkedStack.push(p3);
            }else {
                linkedStack.push(new BinaryNode<>(temp));
            }
        }
        return linkedStack.pop();
    }

    public static void main(String[] args){
        BinaryNode<String> stringBinaryNode = suffixToTree("ab+cde+**");
        System.out.println(1);
    }






}
