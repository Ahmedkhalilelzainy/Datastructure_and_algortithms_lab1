package RB_Tree;

import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class RBTest {
    RB<Integer> tree = new RB<>();


    @Test
    void test1(){
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        assertEquals(2, tree.getHeight());
        assertTrue(tree.search(4) == true);
        assertTrue(tree.search(0) == false);
        assertTrue( tree.callBalance() );

    }
    @Test
    void test2(){

        // RR rotate test.
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        assertEquals(3, tree.getSize());
        assertEquals(1, tree.getHeight());

        // leaf red deletion test
        tree.delete(10);
        assertEquals(1, tree.getHeight());
        assertEquals(2, tree.getSize());


        // RL rotate test
        tree.insert(15);
        assertEquals(1, tree.getHeight());
        assertTrue( tree.callBalance() );

    }

    @Test
    void test3(){
        assertTrue(tree.insert(12));
        assertTrue(tree.insert(5));
        assertTrue(tree.insert(15));
        assertTrue(tree.insert(3));
        assertTrue(tree.insert(10));
        assertTrue(tree.insert(13));
        assertTrue(tree.insert(17));
        assertTrue(tree.insert(4));
        assertTrue(tree.insert(7));
        assertTrue(tree.insert(11));
        assertTrue(tree.insert(14));
        assertTrue(tree.insert(6));
        assertTrue(tree.insert(8));
        assertTrue(tree.delete(10));
        assertTrue(tree.delete(12));
        assertTrue(tree.insert(10));
        assertFalse(tree.insert(13));
        assertTrue(tree.delete(4));
        assertTrue( tree.callBalance() );
    }
    @Test
    void test4(){
        tree.insert(10);
        tree.insert(18);
        tree.insert(7);
        tree.insert(15);

        // check case uncle and parent are red.
        assertEquals(4, tree.getSize());
        assertEquals(Color.BLACK, tree.getRoot().getColor());
        assertEquals(Color.BLACK, tree.getRoot().getLeftChild().getColor());
        assertEquals(Color.BLACK, tree.getRoot().getRightChild().getColor());
        assertEquals(Color.RED, tree.getRoot().getRightChild().getLeftChild().getColor());
        assertEquals(2, tree.getHeight());

        tree.insert(16);

        // check LR rotate
        assertEquals(5, tree.getSize());
        assertEquals(2, tree.getHeight());

        tree.insert(30);
        tree.insert(25);
        tree.insert(40);

        // case propagate red node to up node then rotate case.
        assertEquals(3, tree.getHeight());
        assertEquals(8, tree.getSize());

        tree.insert(60);
        tree.insert(2);
        tree.insert(1);
        tree.insert(70);

        assertEquals(4, tree.getHeight());
        assertEquals(12, tree.getSize());
        assertTrue( tree.callBalance() );

    }
    @Test
    public void test5() {

        tree.insert(50);
        tree.insert(25);
        tree.insert(75);
        tree.insert(10);
        tree.insert(30);
        tree.insert(60);
        tree.insert(80);
        tree.insert(5);
        tree.insert(15);
        tree.insert(27);
        tree.insert(33);
        tree.insert(55);
        tree.insert(65);
        tree.insert(85);
        tree.insert(3);
        tree.insert(7);
        tree.insert(13);
        tree.insert(17);
        tree.insert(28);
        tree.insert(32);
        tree.insert(58);
        tree.insert(62);
        tree.insert(78);
        tree.insert(82);
        tree.insert(90);

        // Deletion of a leaf node
        tree.delete(3);
        assertFalse(tree.search(3));

        // Deletion of a node with one child
        tree.delete(55);
        assertFalse(tree.search(55));
        assertTrue(tree.search(58));
        assertTrue(tree.search(60));

        // Deletion of a node with two children
        tree.delete(75);
        assertFalse(tree.search(75));
        assertTrue(tree.search(80));
        assertTrue(tree.search(85));

        // Deletion of the root node
        tree.delete(50);
        assertFalse(tree.search(50));
        assertEquals(Integer.valueOf(58), tree.getRoot().getKey());

        // Deletion of a non-existent node
        assertFalse(tree.delete(100));
        assertTrue( tree.callBalance() );
    }
    @Test
    public void test6() {

        tree.insert(50);
        tree.insert(25);
        tree.insert(75);
        tree.insert(10);
        tree.insert(30);
        tree.insert(60);
        tree.insert(80);
        tree.insert(5);
        tree.insert(15);
        tree.insert(27);
        tree.insert(33);
        tree.insert(55);
        tree.insert(65);
        tree.insert(85);

        tree.delete(25);
        assertFalse(tree.search(25));
        assertTrue(tree.search(27));
        assertTrue(tree.search(30));

        tree.insert(70);
        tree.insert(65);
        tree.insert(68);
        tree.delete(70);
        assertFalse(tree.search(70));
        assertTrue(tree.search(68));
        assertTrue( tree.callBalance() );
    }
    @Test
    public void test7() {
        tree.insert(50);
        tree.insert(25);
        tree.insert(75);
        tree.delete(50);
        tree.delete(25);
        assertTrue(tree.getRoot().getKey()==75);
        tree.delete(75);
        assertTrue(tree.getRoot().getKey()==null);
        assertTrue( tree.callBalance() );
    }
    @Test
    public void test8() {

        // Test empty tree
        assertEquals(0, tree.getSize());
        assertEquals(-1, tree.getHeight());

        // Insert some nodes
        tree.insert(50);
        tree.insert(25);
        tree.insert(75);
        tree.insert(10);
        tree.insert(30);
        tree.insert(60);
        tree.insert(80);
        tree.insert(5);
        tree.insert(15);

        // Test size and height
        assertEquals(9, tree.getSize());
        assertEquals(3, tree.getHeight());

        // Delete some nodes
        tree.delete(60);
        tree.delete(80);
        tree.delete(5);

        // Test size and height
        assertEquals(6, tree.getSize());
        assertEquals(3, tree.getHeight());

        // Insert some more nodes
        tree.insert(20);
        tree.insert(55);
        tree.insert(70);

        // Test size and height
        assertEquals(9, tree.getSize());
        assertEquals(3, tree.getHeight());

        // Delete some more nodes
        tree.delete(10);
        tree.delete(25);
        tree.delete(75);

        // Test size and height
        assertEquals(6, tree.getSize());
        assertEquals(2, tree.getHeight());
        assertTrue(tree.callBalance());
    }

}