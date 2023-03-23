package AVL_Tree;

import org.junit.jupiter.api.Test;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class AVLTest {
    AVL<Integer> tree = new AVL<>();
    AVL<Integer> T1 = new AVL<>();

    @Test
    void test1(){
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        assertEquals(2, tree.getHeight());
        assertTrue(tree.search(4) == true);
        assertTrue(tree.search(0) == false);
    }

    @Test
    void test2(){
        assertTrue(tree.search(10) == false);
        assertTrue(tree.delete(100) == false);
        assertEquals(-1, tree.getHeight());
        assertEquals(0, tree.getSize());
        assertTrue(tree.insert(-10) == true);
        assertEquals(1, tree.getSize());
        assertTrue(tree.search(10) == false);
        assertTrue(tree.delete(-10) == true);
        assertEquals(0, tree.getSize());
    }

    @Test
    void test3(){
        tree.insert(50);
        tree.insert(40);
        tree.insert(60);
        tree.insert(30);
        tree.insert(45);
        tree.insert(49);
        assertEquals(2, tree.getHeight());
    }

    @Test
    void test4(){
        tree.insert(3);
        tree.insert(4);
        tree.delete(4);
        assertEquals(1, tree.getSize());
        tree.insert(7);
        tree.insert(4);
        assertEquals(3, tree.getSize());
        tree.insert(4);
        assertEquals(3, tree.getSize());
    }

    @Test
    void test5(){
        for(int i = 0;i < 10; i++){
            tree.insert(i);
        }
        assertEquals(3,tree.getHeight());
        assertEquals(10,tree.getSize());
        tree.delete(4);
        tree.delete(6);
        assertTrue(tree.delete(6) == false);
        assertTrue(tree.delete(10) == false);
        tree.delete(9);
        assertEquals(2,tree.getHeight());
        assertEquals(7,tree.getSize());
        assertTrue(tree.search(6) == false);
        tree.insert(6);
        assertTrue(tree.search(6) == true);
        assertEquals(8,tree.getSize());
        assertEquals(3,tree.getHeight());
    }

    @Test
    void test6(){
        for(int i = 1;i <= 10; i++){
            assertTrue(tree.insert(i) == true);
        }
        assertEquals(3, tree.getHeight());
        assertTrue(tree.delete(4) == true);
        assertEquals(3, tree.getHeight());
        assertEquals(9,tree.getSize());
        assertTrue(tree.delete(8) == true);
        assertEquals(3, tree.getHeight());
        assertTrue(tree.delete(9) == true);
        assertEquals(2, tree.getHeight());
    }

    @Test
    void test7(){
        tree.insert(10);
        tree.insert(5);
        tree.insert(17);
        tree.insert(2);
        tree.insert(9);
        tree.insert(12);
        tree.insert(20);
        tree.insert(3);
        tree.insert(11);
        tree.insert(15);
        tree.insert(18);
        tree.insert(30);
        tree.insert(13);
        tree.insert(33);
        assertEquals(4, tree.getHeight());
        //propagated deletion balancing
        assertTrue(tree.delete(9) == true);
        assertEquals(4, tree.getHeight());
        assertEquals(13, tree.getSize());
    }

    @Test
    void test8(){
        tree.insert(13);
        tree.insert(10);
        tree.insert(15);
        tree.insert(5);
        tree.insert(11);
        tree.insert(16);
        tree.insert(4);
        tree.insert(6);
        assertEquals(3,tree.getHeight());
        //LR rotation test
        assertTrue(tree.insert(7) == true);
        assertEquals(3,tree.getHeight());
        assertTrue(tree.search(7) == true);
    }

    @Test
    void test9(){
        tree.insert(5);
        tree.insert(2);
        tree.insert(7);
        tree.insert(1);
        tree.insert(4);
        tree.insert(6);
        tree.insert(9);
        tree.insert(3);
        tree.insert(16);
        assertEquals(3,tree.getHeight());
        //RL rotation test
        assertTrue(tree.insert(15) == true);
        assertEquals(3, tree.getHeight());
        assertTrue(tree.search(7) == true);
        assertTrue(tree.delete(7) == true);
        assertTrue(tree.search(7) == false);
    }
    @Test
    void test10(){
        for(int i = 1;i <= 1000; i++){
            assertTrue(tree.insert(i) == true);
        }
        for(int i = 1;i <= 1000; i++){
            assertTrue(tree.search(i) == true);
        }
        for(int i = 1;i <= 1000; i++){
            assertTrue(tree.delete(i) == true);
        }
        for(int i = 1;i <= 1000; i++){
            assertTrue(tree.search(i) == false);
        }
    }

}