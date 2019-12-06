package algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * @author fandong
 * @create 2019/11/11
 * 把只有右括号的表达式补全
 * 1+2)*3-4)*5-6)))   => ((1+2)*((3-4)*(5-6)))
 * 双栈法  数值和运算符分开存储 每遇到一个右括号，则弹出两个数值和一个运算符组成新的表达式，新的表达式再入栈
 */
public class ComplementBrackets {

    public static Set<String> operatorSet = new HashSet<>(8);
    static {
        operatorSet.add("+");
        operatorSet.add("-");
        operatorSet.add("*");
        operatorSet.add("/");
    }

    public static String complementBrackets(String s){
        LinkedStack<String> ops = new LinkedStack<>();
        LinkedStack<String> vals = new LinkedStack<>();

        String theChar;
        //补全括号
        for (int i = 0; i < s.length(); i++) {
            theChar = String.valueOf(s.charAt(i));
            if (operatorSet.contains(theChar)){
                ops.push(theChar);
            }else if (")".equals(theChar)){
                valPopAndPush(ops, vals, true);
            }else {
                vals.push(theChar);
            }
        }
        //最后拼接运算符和表达式 清空运算符栈
        while (!ops.isEmpty()){
            valPopAndPush(ops, vals, false);
        }
        return vals.pop();
    }

    /**
     * 取一个运算符和两个表达式
     * @param ops
     * @param vals
     * @param brackets 控制是否在两边加上括号 有右括号要加括号，最后拼接不加括号
     */
    private static void valPopAndPush(LinkedStack<String> ops, LinkedStack<String> vals, boolean brackets){
        String op = ops.pop();
        String v1 = vals.pop();
        String v2 = vals.pop();
        StringBuilder sb = new StringBuilder(op.length() + v1.length() + v2.length() + 2);
        if (brackets){
            sb.append("(");
        }
        sb.append(v2).append(op).append(v1);
        if (brackets){
            sb.append(")");
        }
        vals.push(sb.toString());
    }


    public static void main(String[] args){
        String s = "1+2)*3-4)/5-6))";
        System.out.println(complementBrackets(s));
    }
}
