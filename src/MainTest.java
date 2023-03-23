import RB_Tree.RB;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void main() {
        RB<Integer> usedTree = new RB<Integer>();
        usedTree.insert(12);
        usedTree.insert(5);
        usedTree.insert(15);
        usedTree.insert(3);
        usedTree.insert(10);
        usedTree.insert(13);
        usedTree.insert(17);
        usedTree.insert(4);
        usedTree.insert(7);
        usedTree.insert(11);
        usedTree.insert(14);
        usedTree.insert(6);
        usedTree.insert(8);
        usedTree.delete(10);
        usedTree.treeTraversal(usedTree.getRoot(),"l");
    }
}