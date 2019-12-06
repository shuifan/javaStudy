package algorithm;

import edu.princeton.cs.algs4.Stack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author fandong
 * @create 2019/11/11
 * 用栈结构判断输入的括号序列是否配对完整
 * 方法 遇到左括号则入栈，遇到右括号则看栈中是否有对应的左括号
 */
public class Parentheses {

    public static Set<Character> leftParentheses = new HashSet<>(4);
    public static Set<Character> rightParentheses = new HashSet<>(4);
    public static Map<Character, Character> rightLeftParentheses = new HashMap<>(4);

    static {
        leftParentheses.add('{');
        leftParentheses.add('[');
        leftParentheses.add('(');
        rightParentheses.add('}');
        rightParentheses.add(']');
        rightParentheses.add(')');
        rightLeftParentheses.put('}', '{');
        rightLeftParentheses.put(']', '[');
        rightLeftParentheses.put(')', '(');
    }
    public static boolean parentheses(String s){
        Stack<Character> stack = new Stack<>();
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (!leftParentheses.contains(c) && !rightParentheses.contains(c)){
                continue;
            }
            if (leftParentheses.contains(c)){
                stack.push(c);
            }else {
                if (stack.isEmpty()){
                    return false;
                }
                Character pop = stack.pop();
                if (!rightLeftParentheses.get(c).equals(pop)){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args){
        //{()[]}{}[()]
        String s = "{()[]}{}[()]";
        String s1 = "{[]}{}[()]";
        String s2 = "[]}{}[()]";
        System.out.println(parentheses(s));
        System.out.println(parentheses(s1));
        System.out.println(parentheses(s2));
    }
}
