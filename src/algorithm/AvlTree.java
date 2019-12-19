package algorithm;

import lombok.Data;

/**
 * avl树
 * 二叉查找树的问题在于 若是顺序插入的情况下 树会退化为链表 O(logn) -> O(n)
 * avl 树在 二叉查找树的基础上加入了平衡条件 ： 任意节点的左右子树高度差最多为 1
 * 注意点：
 * 1、记录每个节点的高度差
 * 2、平衡的方法为 如果当前节点不平衡了，则判断 左左和右右 单次旋转 左右和右左 两次旋转
 * 3、实际在 二叉查找树插入和删除代码的最后加入了 平衡方法
 * 4、计算高度的情况： 平衡完成后，要重新计算根节点的高度 旋转完成也要重新计算相关节点的高度
 */
@Data
public class AvlTree<T extends Comparable<? super T>> {

    private static int limitHeight = 1;

    private AvlNode<T> root;

    public void insert(T t){
        root = insert(t, root);
    }

    public void delete(T t){
        root = remove(t, root);
    }

    public void printTreeMidRoot(){
        printTreeMidRoot(root);
    }

    public void printTreeFirstRoot(){
        printTreeFirstRoot(root);
    }

    public void printTreeLastRoot(){
        printTreeLastRoot(root);
    }

    /**
     * 后序遍历  先遍历 左子树和右子树 在遍历当前元素
     * 叶子节点高度为 0 则 叶子节点的左右子节点高度为 -1
     * @param avlNode
     * @return
     */
    public int currentHeight(AvlNode<T> avlNode){
        if (avlNode == null){
            return -1;
        }else {
            return 1 + Math.max(currentHeight(avlNode.right), currentHeight(avlNode.left));
        }
    }

    /**
     * 中序遍历  左子节点 当前节点 右子节点
     */
    private void printTreeMidRoot(AvlNode<T> avlNode){
        if (avlNode != null){
            printTreeMidRoot(avlNode.left);
            System.out.println(avlNode.data + "height:" + currentHeight(avlNode));
            printTreeMidRoot(avlNode.right);
        }
    }

    /**
     * 先序遍历  当前节点 左节点 右节点
     * @param avlNode
     */
    private void printTreeFirstRoot(AvlNode<T> avlNode){
        if (avlNode != null){
            System.out.println(avlNode.data + "height:" + currentHeight(avlNode));
            printTreeFirstRoot(avlNode.left);
            printTreeFirstRoot(avlNode.right);
        }
    }

    /**
     * 左节点 右节点 当前节点
     * @param avlNode
     */
    private void printTreeLastRoot(AvlNode<T> avlNode){
        if (avlNode != null){
            printTreeLastRoot(avlNode.left);
            printTreeLastRoot(avlNode.right);
            System.out.println(avlNode.data + "height:" + currentHeight(avlNode));
        }
    }

    @Data
    private class AvlNode<T> {
        private T data;
        private AvlNode<T> left;
        private AvlNode<T> right;
        private int height;

        public AvlNode(T data) {
            this.data = data;
        }
    }

    private int height(AvlNode<T> t) {
        return t == null ? -1 : t.height;
    }

    /**
     * 和左孩子旋转 右旋
     *
     * @param t
     */
    private AvlNode<T> rotateWithLeftChild(AvlNode<T> t) {
        if (t == null) {
            return t;
        }
        AvlNode<T> k = t.left;
        t.left = k.right;
        k.right = t;
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        k.height = Math.max(height(k.left), height(k.right)) + 1;
        return k;
    }

    /**
     * 和右孩子旋转 左旋
     *
     * @param t
     */
    private AvlNode<T> rotateWithRightChild(AvlNode<T> t) {
        if (t == null) {
            return t;
        }
        AvlNode<T> k = t.right;
        t.right = k.left;
        k.left = t;
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        k.height = Math.max(height(k.left), height(k.right)) + 1;
        return k;
    }

