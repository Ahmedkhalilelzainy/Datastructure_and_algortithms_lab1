package RB_Tree;

public class complementary<T extends Comparable<T>> {
    // singleton Design pattern
    private static complementary instance;
    private static boolean isInstantiated = false;
    private complementary(){}
    public static complementary getInstance(){
        if(!isInstantiated){
            isInstantiated = true;
            return instance = new complementary();
        } else {
            return instance;
        }
    }
    // end singleton Design pattern

    public boolean search(T key, RBNode<T> currentNode){
        if( currentNode == null ){
            return false;
        }
        int result = currentNode.getKey().compareTo(key);
        if ( result > 0 ) {
            return search(key, currentNode.getRightChild());
        } else if (result < 0) {
            return search(key, currentNode.getLeftChild());
        } else {
           return true;
        }
    }


    // wrong because of position of parent to grandpa.
    public void leftRotate(RBNode<T> currentNode){
        RBNode<T> oldGrandPa = currentNode.getParent().getParent();
        RBNode<T> oldParent = currentNode.getParent();

        // change intermediate value connections
        oldParent.setRightChild( currentNode.getLeftChild() );
        if( currentNode.getLeftChild() != null ){
            currentNode.getLeftChild().setParent( oldParent );
        }

        // change current node and its old parent connections
        currentNode.setLeftChild( oldParent );
        oldParent.setParent( currentNode );

        // change connections of old grand pa.
        currentNode.setParent( oldGrandPa );
        if( oldGrandPa != null ){
            oldGrandPa.setLeftChild( currentNode );
        }
    }


    public void rightRotate(RBNode<T> currentNode) {
        RBNode<T> oldGrandPa = currentNode.getParent().getParent();
        RBNode<T> oldParent = currentNode.getParent();


    }

    public RBNode<T> insert(T valueInserted, RBNode<T> currentNode,Direction myDir) {
        if( currentNode == null ){
            return new RBNode<T>(valueInserted, myDir);
        }

        int result = currentNode.getKey().compareTo(valueInserted);
        if (result > 0) {
            RBNode<T> rightChild = insert(valueInserted, currentNode.getRightChild(), Direction.RIGHT);
            // link between child and parent.
            currentNode.setRightChild( rightChild );
            rightChild.setParent( currentNode );
        } else if( result < 0 ) {
            RBNode<T> leftChild = insert(valueInserted, currentNode.getLeftChild(), Direction.LEFT);
            // link between child and parent.
            currentNode.setRightChild( leftChild );
            leftChild.setParent( currentNode );
        } else {
            // already exists.
        }

        // check balance part.
        return null;
    }
}
