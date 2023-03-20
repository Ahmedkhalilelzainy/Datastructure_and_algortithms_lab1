package RB_Tree;

public class RB<T extends Comparable<T>> {
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

    public void insert(T valueInserted){
        // need return type(boolean) ... implementation.
        root = function.insert(valueInserted, root);
        function.checkBalance(root);
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
}