    /**
     * 左右情况 两次旋转   先是 左孩子和 其右子节点 左旋，再当前节点和左孩子右旋
     * @param t
     */
    private AvlNode<T> doubleRotateWithLeftChild(AvlNode<T> t){
        t.left = rotateWithRightChild(t.left);
        return rotateWithLeftChild(t);
    }

    /**
     * 右左情况 两次旋转   先是 右孩子和 其左子节点 右旋，再当前节点和右孩子左旋
     * @param t
     */
    private AvlNode<T> doubleRotateWithRightChild(AvlNode<T> t){
        t.right = rotateWithLeftChild(t.right);
        return rotateWithRightChild(t);
    }

    private AvlNode<T> insert(T t, AvlNode<T> thisRoot){
        if (thisRoot == null){
            return new AvlNode<>(t);
        }
        int i = t.compareTo(thisRoot.data);
        if (i > 0){
            thisRoot.right = insert(t, thisRoot.right);
        }else if (i < 0){
            thisRoot.left = insert(t, thisRoot.left);
        }

        return balance(thisRoot);
    }

    private AvlNode<T> remove(T t, AvlNode<T> thisRoot){
        if (thisRoot == null){
            return null;
        }
        int i = t.compareTo(thisRoot.data);
        if (i > 0){
            thisRoot.right = remove(t, thisRoot);
        }else if (i < 0){
            thisRoot.left = remove(t, thisRoot);
        }else {
            //删除节点需要考虑下面节点的情况

            //左右都在的情况下 找右子树的最小节点替代自己，然后删除右子树的最小节点
            if (thisRoot.left != null && thisRoot.right != null){
                thisRoot.data = findMin(thisRoot.right).data;
                thisRoot.right = remove(thisRoot.data, thisRoot.right);
            }else {
                //最多一个节点的情况，直接用 子节点替换自己
                thisRoot = thisRoot.left == null ? thisRoot.right : thisRoot.left;
            }
        }
        return balance(thisRoot);
    }

    private AvlNode<T> findMin(AvlNode<T> thisRoot){
        return thisRoot.left == null ? thisRoot : findMin(thisRoot.left);
    }

    /**
     * 不管需不需要旋转 在插入、删除节点之后的平衡方法 每次都需要更新节点的高度
     * 不平衡 则 翻转并 维护高度
     * 平衡 则 不反转 但是 要 维护高度
     * @param t
     * @return
     */
    private AvlNode<T> balance(AvlNode<T> t){
        if (t == null){
            return t;
        }
        //判断出  左左 左右 右右 右左 的情况分别处理

        //左
        if ((height(t.left) - height(t.right)) > limitHeight){
            //左左
            if (height(t.left.left) >= height(t.left.right)){
                return rotateWithLeftChild(t);
            }else {
                //左右
                return doubleRotateWithLeftChild(t);
            }
            //右
        }else if ((height(t.right) - height(t.left)) > limitHeight ){
            //右右
            if (height(t.right.right) >= height(t.right.left)){
                return rotateWithRightChild(t);
            }else {
                //右左
                return doubleRotateWithRightChild(t);
            }
        }else {
            //上面旋转的情况会计算相关节点的高度
            //无需旋转的情况也 需要计算当前节点的高度
            //显然 当前节点的高度 为 左右节点的最大高度 + 1
            t.height = Math.max(height(t.left) , height(t.right)) + 1;
            return t;
        }

    }

    public static void main(String[] args){
        Integer[] data = new Integer[]{3,2,1,4,5,6,7,16,15,14,13,12,11,10,8,9};
        AvlTree<Integer> avlTree = new AvlTree<>();
        for (Integer i : data){
            avlTree.insert(i);
        }

        avlTree.printTreeMidRoot();
        System.out.println();
        avlTree.printTreeFirstRoot();
        System.out.println();
        avlTree.printTreeLastRoot();
    }

}
