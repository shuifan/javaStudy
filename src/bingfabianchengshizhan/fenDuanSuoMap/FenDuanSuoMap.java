package bingfabianchengshizhan.fenDuanSuoMap;

import java.util.Objects;

/**
 * @author fandong
 * @create 2018/6/24
 */
public class FenDuanSuoMap<T, V> {

    private static final Integer N_LOCKS = 16;
    private final Node<T, V>[] buckets;
    private final Object[] locks;

    public FenDuanSuoMap(int initCount) {
        this.buckets = new Node[initCount];
        this.locks = new Object[initCount];
        for (int i = 0; i < initCount; i++) {
            locks[i] = new Object();
        }
    }

    private int hash(T t){
        return Math.abs(t.hashCode() % buckets.length);
    }

    public V get(T t){
        int hash = hash(t);
        synchronized (locks[hash % N_LOCKS]){
            for (Node<T, V> n = buckets[hash]; n != null; n = n.next){
                if (n.key.equals(t)){
                    return n.value;
                }
            }
            return null;
        }
    }

    public void put(T t, V v){
        int hash = hash(t);
        synchronized (locks[hash % N_LOCKS]){
            Node<T, V> startNode = buckets[hash];
            if (startNode == null){
                buckets[hash] = new Node<>(t, v);
                return;
            }
            Node<T, V> endNode = null;
            for (Node<T, V> n = startNode; n != null; n = n.next){
                if (n.key.equals(t)){
                    n.value = v;
                    return;
                }
                endNode = n;
            }
            endNode.next = new Node<>(t, v);
        }
    }

    public void clear(){
        for (int i = 0; i < buckets.length; i++) {
            synchronized (locks[i % N_LOCKS]){
                buckets[i] = null;
            }
        }
    }


    private static class Node<T, V>{
        private T key;
        private V value;
        private Node<T, V> next;

        public Node() {
        }

        public Node(T key, V value) {
            this.key = key;
            this.value = value;
        }
    }


    public static void main(String[] args){
        FenDuanSuoMap<String , String> fenDuanSuoMap = new FenDuanSuoMap<>(16);
        fenDuanSuoMap.put("a", "134");
        fenDuanSuoMap.put("a", "1345");
        fenDuanSuoMap.put("a", "1344");
        System.out.println(fenDuanSuoMap.get("a"));
        fenDuanSuoMap.clear();
        System.out.println(fenDuanSuoMap.get("a"));
    }
}
