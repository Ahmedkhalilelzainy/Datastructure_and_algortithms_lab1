import AVL_Tree.AVL;
import RB_Tree.RB;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        AVL<Integer> usedTree = new AVL<Integer>();

        for (int i = 0; i < 10; i++) {
            usedTree.insert(i);
        }


        // testing
//        RBNode<String> node1 = new RBNode<>("10"), node2 = new RBNode<>("20"), node3 = null;
//        node2.setParent( node1 );
//        complementary<String> functions = complementary.getInstance();
//        functions.leftRotate(node2);
//        try {
//            node1.setParent( node3 );
//            System.out.println("in try");
//        }catch (Exception e){
//            System.out.println( "in catch" );
//        }

        RB<Integer> usedTree1 = new RB<>();
        for(int i = 0;i < 10;i ++){
            usedTree1.insert( i );
        }


        usedTree1.treeTraversal( usedTree1.getRoot(), "root" );
    }
}