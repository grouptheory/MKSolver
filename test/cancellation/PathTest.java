/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cancellation;

import java.util.Iterator;
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
public class PathTest {

    public PathTest() {
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
     * Test of project method, of class Path.
     */
    @Test
    public void testProject() {
        System.out.println("project");
        Path p = null;
        Diagram d2 = null;
        Path expResult = null;
        Path result = Path.project(p, d2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iteratorEdges method, of class Path.
     */
    @Test
    public void testIteratorEdges() {
        System.out.println("iteratorEdges");
        Path instance = null;
        Iterator expResult = null;
        Iterator result = instance.iteratorEdges();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isNodePath method, of class Path.
     */
    @Test
    public void testIsNodePath() {
        System.out.println("isNodePath");
        Path instance = null;
        boolean expResult = false;
        boolean result = instance.isNodePath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSrcNode method, of class Path.
     */
    @Test
    public void testGetSrcNode() {
        System.out.println("getSrcNode");
        Path instance = null;
        Node expResult = null;
        Node result = instance.getSrcNode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of length method, of class Path.
     */
    @Test
    public void testLength() {
        System.out.println("length");
        Path instance = null;
        int expResult = 0;
        int result = instance.length();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEdgePath method, of class Path.
     */
    @Test
    public void testIsEdgePath() {
        System.out.println("isEdgePath");
        Path instance = null;
        boolean expResult = false;
        boolean result = instance.isEdgePath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSrcEdge method, of class Path.
     */
    @Test
    public void testGetSrcEdge() {
        System.out.println("getSrcEdge");
        Path instance = null;
        Edge expResult = null;
        Edge result = instance.getSrcEdge();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of append method, of class Path.
     */
    @Test
    public void testAppend_Edge() {
        System.out.println("append");
        Edge e = null;
        Path instance = null;
        instance.append(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of append method, of class Path.
     */
    @Test
    public void testAppend_Node() {
        System.out.println("append");
        Node nd = null;
        Path instance = null;
        instance.append(nd);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasEdge method, of class Path.
     */
    @Test
    public void testHasEdge() {
        System.out.println("hasEdge");
        Edge e = null;
        Path instance = null;
        boolean expResult = false;
        boolean result = instance.hasEdge(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEdgeIndex method, of class Path.
     */
    @Test
    public void testGetEdgeIndex() {
        System.out.println("getEdgeIndex");
        Edge e = null;
        Path instance = null;
        int expResult = 0;
        int result = instance.getEdgeIndex(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cutEdge method, of class Path.
     */
    @Test
    public void testCutEdge() {
        System.out.println("cutEdge");
        Edge e = null;
        Node insert = null;
        Path instance = null;
        instance.cutEdge(e, insert);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Path.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Path instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validate method, of class Path.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        Diagram d = null;
        Path instance = null;
        instance.validate(d);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}