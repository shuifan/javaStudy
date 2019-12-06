package algorithm;

import lombok.Data;

/**
 * @author fandong
 * @create 2019/11/12
 * 基于双向链表的双端队列
 */
@Data
public class LinkedDeque<T> {

    private Node<T> left;
    private Node<T> right;
    private int n;



    @Data
    private class Node<T>{
        private T data;
        private Node<T> previous;
        private Node<T> next;

        private Node(T data) {
            this.data = data;
        }
    }

    public int size(){
        return n;
    }

    public void pushLeft(T t){
        Node<T> newNode = new Node<>(t);
        if (right == null){
            right = left = newNode;
        }else {
            left.previous = newNode;
            newNode.next = left;
            left = newNode;
        }
        n++;
    }

    public void pushRight(T t){
        Node<T> newNode = new Node<>(t);
        if (right == null){
            right = left = newNode;
        }else {
            right.next = newNode;
            newNode.previous = left;
            right = newNode;
        }
        n++;
    }

    public T popLeft(){
        if (n == 0){
            throw new IllegalStateException();
        }
        Node<T> oldLeft = left;
        left = left.next;
        //去除对旧节点的引用
        if (left != null){
            left.previous = null;
        }
        n--;
        return oldLeft.data;
    }

    public T popRight(){
        if (n == 0){
            throw new IllegalStateException();
        }
        Node<T> oldRight = right;
        right = right.previous;
        //去除对旧节点的引用
        if (right != null){
            right.next = null;
        }
        n--;
        return oldRight.data;
    }

    public static void main(String[] args){
        LinkedDeque<Integer> linkedDeque = new LinkedDeque<>();
        for (int i = 0; i < 4; i++) {
            linkedDeque.pushLeft(i);
        }
        for (int i = 0; i < 4; i++) {
            System.out.println(linkedDeque.popRight());
        }
        for (int i = 0; i < 4; i++) {
            linkedDeque.pushRight(i);
        }
        for (int i = 0; i < 4; i++) {
            System.out.println(linkedDeque.popLeft());
        }
     }
}
