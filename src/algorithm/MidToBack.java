package algorithm;

import sun.awt.image.ImageWatched;

import java.util.HashMap;
import java.util.Map;

/**
 * 中缀表达式到后缀表达式的 相互转换
 *  a + b * c + (d * e + f) * g
 *  abc*+de*f+g*+
 */
public class MidToBack {

    private static Map<String, Integer> operatorMap = new HashMap<>(8);
    static {
        operatorMap.put("+", 1);
        operatorMap.put("-", 1);
        operatorMap.put("*", 2);
        operatorMap.put("/", 2);
    }

    public static void midToBack(String mid){
        mid = mid.replace(" ", "");
        LinkedStack<String> operatorStack = new LinkedStack<>();
        String temp;
        for (int i = 0; i < mid.length(); i++) {
            temp = mid.substring(i, i + 1);
            //遇到左括号直接入栈 并开始下一次循环
            if ("(".endsWith(temp)){
                operatorStack.push(temp);
                continue;
            }
            //仅当遇到右括号时，出栈直到左括号
            if (")".endsWith(temp)){
                while (true){
                    String pop = operatorStack.pop();
                    if (pop.equals("(")){
                        break;
                    }
                    System.out.print(pop);
                }
                continue;
            }
            Integer tempPriority = operatorMap.get(temp);
            //非运算符直接输出
            if (tempPriority == null){
                System.out.print(temp);
            }else {
                //为运算符时，栈为空则直接入栈
               if (operatorStack.isEmpty()){
                   operatorStack.push(temp);
               }else {
                   //为运算符时，则需要看前面是否有 优先级>= 当前运算符的
                   while (!operatorStack.isEmpty()){
                       String top = operatorStack.top();
                       //遇到左括号 直接退出
                       if ("(".equals(top)){
                           break;
                       }
                       //栈顶部优先级低于当前运算符 退出
                       if (operatorMap.get(top) < tempPriority ){
                           break;
                       }else {
                           //将栈中 优先级>= 当前运算符的 输出
                           System.out.print(top);
                           operatorStack.pop();
                       }
                   }
                   //完成上述循环后，优先级>=的已输出，或 已遇到左括号
                   //将当前运算符入栈
                   operatorStack.push(temp);
               }
            }
        }
        //输出剩下的运算符
        while (!operatorStack.isEmpty()){
            System.out.print(operatorStack.pop());
        }
    }

    public static String backToMid(String back){
        LinkedStack<String> linkedStack = new LinkedStack<>();
        String temp;
        for (int i = 0; i < back.length(); i++) {
            temp = back.substring(i, i + 1);
            if (operatorMap.containsKey(temp)){
                String pop1 = linkedStack.pop();
                String pop2 = linkedStack.pop();
                linkedStack.push("(" + pop2 + temp + pop1 + ")");
            }else {
                linkedStack.push(temp);
            }
        }
        return linkedStack.pop();
    }

    public static void main(String[] args){
        String mid = "a + b * c + (d * e + f) * g - c";
        midToBack(mid);
        System.out.println();
        String mid1 = backToMid("abc*+de*f+g*+c-");
        System.out.println(mid1);
        System.out.println(Parentheses.parentheses(mid1));
    }
}
