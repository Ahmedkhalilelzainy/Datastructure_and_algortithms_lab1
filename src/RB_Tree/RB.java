package RB_Tree;

import AVL_Tree.ITree;

public class RB<T extends Comparable<T>> implements ITree<T> {
    private RBNode<T> root;
    private int size;
    private complementary function;

    public RB(){
        root = null;
        size = 0;
        function = complementary.getInstance();
    }

    public boolean search(T valueNeeded){
        return function.search(valueNeeded, root);
    }

    public boolean insert(T valueInserted){
        // need return type(boolean) ... implementation.
        root = function.insert(valueInserted, root, null, root);
        function.checkBalance(root);
        return true;
    }

    public void treeTraversal(RBNode<T> currentNode, String direction){
        // base case
        if( currentNode == null ){
            return;
        }

        treeTraversal( currentNode.getLeftChild(), "l" );

        // work
        System.out.println( currentNode.getKey() + ", my parent is: " + ((currentNode.getParent() != null)?  currentNode.getParent().getKey(): null ) + ", " + direction + ", " + currentNode.getColor() );

        treeTraversal( currentNode.getRightChild(), "r" );

    }

    public RBNode<T> getRoot() {
        return this.root;
    }
    public boolean delete(T val){
        return function.deleteNode(val,root,this);
    }

    public int size(){
        return size;
    }
    public int height(){
        return 0;
    }
    public void transplant(RBNode<T>nodeTobeDeleted,RBNode<T>replacement){
        if(nodeTobeDeleted.getParent()==null)
            root=replacement;
        else if (nodeTobeDeleted==nodeTobeDeleted.getParent().getLeftChild())
            nodeTobeDeleted.getParent().setLeftChild(replacement);
        else
            nodeTobeDeleted.getParent().setRightChild(replacement);
        if(replacement!=null)
        replacement.setParent(nodeTobeDeleted.getParent());
    }
}
