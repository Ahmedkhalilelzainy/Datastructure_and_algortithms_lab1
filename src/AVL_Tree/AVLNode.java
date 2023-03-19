package AVL_Tree;

public class AVLNode<T extends Comparable<T>>{
    private T key;
    private AVLNode<T> left,right;
    private int height;
    public AVLNode(T val){
        key = val;
        left=right=null;
        height = 1;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public AVLNode<T> getLeft() {
        return left;
    }

    public void setLeft(AVLNode<T> left) {
        this.left = left;
    }

    public AVLNode<T> getRight() {
        return right;
    }

    public void setRight(AVLNode<T> right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void print(){
        if(this.left!=null)
            this.left.print();
        System.out.println(this.key);
        if(this.right!=null)
            this.right.print();
    }

}
