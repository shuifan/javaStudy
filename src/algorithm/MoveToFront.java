package algorithm;

import lombok.Data;

/**
 * @author fandong
 * @create 2019/11/13
 * 前移编码  读取一串字符 新的字符插入表头，重复字符删除原来的并插入表头
 */
@Data
public class MoveToFront<T> {

    private Node<T> first;
    private int n;

    @Data
    private class Node<T>{
        private T data;
        private Node<T> next;

        private Node(T data) {
            this.data = data;
        }
    }

    public void add(T t){
        if (first == null){
            first = new Node<>(t);
            n++;
        }else {
            Node<T> var = first;
            Node<T> previous = null;
            while (var != null){
                if (var.data.equals(t)){
                    if (previous != null){
                        previous.next = var.next;
                        var.next = first;
                        first = var;
                        return;
                    }else {
                        return;
                    }
                }
                previous = var;
                var = var.next;
            }
            Node<T> oldFirst = first;
            first = new Node<>(t);
            first.next = oldFirst;
            n++;
        }
    }

    public T get(){
        Node<T> oldFirst = first;
        first = first.next;
        n--;
        return oldFirst.data;
    }

    public boolean isEmpty(){
        return n == 0;
    }


    public static void main(String[] args){
        MoveToFront<String> moveToFront = new MoveToFront<>();
        moveToFront.add("1");
        moveToFront.add("2");
        moveToFront.add("1");
        moveToFront.add("1");
        moveToFront.add("1");
        moveToFront.add("3");
        moveToFront.add("1");
        moveToFront.add("3");

        while (!moveToFront.isEmpty()){
            System.out.println(moveToFront.get());
        }
    }


}
