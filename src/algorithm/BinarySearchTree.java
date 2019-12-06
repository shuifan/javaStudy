package algorithm;


import lombok.Data;

/**
 * 二叉查找树
 * 节点左侧子树所有节点均小于此节点
 * 节点右侧子树所有节点均大于此节点
 *
 * 树的操作 使用递归 很方便
 * @param <T>
 */
@Data
public class BinarySearchTree<T extends Comparable<? super T>> {

    private BinaryNode<T> root;

    public T findMin(){
        if (root == null){
            return null;
        }
        return findMin(root).data;
    }

    public T findMax(){
        if (root == null){
            return null;
        }
        return findMax(root).data;
    }

    public boolean contains(T t){
        return contains(t, root);
    }

    public void insert(T t){
        // 此处如果不把返回值赋值给 root 则无法初始化根节点
        root = insert(t, root);
    }

    public void remove(T t){
        //此处如果不把返回值赋值给 root 则删除最后一个节点时，无法设置 root 为 null
        root = remove(t, root);
    }

    public boolean isEmpty(){
        return root == null;
    }

    /**
     * 注意 返回值为 入参节点
     * r.left = insert(t, r.left)
     * @param t
     * @param r
     * @return
     */
    private BinaryNode<T> insert(T t, BinaryNode<T> r){
        if (r == null){
            return new BinaryNode<>(t);
        }
        int i = t.compareTo(r.data);
        if (i > 0){
            r.right =  insert(t, r.right);
        }else if (i < 0 ){
            r.left = insert(t, r.left);
        }
        return r;
    }

    /**
     * 注意返回值为 入参节点
     * 关键在于  r.left = remove(t, r.left)
     * @param t
     * @param r
     * @return
     */
    private BinaryNode<T> remove(T t, BinaryNode<T> r){
        if (r == null){
            return null;
        }
        int i = t.compareTo(r.data);
        if (i < 0){
            r.left = remove(t, r.left);
        }else if (i > 0){
            r.right = remove(t, r.right);
        }else{
            //有两个子节点的情况
            if (r.left != null && r.right != null){
                r.data = findMin(r.right).data;
                r.right = remove(r.data, r.right);
            }else {
                r = (r.left != null) ? r.left : r.right;
            }
        }
        return r;

    }

    private BinaryNode<T> findMin(BinaryNode<T> r){
        return r.left == null ? r : findMin(r.left);
    }

    private BinaryNode<T> findMax(BinaryNode<T> r){
        return r.right == null ? r : findMax(r.right);
    }

    private boolean contains(T t, BinaryNode<T> r){
        if (root == null){
            return false;
        }
        int i = t.compareTo(r.data);
        if (i > 0){
            return contains(t, r.right);
        }else if (i < 0 ){
            return contains(t, r.left);
        }else {
            return true;
        }
    }

    @Data
    private static class BinaryNode<T>{
        private T data;
        private BinaryNode<T> left;
        private BinaryNode<T> right;

        public BinaryNode(T data) {
            this.data = data;
        }

        public BinaryNode(T data, BinaryNode<T> left, BinaryNode<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }


    public static void main(String[] args){
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        binarySearchTree.insert(1);
        binarySearchTree.insert(2);
        binarySearchTree.insert(3);
        binarySearchTree.insert(4);
        binarySearchTree.insert(10);
        binarySearchTree.insert(9);
        binarySearchTree.insert(8);
        binarySearchTree.insert(7);
        System.out.println(binarySearchTree.findMax());
        System.out.println(binarySearchTree.findMin());
        System.out.println(binarySearchTree.contains(2));
        binarySearchTree.remove(1);
        binarySearchTree.remove(2);
        binarySearchTree.remove(3);
        binarySearchTree.remove(4);
    }

}
