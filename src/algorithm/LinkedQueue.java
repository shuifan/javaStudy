package algorithm;

import lombok.Data;

import javax.management.NotificationEmitter;
import java.util.EmptyStackException;

/**
 * @author fandong
 * @create 2019/11/11
 * FIFO  队列
 */

@Data
public class LinkedQueue<T> {

    private Node<T> first;
    private Node<T> last;
    private int n;

    public void enqueue(T t){
        Node<T> newNode = new Node<>(t);
        if (first == null){
            first = last = newNode;
        }else {
            Node<T> oldLast = last;
            last = newNode;
            oldLast.next = newNode;
        }
        n++;
    }

    public void enqueue(T[] ts){
        for (T t : ts){
            enqueue(t);
        }
    }

    public T dequeue(){
        if (first == null){
            throw new EmptyStackException();
        }
        Node<T> oldFirst = first;
        first = first.next;
        n--;
        return oldFirst.data;
    }

    public int size(){
        return n;
    }

    public boolean isEmpty(){
        return n == 0;
    }

    @Data
    private class Node<T>{
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    public static void main(String[] args){
        LinkedQueue<Integer> linkedQueue = new LinkedQueue<>();
        for (int i = 0; i < 4; i++) {
            linkedQueue.enqueue(i);
        }
        System.out.println(linkedQueue.size());
        for (int i = 0; i < 5; i++) {
            System.out.println(linkedQueue.dequeue());
        }
    }

}
