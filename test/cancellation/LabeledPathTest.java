/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cancellation;

import java.util.Iterator;
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
public class LabeledPathTest {

    public LabeledPathTest() {
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
     * Test of project method, of class LabeledPath.
     */
    @Test
    public void testProject() {
        System.out.println("project");
        LabeledPath lp = null;
        Diagram d2 = null;
        LabeledPath expResult = null;
        LabeledPath result = LabeledPath.project(lp, d2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of length method, of class LabeledPath.
     */
    @Test
    public void testLength() {
        System.out.println("length");
        LabeledPath instance = null;
        int expResult = 0;
        int result = instance.length();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cutEdge method, of class LabeledPath.
     */
    @Test
    public void testCutEdge() {
        System.out.println("cutEdge");
        Edge e = null;
        Node insert = null;
        LabeledPath instance = null;
        instance.cutEdge(e, insert);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasEdge method, of class LabeledPath.
     */
    @Test
    public void testHasEdge() {
        System.out.println("hasEdge");
        Edge e = null;
        LabeledPath instance = null;
        boolean expResult = false;
        boolean result = instance.hasEdge(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEdgeIndex method, of class LabeledPath.
     */
    @Test
    public void testGetEdgeIndex() {
        System.out.println("getEdgeIndex");
        Edge e = null;
        LabeledPath instance = null;
        int expResult = 0;
        int result = instance.getEdgeIndex(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNewEnd method, of class LabeledPath.
     */
    @Test
    public void testGetNewEnd() {
        System.out.println("getNewEnd");
        LabeledPath instance = null;
        Node expResult = null;
        Node result = instance.getNewEnd();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPath method, of class LabeledPath.
     */
    @Test
    public void testGetPath() {
        System.out.println("getPath");
        LabeledPath instance = null;
        Path expResult = null;
        Path result = instance.getPath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLabel method, of class LabeledPath.
     */
    @Test
    public void testGetLabel() {
        System.out.println("getLabel");
        LabeledPath instance = null;
        Letter expResult = null;
        Letter result = instance.getLabel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iteratorEdges method, of class LabeledPath.
     */
    @Test
    public void testIteratorEdges() {
        System.out.println("iteratorEdges");
        LabeledPath instance = null;
        Iterator expResult = null;
        Iterator result = instance.iteratorEdges();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class LabeledPath.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        LabeledPath instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validate method, of class LabeledPath.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        Diagram d = null;
        LabeledPath instance = null;
        instance.validate(d);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}