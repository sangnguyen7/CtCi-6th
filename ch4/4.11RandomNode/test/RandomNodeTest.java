import code.practice.ch4.RandomNode;
import code.practice.ch4.TreeNode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

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

    private void initialize(){
        randomNode.insert(10);
        randomNode.insert(3);
        randomNode.insert(4);
        randomNode.insert(8);
        randomNode.insert(9);
    }

    @Test
    public void testRandomNodeMethod(){
        TreeNode node = randomNode.getRandomNode();
        assertEquals(node, null);

        randomNode.insert(10);
        for (int i = 0; i < 100; i++){
            node = randomNode.getRandomNode();
            assertEquals(node.getVal(), 10);
        }

        randomNode.insert(3);
        randomNode.insert(4);
        randomNode.insert(8);
        randomNode.insert(9);
        for (int i = 0; i < 100; i++){
            node = randomNode.getRandomNode();
            assertNotEquals(node, null);
        }
        Class c = randomNode.getClass();
        try{
            Method methodRandom = c.getDeclaredMethod("helper", TreeNode.class, int.class);
            methodRandom.setAccessible(true);
            node = (TreeNode) methodRandom.invoke(randomNode, randomNode.getRoot(), 3);
            assertEquals(node.getVal(), 10);
            node = (TreeNode) methodRandom.invoke(randomNode, randomNode.getRoot(), 1);
            assertEquals(node.getVal(), 8);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteMethod(){
        initialize();
        assertEquals(randomNode.getRoot().getVal(), 10);
        assertEquals(randomNode.getRoot().getNumOfChild(), 5);
        randomNode.delete(randomNode.getRoot());
        assertNotEquals(randomNode.getRoot().getVal(), 10);
        assertEquals(randomNode.getRoot().getVal(), 8);
        assertEquals(randomNode.getRoot().getNumOfChild(), 4);
        TreeNode node = randomNode.find(4);
        randomNode.delete(node);
        assertEquals(randomNode.getRoot().getNumOfChild(), 3);
        assertEquals(randomNode.getRoot().getVal(), 8);
        assertNotEquals(randomNode.getRoot().getLeft(), null);
        assertNotEquals(randomNode.getRoot().getRight(), null);
        assertEquals(randomNode.getRoot().getRight().getVal(), 9);
    }

}
