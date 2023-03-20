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

        // change connections of old grand pa.
        currentNode.setParent( oldGrandPa );
        if( oldGrandPa != null ){
            if( oldGrandPa.getLeftChild() != null && oldGrandPa.getLeftChild() == oldParent ){
                oldGrandPa.setLeftChild( currentNode );
            } else {
                oldGrandPa.setRightChild( currentNode );
            }
        }

        // change intermediate value connections
        oldParent.setRightChild( currentNode.getLeftChild() );
        if( currentNode.getLeftChild() != null ){
            currentNode.getLeftChild().setParent( oldParent );
        } // ok

        // change current node and its old parent connections
        currentNode.setLeftChild( oldParent );
        oldParent.setParent( currentNode );

    }

    public void rightRotate(RBNode<T> currentNode) {
        RBNode<T> oldGrandPa = currentNode.getParent().getParent();
        RBNode<T> oldParent = currentNode.getParent();

        // change connections of old grand pa.
        currentNode.setParent( oldGrandPa );
        if( oldGrandPa != null ){
            if( oldGrandPa.getLeftChild() != null && oldGrandPa.getLeftChild() == oldParent ){
                oldGrandPa.setLeftChild( currentNode );
            } else {
                oldGrandPa.setRightChild( currentNode );
            }
        }

        // change intermediate value connections
        oldParent.setLeftChild( currentNode.getRightChild() );
        if( currentNode.getRightChild() != null ){
            currentNode.getRightChild().setParent( oldParent );
        } //

        // change current node and its old parent connections
        currentNode.setRightChild( oldParent );
        oldParent.setParent( currentNode );
    }

    public RBNode<T> insert(T valueInserted, RBNode<T> currentNode) {
        if( currentNode == null ){
            RBNode<T> newNode = new RBNode<T>(valueInserted);
            return newNode;
        }

        int result = currentNode.getKey().compareTo(valueInserted);
        if (result < 0) {
            RBNode<T> rightChild = insert(valueInserted, currentNode.getRightChild());

            // link between child and parent.
            currentNode.setRightChild( rightChild );
            rightChild.setParent( currentNode );
        } else if( result > 0 ) {
            RBNode<T> leftChild = insert(valueInserted, currentNode.getLeftChild());

            // link between child and parent.
            currentNode.setLeftChild( leftChild );
            leftChild.setParent( currentNode );
        } else {
            // already exists.
        }

        // check balance part.

        checkBalance(currentNode);

        return currentNode;
    }

    public void checkBalance(RBNode<T> currentNode) {
        if( currentNode.getColor() == Color.BLACK ){
            return;
        }
        else if( currentNode.getParent() == null && currentNode.getColor() == Color.RED ){
            currentNode.setColor( Color.BLACK );
        }
        else if( currentNode.getColor() == Color.RED && currentNode.getParent().getColor() == Color.BLACK ){
            return;
        }
        else if( currentNode.getColor() == Color.RED
                && currentNode.getParent().getColor() == Color.RED
                && uncleNode( currentNode ) != null
                && uncleNode(currentNode).getColor() == Color.RED ){

            currentNode.getParent().setColor( Color.BLACK );
            uncleNode( currentNode ).setColor( Color.BLACK );
            currentNode.getParent().getParent().setColor( Color.RED );

        }
        else if( currentNode.getColor() == Color.RED
                && currentNode.getParent().getColor() == Color.RED
                && (uncleNode( currentNode ) == null || uncleNode(currentNode).getColor() == Color.BLACK ) ){

            boolean startedByLeft, endedByLeft;
            startedByLeft = ( currentNode.getParent().getParent().getLeftChild() == currentNode.getParent() );
            endedByLeft   = ( currentNode.getParent().getLeftChild() == currentNode );

            // four cases LL, RR, LR and RL
            if( startedByLeft && endedByLeft ){
                rightRotate( currentNode.getParent() );
                currentNode.getParent().setColor( Color.BLACK );
                currentNode.getParent().getRightChild().setColor( Color.RED );
            } else if( !startedByLeft && !endedByLeft ){
                leftRotate( currentNode.getParent() );
                currentNode.getParent().setColor( Color.BLACK );
                currentNode.getParent().getLeftChild().setColor( Color.RED );
            } else if( startedByLeft && !endedByLeft ){
                leftRotate( currentNode );
                rightRotate( currentNode );
                currentNode.setColor( Color.BLACK );
                currentNode.getLeftChild().setColor( Color.RED );
                currentNode.getRightChild().setColor( Color.RED );
            } else {
                rightRotate( currentNode );
                leftRotate( currentNode );
                currentNode.setColor( Color.BLACK );
                currentNode.getLeftChild().setColor( Color.RED );
                currentNode.getRightChild().setColor( Color.RED );
            }
        }
        else {
            System.out.println( "unknown case in insertion cases" );
        }

    }

    private RBNode uncleNode(RBNode<T> currentNode) {
        RBNode<T> grandPa = currentNode.getParent().getParent();
        RBNode<T> parent = currentNode.getParent();

        if( grandPa.getLeftChild() == parent ){
            return grandPa.getRightChild();
        }
        else {
            return grandPa.getLeftChild();
        }
    }


}
