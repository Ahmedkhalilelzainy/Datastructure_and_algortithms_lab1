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
        int result = key.compareTo(currentNode.getKey());
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

    public RBNode<T> rightRotate(RBNode<T> currentNode) {
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
        return currentNode;
    }

    public RBNode<T> insert(T valueInserted, RBNode<T> currentNode, RBNode<T> controllerNode, RBNode<T> rootNode) {
        if( currentNode == null ){
            RBNode<T> newNode = new RBNode<T>(valueInserted);
            if( rootNode != null ){
                // node insertion
                newNode.setParent( controllerNode );
                if( valueInserted.compareTo( controllerNode.getKey() ) > 0 ){
                    controllerNode.setRightChild( newNode );
                } else {
                    controllerNode.setLeftChild( newNode );
                } // noooooo insertion for existing value.
                checkBalance(newNode);
                return rootNode;
            } else {
                return newNode;
            }
        }

        int result = currentNode.getKey().compareTo(valueInserted);
        if (result < 0) {
            insert(valueInserted, currentNode.getRightChild(), currentNode, rootNode);
        } else if( result > 0 ) {
            insert(valueInserted, currentNode.getLeftChild(), currentNode, rootNode);
        } else {
            // already exists.
            System.out.println( "already exists." );
        }

        // check balance part.
        System.out.println( "iam before balance with value; " + currentNode.getKey() );

        checkBalance(currentNode);
        if( rootNode.getParent() != null )return rootNode.getParent();
        else return rootNode;
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
