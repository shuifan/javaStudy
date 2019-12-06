package algorithm;

import lombok.Data;

import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * @author fandong
 * @create 2019/11/6
 *
 * 基于链表的栈
 */
@Data
public class LinkedStack<T> implements Iterable<T>{

    /**
     * 栈顶
     */
    private Item<T> top;
    private int size;

    @Override
    public Iterator<T> iterator() {
        return new MyLinkedIterator(top);
    }

    private class MyLinkedIterator implements Iterator<T>{
        private Item<T> current;

        private MyLinkedIterator(Item<T> current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public T next() {
            Item<T> temp = current;
            current = current.next;
            return temp.data;
        }
    }

    @Data
    private class Item<T>{
        private Item<T> next;
        private T data;

        private Item(T data) {
            this.data = data;
        }
    }

    public LinkedStack() {
        top = null;
        size = 0;
    }

    public void push(T data){
        Item<T> newItem = new Item<>(data);
        newItem.next = top;
        top = newItem;
        size++;
    }

    public T top(){
        if (top != null){
            return top.data;
        }else {
            return null;
        }
    }

    public T pop(){
        if (top == null){
            throw new EmptyStackException();
        }
        Item<T> item = top;
        top = top.next;
        item.next = null;
        size--;
        return item.data;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public static void main(String[] args){
        LinkedStack<Integer> linkedStack = new LinkedStack<>();
        for (int i = 0; i < 10; i++) {
            linkedStack.push(i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(linkedStack.pop());

        }
    }


}
