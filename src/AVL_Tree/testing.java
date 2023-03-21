package AVL_Tree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class testing {
    AVL<Integer> tree = new AVL<>();
    @Test
    void test1(){
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        assertEquals(2, tree.height());
        assertTrue(tree.search(4) == true);
        assertTrue(tree.search(0) == false);

    }

    @Test
    void test2(){
        tree.insert(50);
        tree.insert(40);
        tree.insert(60);
        tree.insert(30);
        tree.insert(45);
        tree.insert(49);
        assertEquals(2, tree.height());
    }

    @Test
    void test3(){
        tree.insert(3);
        tree.insert(4);
        tree.delete(4);
        assertEquals(1, tree.size());
        tree.insert(7);
        tree.insert(4);
        assertEquals(3, tree.size());
        tree.insert(4);
        assertEquals(3, tree.size());
    }

    @Test
    void test4(){
        for(int i = 0;i < 10; i++){
            tree.insert(i);
        }
        
    }
}
