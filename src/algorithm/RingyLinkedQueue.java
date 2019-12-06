package algorithm;

import lombok.Data;

/**
 * @author fandong
 * @create 2019/11/12
 * 基于环形链表实现队列
 */
@Data
public class RingyLinkedQueue<T> {

    private int n;
    private Node<T> last;

    public void enqueue(T t){
        Node<T> newNode = new Node<>(t);
        if (last == null){
            last = newNode;
            last.next = last;
        }else {
            newNode.next = last.next;
            last.next = newNode;
            last = newNode;
        }
        n++;
    }

    public T dequeue(){
        if (n == 0){
            throw new IllegalStateException();
        }
        Node<T> oldFirst = last.next;
        last.next = last.next.next;
        n--;
        if (n == 0){
            last = null;
        }
        return oldFirst.data;
    }

    public boolean isEmpty(){
        return  n == 0;
    }

    @Data
    private class Node<T>{
        private T data;
        private Node<T> next;

        private Node(T data) {
            this.data = data;
        }
    }


    public static void main(String[] args){
        RingyLinkedQueue<Integer> ringyLinkedQueue = new RingyLinkedQueue<>();
        for (int i = 0; i < 9; i++) {
            ringyLinkedQueue.enqueue(i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(ringyLinkedQueue.dequeue());
        }
    }
}
