package RB_Tree;

import AVL_Tree.ITree;

public class RB<T extends Comparable<T>> implements ITree<T> {
    private RBNode<T> root;
    private int size;
    private complementary function;
    private int treeHeight;

    public RB(){
        root = null;
        size = 0;
        treeHeight = -1;
        function = complementary.getInstance();
    }

    public boolean search(T valueNeeded){
        return function.search(valueNeeded, root);
    }

    private void treeTraversal(RBNode<T> currentNode, String direction){
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

    public boolean insert(T valueInserted){
        // need return type(boolean) ... implementation.
        int oldSize = size;
        root = function.insert(valueInserted, root, null, this);
        function.checkBalance(root);
        if( oldSize == size ){
            return false;
        } else {
            updateHeight();
            return true;
        }
    }

    public boolean delete(T val){
        boolean isDeleted = function.deleteNode(val,root,this);
        if( !isDeleted ){
            return false;
        } else {
            decrementSize();
            updateHeight();
            return true;
        }
    }

    public int getSize(){
        return this.size;
    }
    public void incrementSize() {
        size++;
    }
    public void decrementSize() {
        size--;
    }

    private int updateHeight(){
        if( size == 0 )
            return treeHeight = -1;
        else {
            return treeHeight = function.updateHeight(root);
        }
    }
    public int getHeight(){
        return this.treeHeight;
    }
    public void transplant(RBNode<T> nodeTobeDeleted,RBNode<T> replacement){
        if(nodeTobeDeleted.getParent() == null)
            root = replacement;
        else if (nodeTobeDeleted == nodeTobeDeleted.getParent().getLeftChild())
            nodeTobeDeleted.getParent().setLeftChild(replacement);
        else
            nodeTobeDeleted.getParent().setRightChild(replacement);
        if(replacement != null)
        replacement.setParent(nodeTobeDeleted.getParent());
    }

}
