package algorithm;

import lombok.Data;

/**
 * 不相交集的 union find 算法
 *
 * 数组的每个位置 要么值为 -1 表示此处为根节点， 要么记录的是当前树的高度或者大小，合并操作将小规模的合并到大规模去
 */
@Data
public class DisjSets {

    private int[] setArray;

    public DisjSets(int capacity) {
        setArray = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            setArray[i] = -1;
        }
    }

    public int find(int i){
        if (setArray[i] < 0){
            return i;
        }else {
            //路径压缩 把整个链上的所有节点的父节点变为根节点 利于下次find操作
            return setArray[i] = find(setArray[i]);
        }
    }

    public void union(int root1, int root2){
        //高度不同 小的合并到大的  大的高度不用变
        //注意 高度用的负值记录的 小的反而高度更大
        if (setArray[root1] < setArray[root2]){
            setArray[root2] = root1;
        }else {
            //root1 高度大于等于 root2的情况 root1做根
            //相等的情况下 要维护 root1的高度
            if (setArray[root1] == setArray[root2]){
                setArray[root2]--;
            }
            setArray[root1] = root2;
        }
    }

    public static void main(String[] args){
        DisjSets disjSets = new DisjSets(10);
        disjSets.union(1,2);
        disjSets.union(3,4);
        disjSets.union(5,6);
        disjSets.union(7,8);
        int index = disjSets.find(5);
        int index1 = disjSets.find(9);
        disjSets.union(index, index1);
        disjSets.union(4,6);
        System.out.println(1);
    }


}
