import AVL_Tree.AVL;
import RB_Tree.Direction;
import RB_Tree.RBNode;
import RB_Tree.complementary;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        AVL<Integer> usedTree = new AVL<Integer>();

        for (int i = 0; i < 10; i++) {
            usedTree.insert(i);
        }
        
        usedTree.treeTraversal( usedTree.getRoot() );
        System.out.println( usedTree.height( usedTree.getRoot() ) );

        // testing
        RBNode<String> node1 = new RBNode<>("10", Direction.LEFT), node2 = new RBNode<>("20", Direction.RIGHT), node3 = null;
        node2.setParent( node1 );
        complementary<String> functions = complementary.getInstance();
        functions.leftRotate(node2);
        try {
            node1.setParent( node3 );
            System.out.println("in try");
        }catch (Exception e){
            System.out.println( "in catch" );
        }
    }
}