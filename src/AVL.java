public class AVL<T extends Comparable<T>>{
    private Node<T> root = null;
    private int size = 0;

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }

    public int height(Node<T> node){
        if(node == null)
            return 0;
        return node.getHeight();
    }
    public int balance(Node<T> node){
        if(node == null)
            return 0;
        return height(node.getLeft()) - height(node.getRight());
    }

    public Node<T> leftRotate(Node<T> node){
        //the new root
        Node<T> r = node.getRight();
        r.setLeft(node);
        node.setRight(r.getLeft());
        //update heights
        node.setHeight(Math.max(height(node.getLeft()),height(node.getRight())) + 1);
        r.setHeight(Math.max(height(r.getLeft()),height(r.getRight())) + 1);
        return r;
    }

    public Node<T> rightRotate(Node<T> node){
        //the new root
        Node<T> r = node.getLeft();
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
    public Node<T> insertRec(T val,Node<T> root){
        //height will be updated during insertion
        //size will be updated during insertion
        if(root==null){
            root = new Node<>(val);
            return root;
        }
        else if(root.getKey().compareTo(val) > 0)
           root.setLeft(insertRec(val,root.getLeft()));
        else if(root.getKey().compareTo(val) < 0)
           root.setRight(insertRec(val,root.getRight()));
        return root;
    }
    public Node<T> search(T val, Node<T> tempRoot){
        if(tempRoot == null || tempRoot.getKey().compareTo(val) == 0)
            return tempRoot;
        if(tempRoot.getKey().compareTo(val) > 0){
            return search(val, tempRoot.getLeft());
        }
        return search(val, tempRoot.getRight());
    }

    //function to get the minimum value of the subtree rooted at root
    private T minValue(Node<T> root){
        T min = root.getKey();
        while (root.getLeft() != null){
            root = root.getLeft();
            min = root.getKey();
        }
        return min;
    }
    public void delete(T val){
    }
    public Node<T> deleteRec(T val, Node<T> root){
        return root;
    }

    public int getSize(){
        return size;
    }





}
