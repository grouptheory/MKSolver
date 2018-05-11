/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cancellation;

import cancellation.DiagramTreeNode.CollectionVisitor;
import cancellation.DiagramTreeNode.ConsoleVisitor;
import letter.Letter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author grouptheory
 */
public class DiagramTreeNodeTest {

    public DiagramTreeNodeTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getDiagram method, of class DiagramTreeNode.
     */
    @Test
    public void testGetDiagram() {
        System.out.println("getDiagram");
        DiagramTreeNode instance = null;
        Diagram expResult = null;
        Diagram result = instance.getDiagram();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class DiagramTreeNode.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        DiagramTreeNode instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLeaf method, of class DiagramTreeNode.
     */
    @Test
    public void testSetLeaf() {
        System.out.println("setLeaf");
        DiagramTreeNode instance = null;
        instance.setLeaf();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLeaf method, of class DiagramTreeNode.
     */
    @Test
    public void testGetLeaf() {
        System.out.println("getLeaf");
        DiagramTreeNode instance = null;
        boolean expResult = false;
        boolean result = instance.getLeaf();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDepth method, of class DiagramTreeNode.
     */
    @Test
    public void testGetDepth() {
        System.out.println("getDepth");
        DiagramTreeNode instance = null;
        int expResult = 0;
        int result = instance.getDepth();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of extend method, of class DiagramTreeNode.
     */
    @Test
    public void testExtend() {
        System.out.println("extend");
        Letter let = null;
        boolean last = false;
        DiagramTreeNode instance = null;
        instance.extend(let, last);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class DiagramTreeNode.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        DiagramTreeNode instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of visitedBy method, of class DiagramTreeNode.
     */
    @Test
    public void testVisitedBy_ExtensionVisitor() {
        System.out.println("visitedBy");
        ExtensionVisitor ev = null;
        DiagramTreeNode instance = null;
        instance.visitedBy(ev);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of visitedBy method, of class DiagramTreeNode.
     */
    @Test
    public void testVisitedBy_DiagramTreeNodeConsoleVisitor() {
        System.out.println("visitedBy");
        ConsoleVisitor pv = null;
        DiagramTreeNode instance = null;
        instance.visitedBy(pv);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of visitedBy method, of class DiagramTreeNode.
     */
    @Test
    public void testVisitedBy_DiagramTreeNodeCollectionVisitor() {
        System.out.println("visitedBy");
        CollectionVisitor cv = null;
        DiagramTreeNode instance = null;
        instance.visitedBy(cv);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}