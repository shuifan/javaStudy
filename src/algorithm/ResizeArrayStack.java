package algorithm;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author fandong
 * @create 2019/11/6
 *
 * 栈：
 * 基于数组
 * 自动扩缩容：空间不足扩容两倍，占用不到1/4，缩容为一般
 * 泛型支持
 * 迭代支持
 *
 * bug:top对应栈顶，添加时top++，实际栈顶元素在top-1处，pop时应当先top-1，再取值
 */
public class ResizeArrayStack<T> implements Iterable<T>{

    private T[] dataArray;
    private int top;
    public ResizeArrayStack(int initCapacity) {
        if (initCapacity <= 0){
            throw new IllegalArgumentException("illegal initCapacity");
        }
        dataArray = (T[])new Object[initCapacity];
        top = 0;
    }

    public void push(T item){

        if (top == (dataArray.length - 1)){
            resize(dataArray.length * 2);
        }
        dataArray[top++] = item;
    }

    public T pop() throws IllegalAccessException {
        if (top == 0){
            throw new IllegalAccessException("the stack is empty");
        }
        if (top > 0 && top <= (dataArray.length / 4)){
            resize(dataArray.length / 2);
        }
        --top;
        T t = dataArray[top];
        //pop出去之后及时清理不用对象的引用
        dataArray[top] = null;
        return t;
    }

    public boolean isEmpty(){
        return top == 0;
    }

    private void resize(int newSize){
        System.out.println("resize to " + newSize);
        dataArray = Arrays.copyOf(dataArray, newSize);
    }

    @Override
    public Iterator<T> iterator() {
        return new ResizeArrayStackIterator();
    }

    private class ResizeArrayStackIterator implements Iterator<T>{

        private int i = top;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public T next() {
            return dataArray[--i];
        }
    }


    public static void main(String[] args) throws IllegalAccessException {
        ResizeArrayStack<Integer> resizeArrayStack = new ResizeArrayStack<>(8);
        for (int i = 0; i < 10; i++) {
            resizeArrayStack.push(i);
        }
        Iterator<Integer> iterator = resizeArrayStack.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        for (int i = 0; i < 10; i++) {
            resizeArrayStack.pop();
        }
    }
}
