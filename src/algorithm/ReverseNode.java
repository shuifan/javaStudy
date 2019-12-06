package algorithm;

import lombok.Data;

import javax.xml.soap.Node;

/**
 * @author fandong
 * @create 2019/11/12
 * 反转链表
 * 思路 a->b->c  反转 a<-b<-c  迭代链表，每次反转相邻的两个，并记录新的头节点
 * 单项链表迭代到null结束，环链表 迭代到原头节点结束
 */
@Data
public class ReverseNode {

    @Data
    public static class Node<T>{
        private T data;
        private Node<T> next;

        Node(T t){
            data = t;
        }
    }

    public static <T> Node<T> reverse(Node<T> node){

        Node<T> first = node;
        Node<T> newFirst = null;
        while (first != null){
            Node<T> second =  first.next;
            first.next = newFirst;
            newFirst = first;
            first = second;
        }
        return newFirst;
    }

    public static void main(String[] args){
        Node<Integer> a1 = new Node<>(1);
        Node<Integer> a2 = new Node<>(2);
        Node<Integer> a3 = new Node<>(3);
        a1.next = a2;
        a2.next = a3;

        Node<Integer> reverse = reverse(a1);
        while (reverse != null){
            System.out.println(reverse.data);
            reverse = reverse.next;
        }
    }
}
