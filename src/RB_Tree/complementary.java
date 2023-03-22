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

    private RBNode<T> getNode(T key,RBNode<T>currentNode){
        if( currentNode == null ){
            return null;
        }
        int result = key.compareTo(currentNode.getKey());
        if ( result > 0 ) {
            return getNode(key, currentNode.getRightChild());
        } else if (result < 0) {
            return getNode(key, currentNode.getLeftChild());
        } else {
            return currentNode;
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

    public RBNode<T> insert(T valueInserted, RBNode<T> currentNode, RBNode<T> controllerNode, RB RBClass) {
        if( currentNode == null ){
            RBClass.incrementSize();
            RBNode<T> newNode = new RBNode<T>(valueInserted);

            if( RBClass.getRoot() != null ){
                // node insertion
                newNode.setParent( controllerNode );
                if( valueInserted.compareTo( controllerNode.getKey() ) > 0 ){
                    controllerNode.setRightChild( newNode );
                } else {
                    controllerNode.setLeftChild( newNode );
                } // no insertion for existing value.
                checkBalance(newNode);
                return RBClass.getRoot();
            } else {
                return newNode;
            }
        }

        int result = currentNode.getKey().compareTo(valueInserted);
        if (result < 0) {
            insert(valueInserted, currentNode.getRightChild(), currentNode, RBClass);
        } else if( result > 0 ) {
            insert(valueInserted, currentNode.getLeftChild(), currentNode, RBClass);
        } else {
            // already exists.
            System.out.println( "already exists." );
            return RBClass.getRoot();

        }

        // check balance part.
//        System.out.println( "iam before balance with value; " + currentNode.getKey() );

        checkBalance(currentNode);
        if( RBClass.getRoot().getParent() != null )return RBClass.getRoot().getParent();
        else return RBClass.getRoot();
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

    public boolean deleteNode(T key,RBNode<T>currentNode,RB<T>usedTree){
       RBNode<T>nodeTobeDeleted=getNode(key,currentNode);
       RBNode<T>temp=new RBNode<T>(null);
       if(nodeTobeDeleted == null)return false;
        RBNode<T>replacement=nodeTobeDeleted;
        Color colorOfDeletedNode=nodeTobeDeleted.getColor();
        System.out.println(colorOfDeletedNode);
       //1st case
        if(nodeTobeDeleted.getLeftChild()==null){
           temp=nodeTobeDeleted.getRightChild();
            usedTree.transplant(nodeTobeDeleted,nodeTobeDeleted.getRightChild());
       }
        //2nd case
       else if(nodeTobeDeleted.getRightChild()==null){
          temp=nodeTobeDeleted.getLeftChild();
           usedTree.transplant(nodeTobeDeleted,nodeTobeDeleted.getLeftChild());
       }
       //3rd case
       else{
           replacement= minimum(nodeTobeDeleted.getRightChild());
             colorOfDeletedNode=replacement.getColor();
             if(replacement.getRightChild()!=null)
                temp=replacement.getRightChild();
             if(replacement.getParent()==nodeTobeDeleted){
                 temp.setParent(replacement);
             }
             else{
                 usedTree.transplant(replacement,replacement.getRightChild());
                 replacement.setRightChild(nodeTobeDeleted.getRightChild());
                 replacement.getRightChild().setParent(replacement);
                 temp.setParent(replacement.getParent());
             }
             usedTree.transplant(nodeTobeDeleted,replacement);
             replacement.setLeftChild(nodeTobeDeleted.getLeftChild());
             replacement.getLeftChild().setParent(replacement);
             replacement.setColor(nodeTobeDeleted.getColor());
       }
        System.out.println(colorOfDeletedNode);
       if(colorOfDeletedNode==Color.BLACK)
            delete_fixup(temp,usedTree);
       return true;
    }

    public void delete_fixup(RBNode<T> currentNode,RB tree){
//        System.out.println(currentNode.getParent().getKey());
        RBNode<T>sibling;
                while (currentNode!=tree.getRoot()&&currentNode.getColor()==Color.BLACK){
            if(currentNode==currentNode.getParent().getLeftChild()){
                sibling=currentNode.getParent().getRightChild();
               //case 1
                if(sibling.getColor()==Color.RED){
                    sibling.setColor(Color.BLACK);
                    currentNode.getParent().setColor(Color.RED);
                    leftRotate(currentNode.getParent());
                    sibling=currentNode.getParent().getRightChild();
                }
                //case2
                if(sibling.getLeftChild().getColor()==Color.BLACK&&sibling.getRightChild().getColor()==Color.BLACK){
                sibling.setColor(Color.RED);
                currentNode =currentNode.getParent();
                }
                else{
                    //case3
                    if(sibling.getRightChild().getColor()==Color.BLACK){
                        sibling.getLeftChild().setColor(Color.BLACK);
                        sibling.setColor(Color.RED);
                        rightRotate(sibling);
                        sibling=currentNode.getParent().getRightChild();
                    }
                    //case4
                    sibling.setColor(currentNode.getParent().getColor());
                    currentNode.getParent().setColor(Color.BLACK);
                    sibling.getRightChild().setColor(Color.BLACK);
                    leftRotate(currentNode.getParent());
                    currentNode=tree.getRoot();
                }

            }
            else{
                sibling=currentNode.getParent().getLeftChild();
                if(sibling.getColor()==Color.RED){
                    //case1
                    sibling.setColor(Color.BLACK);
                    currentNode.getParent().setColor(Color.RED);
                    rightRotate(currentNode.getParent());
                    sibling=currentNode.getParent().getLeftChild();
                }
                if(sibling.getLeftChild().getColor()==Color.BLACK&&sibling.getRightChild().getColor()==Color.BLACK){
                //case 2
                    sibling.setColor(Color.RED);
                    currentNode=currentNode.getParent();
                }
                else {
                    if(sibling.getLeftChild().getColor()==Color.BLACK){
                     sibling.getRightChild().setColor(Color.BLACK);
                     sibling.setColor(Color.RED);
                     leftRotate(sibling);
                     sibling=currentNode.getParent().getLeftChild();
                    }
                    sibling.setColor(currentNode.getParent().getColor());
                    currentNode.getParent().setColor(Color.BLACK);
                    sibling.getLeftChild().setColor(Color.BLACK);
                    rightRotate(currentNode.getParent());
                    currentNode=tree.getRoot();
                }

            }
        }
        currentNode.setColor(Color.BLACK);
    }
    public RBNode<T> minimum(RBNode<T> currentNode){
        while (currentNode.getLeftChild()!=null){
            currentNode=currentNode.getLeftChild();
        }
        return currentNode;
    }

    public RBNode<T> maximum(RBNode<T> currentNode){
        while (currentNode.getRightChild() != null){
            currentNode = currentNode.getRightChild();
        }
        return currentNode;
    }

    public int updateHeight( RBNode<T> currentNode ) {
        if( currentNode == null ){
            return -1;
        }
        int leftHeight = updateHeight( currentNode.getLeftChild() );
        int rightHeight = updateHeight( currentNode.getRightChild() );
        currentNode.setHeight( Math.max(leftHeight + 1, rightHeight + 1) );
        return currentNode.getHeight();
    }
}
