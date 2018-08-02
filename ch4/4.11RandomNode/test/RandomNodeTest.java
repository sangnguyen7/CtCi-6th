import apple.laf.JRSUIUtils;
import code.practice.ch4.RandomNode;
import code.practice.ch4.TreeNode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RandomNodeTest {

    private RandomNode randomNode;

    @BeforeEach
    public void setUp(){
        randomNode = new RandomNode();
    }
    @AfterEach
    public void tearDown(){
        randomNode = null;
    }
    @Test
    public void validateInitialize(){
        assertEquals(randomNode.getRoot(), null);
    }
    @Test
    public void testInsertMethod(){
        randomNode.insert(2);
        assertEquals(randomNode.getRoot().getNumOfChild(), 1);
        assertEquals(randomNode.getRoot().getVal(), 2);
        assertEquals(randomNode.getRoot().getLeft(), null);
        assertEquals(randomNode.getRoot().getRight(), null);

        randomNode.insert(3);
        assertEquals(randomNode.getRoot().getNumOfChild(), 2);
        assertNotEquals(randomNode.getRoot().getLeft(), null);
        assertEquals(randomNode.getRoot().getLeft().getNumOfChild(), 1);
        assertEquals(randomNode.getRoot().getRight(), null);
        assertEquals(randomNode.getRoot().getLeft().getVal(), 3);

        randomNode.insert(4);
        assertEquals(randomNode.getRoot().getNumOfChild(), 3);
        assertNotEquals(randomNode.getRoot().getLeft(), null);
        assertNotEquals(randomNode.getRoot().getRight(), null);
        assertEquals(randomNode.getRoot().getRight().getNumOfChild(), 1);
        assertEquals(randomNode.getRoot().getRight().getVal(), 4);

        randomNode.insert(7);
        assertEquals(randomNode.getRoot().getNumOfChild(), 4);
        assertNotEquals(randomNode.getRoot().getLeft().getLeft(), null);
        assertEquals(randomNode.getRoot().getLeft().getLeft().getVal(), 7);
    }

    @Test
    public void testFindMethod(){
        TreeNode node = randomNode.find(2);
        assertEquals(node, null);
        randomNode.insert(5);
        node = randomNode.find(5);
        assertEquals(node.getVal(), 5);
        randomNode.insert(2);
        randomNode.insert(4);
        randomNode.insert(6);
        randomNode.insert(8);
        node = randomNode.find(4);
        assertNotEquals(node, null);
        assertEquals(node.getVal(), 4);
        assertNotEquals(node.getLeft(), null);
        assertEquals(node.getLeft().getVal(), 8);
        assertEquals(node.getRight(), null);
    }

}
