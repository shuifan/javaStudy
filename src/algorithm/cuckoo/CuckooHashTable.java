package algorithm.cuckoo;

import algorithm.Prime;
import lombok.Data;

import java.util.Random;

/**
 * 基于一个散列函数，辅以分离链接或开放定址来解决散列冲突的散列表的
 * 问题在于：平均操作时间为 O(1) ，最差情况下的访问时间是不可控的
 * 原因在于 链表可能很长，或者在一个大的数组里需要定址很多次才能找
 * 到合适的位置
 *
 * 布谷鸟散列，通过引入多个散列函数以及一个或多个数组，每个值的最差
 * 访问时间为 O（散列函数的数量），散列函数的数量应当是一个很小的值，
 * 从而 布谷鸟散列有明确的操作时间下界
 *
 * 布谷鸟散列的问题在于 散列函数的选择直接影响到性能，十分关键；并且
 * 和平方探测法一样，装载因子需要在0.5以下
 *
 * hash函数和数组大小 二者变其一则需要将原有数组的元素重新添加到新的数组中
 * @param <T>
 */
@Data
public class CuckooHashTable<T> {

    private T[] dataArray;

    private int currentSize;

    private HashFamily<T> hashFamily;

    private Random random;

    private int lastReplacePosition = -1;

    private int rehashFrequency = 0;

    private static final int DEFAULT_CAPACITY = 10;
    private static final int ALLOWED_REHASH_FREQUENCY = 1;
    private static final double DEFAULT_FACTOR = 0.4;

    public CuckooHashTable(HashFamily<T> hashFamily) {
        random = new Random();
        dataArray = (T[]) new Object[Prime.nextPrime(DEFAULT_CAPACITY)];
        this.hashFamily = hashFamily;
        hashFamily.generateNewHashFunction();
        hashFamily.generateNewHashFunction();
    }

    public boolean contains(T t){
        int functionCount = hashFamily.getFunctionCount();
        for (int i = 0; i < functionCount; i++) {
            int index = myHash(t, i);
            if (dataArray[index] != null && dataArray[index].equals(t)){
                return true;
            }
        }
        return false;
    }

    public void remove(T t){
        int functionCount = hashFamily.getFunctionCount();
        for (int i = 0; i < functionCount; i++) {
            int index = myHash(t, i);
            if (dataArray[index] != null && dataArray[index].equals(t)){
                dataArray[index] = null;
            }
        }
    }

    public void insert(T t){
        if (contains(t)){
            return;
        }

        if (currentSize >= dataArray.length * DEFAULT_FACTOR){
            expand();
        }

        while (true){
            for (int j = 0; j < 100; j++) {
                //查找 有空的就直接插入并返回 唯一的返回点
                int functionCount = hashFamily.getFunctionCount();
                for (int i = 0; i < functionCount; i++) {
                    int index = myHash(t, i);
                    if (dataArray[index] == null){
                        dataArray[index] = t;
                        return;
                    }
                }
                //到此处说明 所有可能的位置都有值 需要随机找个位置替换
                //替换的位置 与上次替换位置不相同为佳
                int replacePosition;
                int i = 0;
                do {
                    replacePosition = myHash(t, random.nextInt(hashFamily.getFunctionCount()));
                }while (replacePosition == lastReplacePosition && i++ < 5);

                T oldData = dataArray[replacePosition];
                dataArray[replacePosition] = t;
                t = oldData;
            }
            //如果100次还没成功找到位置，则需要增加散列函数
            //增加了散列函数次数多了则需要 扩展数组大小
            if (rehashFrequency > ALLOWED_REHASH_FREQUENCY){
                expand();
                rehashFrequency = 0;
            }else {
                rehash();
            }
        }

    }

    /**
     * 数组长度不变 新增hash函数
     *
     */
    public void rehash(){
        hashFamily.generateNewHashFunction();
        rehash(dataArray.length);
        rehashFrequency++;
    }

    /**
     * hash函数不变 扩展数组的大小
     */
    public void expand(){
        rehash(dataArray.length * 2);
    }

    @SuppressWarnings("all")
    private void rehash(int newArraySize){
        T[] oldArray = this.dataArray;
        dataArray = (T[])new Object[Prime.nextPrime(newArraySize)];
        currentSize = 0;
        for (T t : oldArray){
            if (t != null){
                insert(t);
            }
        }
    }

    private int myHash(T t, int functionIndex){
        int hash = hashFamily.hash(t, functionIndex);
        int index = hash % dataArray.length;
        if (index < 0 ){
            index = index + dataArray.length;
        }
        if (index >= dataArray.length){
            index = index - dataArray.length;
        }
        return index;
    }

    public static void main(String[] args){
        long l = System.nanoTime();
        CuckooHashTable<String> cuckooHashTable = new CuckooHashTable<>(new StringHashFamily());
        for (int i = 0; i < 220000; i++) {
            cuckooHashTable.insert(String.valueOf(i));
        }
        System.out.println(System.nanoTime() - l);
    }

}
