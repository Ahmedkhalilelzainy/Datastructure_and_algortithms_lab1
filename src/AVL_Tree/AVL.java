package AVL_Tree;

public class AVL<T extends Comparable<T>>{
    private AVLNode<T> root = null;
    private int size = 0;

    public AVLNode<T> getRoot() {
        return root;
    }

    public void setRoot(AVLNode<T> root) {
        this.root = root;
    }

    public int height(AVLNode<T> node){
        if(node == null)
            return 0;
        return node.getHeight();
    }
    public int balance(AVLNode<T> node){
        if(node == null)
            return 0;
        return height(node.getLeft()) - height(node.getRight());
    }

    public AVLNode<T> leftRotate(AVLNode<T> node){
        //the new root
        AVLNode<T> r = node.getRight();
        r.setLeft(node);
        node.setRight(r.getLeft());
        //update heights
        node.setHeight(Math.max(height(node.getLeft()),height(node.getRight())) + 1);
        r.setHeight(Math.max(height(r.getLeft()),height(r.getRight())) + 1);
        return r;
    }

    public AVLNode<T> rightRotate(AVLNode<T> node){
        //the new root
        AVLNode<T> r = node.getLeft();
        r.setRight(node);
        node.setLeft(r.getRight());
        //update heights
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
        r.setHeight(Math.max(height(r.getLeft()),height(r.getRight())) + 1);
        return r;
    }
    public void insert(T key){
        root=insertRec(key,root);
    }
    public AVLNode<T> insertRec(T val, AVLNode<T> root){
        //height will be updated during insertion
        //size will be updated during insertion
        if(root==null){
            root = new AVLNode<>(val);
            return root;
        }
        else if(root.getKey().compareTo(val) > 0)
           root.setLeft(insertRec(val,root.getLeft()));
        else if(root.getKey().compareTo(val) < 0)
           root.setRight(insertRec(val,root.getRight()));
        return root;
    }
    public AVLNode<T> search(T val, AVLNode<T> tempRoot){
        if(tempRoot == null || tempRoot.getKey().compareTo(val) == 0)
            return tempRoot;
        if(tempRoot.getKey().compareTo(val) > 0){
            return search(val, tempRoot.getLeft());
        }
        return search(val, tempRoot.getRight());
    }

    //function to get the minimum value of the subtree rooted at root
    private T minValue(AVLNode<T> root){
        T min = root.getKey();
        while (root.getLeft() != null){
            root = root.getLeft();
            min = root.getKey();
        }
        return min;
    }
    public void delete(T val){
    }
    public AVLNode<T> deleteRec(T val, AVLNode<T> root){
        return root;
    }

    public int getSize(){
        return size;
    }


    public void treeTraversal(AVLNode<T> currentNode){
        // base case
        if( currentNode == null ){
            return;
        }

        treeTraversal(currentNode.getLeft());

        // work
        System.out.println( currentNode.getKey() );

        treeTraversal(currentNode.getRight());

    }


}
