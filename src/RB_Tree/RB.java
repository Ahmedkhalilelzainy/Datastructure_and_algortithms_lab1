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
        root = function.insert(valueInserted, root, Direction.NONE);
    }
}
