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
public class NodeTest {

    public NodeTest() {
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
     * Test of getID method, of class Node.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Node instance = null;
        int expResult = 0;
        int result = instance.getID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of degree method, of class Node.
     */
    @Test
    public void testDegree() {
        System.out.println("degree");
        Node instance = null;
        int expResult = 0;
        int result = instance.degree();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addEdge method, of class Node.
     */
    @Test
    public void testAddEdge() {
        System.out.println("addEdge");
        Node peer = null;
        Node instance = null;
        Edge expResult = null;
        Edge result = instance.addEdge(peer);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEdge method, of class Node.
     */
    @Test
    public void testGetEdge() {
        System.out.println("getEdge");
        Node peer = null;
        Node instance = null;
        Edge expResult = null;
        Edge result = instance.getEdge(peer);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delEdge method, of class Node.
     */
    @Test
    public void testDelEdge() {
        System.out.println("delEdge");
        Node peer = null;
        Node instance = null;
        Edge expResult = null;
        Edge result = instance.delEdge(peer);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of edgeIterator method, of class Node.
     */
    @Test
    public void testEdgeIterator() {
        System.out.println("edgeIterator");
        Node instance = null;
        Iterator expResult = null;
        Iterator result = instance.edgeIterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Node.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Node instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toStringShort method, of class Node.
     */
    @Test
    public void testToStringShort() {
        System.out.println("toStringShort");
        Node instance = null;
        String expResult = "";
        String result = instance.toStringShort();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}