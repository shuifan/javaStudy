package bingfabianchengshizhan;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author fandong
 * @create 2018/6/27
 */
public class ConcurrentStack<E> {

    private AtomicReference<Node> top = new AtomicReference<>();

    public void push(E e){
        Node<E> newTop = new Node<>(e);
        Node<E> oldTop;
        do {
            oldTop = top.get();
            newTop.next = oldTop;
        }while (!top.compareAndSet(oldTop, newTop));
    }

    public E pop(){
        Node<E> oldTop;
        Node<E> newTop;

        do {
            oldTop = top.get();
            if (oldTop == null){
                return null;
            }
            newTop = oldTop.next;
        }while (!top.compareAndSet(oldTop, newTop));
        return oldTop.item;
    }



    private class Node<E>{
        E item;
        Node<E> next;

        public Node() {
        }

        public Node(E item) {
            this.item = item;
        }
    }

    public static void main(String[] args){
        ConcurrentStack<String> concurrentStack = new ConcurrentStack<>();
        concurrentStack.push("123");
        System.out.println(concurrentStack.pop());
        System.out.println(concurrentStack.pop());
    }
}
