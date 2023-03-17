public class AVL<T extends Comparable<T>>{
    private Node root = null;

    public int height(Node node){
        if(node == null)
            return 0;
        return node.getHeight();
    }
    public int balance(Node node){
        if(node == null)
            return 0;
        return height(node.getLeft()) - height(node.getRight());
    }

    public Node leftRotate(Node node){
        //the new root
        Node r = node.getRight();
        r.setLeft(node);
        node.setRight(r.getLeft());
        //update heights
        node.setHeight(Math.max(height(node.getLeft()),height(node.getRight())) + 1);
        r.setHeight(Math.max(height(r.getLeft()),height(r.getRight())) + 1);

        return r;
    }

    public Node rightRotate(Node node){
        //the new root
        Node r = node.getLeft();
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
    public Node insertRec(T val,Node root){
        //height will be updated during insertion
        if(root==null){
            root = new Node(val);
            return root;
        }
        else if(root.getKey().compareTo(val) > 0)
           root.setLeft(insertRec(val,root.getLeft()));
        else if(root.getKey().compareTo(val) < 0)
           root.setRight(insertRec(val,root.getRight()));
        return root;
    }

    public Node delete(Node node){
        return node;
    }





}
