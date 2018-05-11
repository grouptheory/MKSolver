/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cancellation;

import cancellation.Diagram.Decorator;
import java.util.Iterator;
import letter.Variable;
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
public class DiagramTest {

    public DiagramTest() {
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
     * Test of getBegin method, of class Diagram.
     */
    @Test
    public void testGetBegin() {
        System.out.println("getBegin");
        Diagram instance = new Diagram();
        Node expResult = null;
        Node result = instance.getBegin();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEnd method, of class Diagram.
     */
    @Test
    public void testGetEnd() {
        System.out.println("getEnd");
        Diagram instance = new Diagram();
        Node expResult = null;
        Node result = instance.getEnd();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isClosed method, of class Diagram.
     */
    @Test
    public void testIsClosed() {
        System.out.println("isClosed");
        Diagram instance = new Diagram();
        boolean expResult = false;
        boolean result = instance.isClosed();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addNode method, of class Diagram.
     */
    @Test
    public void testAddNode() {
        System.out.println("addNode");
        Diagram instance = new Diagram();
        Node expResult = null;
        Node result = instance.addNode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of lookupNode method, of class Diagram.
     */
    @Test
    public void testLookupNode() {
        System.out.println("lookupNode");
        int id = 0;
        Diagram instance = new Diagram();
        Node expResult = null;
        Node result = instance.lookupNode(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberOfNodes method, of class Diagram.
     */
    @Test
    public void testGetNumberOfNodes() {
        System.out.println("getNumberOfNodes");
        Diagram instance = new Diagram();
        int expResult = 0;
        int result = instance.getNumberOfNodes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iteratorEdges method, of class Diagram.
     */
    @Test
    public void testIteratorEdges() {
        System.out.println("iteratorEdges");
        Diagram instance = new Diagram();
        Iterator expResult = null;
        Iterator result = instance.iteratorEdges();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iteratorNodes method, of class Diagram.
     */
    @Test
    public void testIteratorNodes() {
        System.out.println("iteratorNodes");
        Diagram instance = new Diagram();
        Iterator expResult = null;
        Iterator result = instance.iteratorNodes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iteratorLabeledPaths method, of class Diagram.
     */
    @Test
    public void testIteratorLabeledPaths() {
        System.out.println("iteratorLabeledPaths");
        Diagram instance = new Diagram();
        Iterator expResult = null;
        Iterator result = instance.iteratorLabeledPaths();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDual method, of class Diagram.
     */
    @Test
    public void testGetDual() {
        System.out.println("getDual");
        LabeledPath lp = null;
        Diagram instance = new Diagram();
        LabeledPath expResult = null;
        LabeledPath result = instance.getDual(lp);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPaths method, of class Diagram.
     */
    @Test
    public void testGetPaths() {
        System.out.println("getPaths");
        Edge e = null;
        Diagram instance = new Diagram();
        LabeledPath[] expResult = null;
        LabeledPath[] result = instance.getPaths(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVariablePath method, of class Diagram.
     */
    @Test
    public void testGetVariablePath() {
        System.out.println("getVariablePath");
        Variable v = null;
        Diagram instance = new Diagram();
        LabeledPath expResult = null;
        LabeledPath result = instance.getVariablePath(v);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberOfEdges method, of class Diagram.
     */
    @Test
    public void testGetNumberOfEdges() {
        System.out.println("getNumberOfEdges");
        Diagram instance = new Diagram();
        int expResult = 0;
        int result = instance.getNumberOfEdges();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of lookupEdge method, of class Diagram.
     */
    @Test
    public void testLookupEdge() {
        System.out.println("lookupEdge");
        int id1 = 0;
        int id2 = 0;
        Diagram instance = new Diagram();
        Edge expResult = null;
        Edge result = instance.lookupEdge(id1, id2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addEdge method, of class Diagram.
     */
    @Test
    public void testAddEdge() {
        System.out.println("addEdge");
        Node a = null;
        Node b = null;
        Diagram instance = new Diagram();
        Edge expResult = null;
        Edge result = instance.addEdge(a, b);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delEdge method, of class Diagram.
     */
    @Test
    public void testDelEdge() {
        System.out.println("delEdge");
        Edge e = null;
        Diagram instance = new Diagram();
        instance.delEdge(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isCuttableEdge method, of class Diagram.
     */
    @Test
    public void testIsCuttableEdge() {
        System.out.println("isCuttableEdge");
        Edge e = null;
        Diagram instance = new Diagram();
        boolean expResult = false;
        boolean result = instance.isCuttableEdge(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cutEdge method, of class Diagram.
     */
    @Test
    public void testCutEdge() {
        System.out.println("cutEdge");
        Edge e = null;
        Diagram instance = new Diagram();
        Node expResult = null;
        Node result = instance.cutEdge(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addLabeledPath method, of class Diagram.
     */
    @Test
    public void testAddLabeledPath() {
        System.out.println("addLabeledPath");
        LabeledPath lp = null;
        Diagram instance = new Diagram();
        instance.addLabeledPath(lp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Diagram.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Diagram instance = new Diagram();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validateNode method, of class Diagram.
     */
    @Test
    public void testValidateNode() {
        System.out.println("validateNode");
        Node node = null;
        Diagram instance = new Diagram();
        instance.validateNode(node);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validateEdge method, of class Diagram.
     */
    @Test
    public void testValidateEdge() {
        System.out.println("validateEdge");
        Edge e = null;
        Diagram instance = new Diagram();
        instance.validateEdge(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validateLabeledPath method, of class Diagram.
     */
    @Test
    public void testValidateLabeledPath() {
        System.out.println("validateLabeledPath");
        LabeledPath lp = null;
        Diagram instance = new Diagram();
        instance.validateLabeledPath(lp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validate method, of class Diagram.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        Diagram instance = new Diagram();
        instance.validate();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDecorator method, of class Diagram.
     */
    @Test
    public void testGetDecorator() {
        System.out.println("getDecorator");
        Diagram instance = new Diagram();
        Decorator expResult = null;
        Decorator result = instance.getDecorator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDecorator method, of class Diagram.
     */
    @Test
    public void testSetDecorator() {
        System.out.println("setDecorator");
        Decorator dec = null;
        Diagram instance = new Diagram();
        instance.setDecorator(dec);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}