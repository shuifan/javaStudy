package algorithm;

import java.util.LinkedList;
import java.util.List;

/**
 * @author fandong
 * @create 2019/11/12
 * 约瑟夫环  n个人，每m个杀一个，求最后存活
 * 思路 基于队列的实现，所有人先入队，m-1个人先出队，再入队，第m个为要kill的人
 */
public class Josephus {

    public static int josephusQueue(int n, int m){
        LinkedQueue<Integer> linkedQueue = new LinkedQueue<>();
        for (int i = 0; i < n; i++) {
            linkedQueue.enqueue(i);
        }
        while (linkedQueue.size() >1){
            for (int i = 0; i < m-1; i++) {
                linkedQueue.enqueue(linkedQueue.dequeue());
            }
            System.out.println("kill " + linkedQueue.dequeue());
        }
        return linkedQueue.dequeue();
    }

    public static void main(String[] args){
        System.out.println(josephusQueue(7,2));
    }
}
