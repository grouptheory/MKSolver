/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cancellation;

import equation.Equivalences;
import equation.GroupWord;
import equation.QuadraticSystem;
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
public class AbstractCancellationDiagramAnalysisTest {

    public AbstractCancellationDiagramAnalysisTest() {
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
     * Test of addDecorator method, of class AbstractCancellationDiagramAnalysis.
     */
    @Test
    public void testAddDecorator() {
        System.out.println("addDecorator");
        ICancellationDiagramAnalysisDecorator dec = null;
        AbstractCancellationDiagramAnalysis instance = null;
        instance.addDecorator(dec);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iteratorDecorators method, of class AbstractCancellationDiagramAnalysis.
     */
    @Test
    public void testIteratorDecorators() {
        System.out.println("iteratorDecorators");
        AbstractCancellationDiagramAnalysis instance = null;
        Iterator expResult = null;
        Iterator result = instance.iteratorDecorators();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProblem method, of class AbstractCancellationDiagramAnalysis.
     */
    @Test
    public void testGetProblem() {
        System.out.println("getProblem");
        AbstractCancellationDiagramAnalysis instance = null;
        GroupWord expResult = null;
        GroupWord result = instance.getProblem();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuadraticSystem method, of class AbstractCancellationDiagramAnalysis.
     */
    @Test
    public void testGetQuadraticSystem() {
        System.out.println("getQuadraticSystem");
        AbstractCancellationDiagramAnalysis instance = null;
        QuadraticSystem expResult = null;
        QuadraticSystem result = instance.getQuadraticSystem();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuadraticEquation method, of class AbstractCancellationDiagramAnalysis.
     */
    @Test
    public void testGetQuadraticEquation() {
        System.out.println("getQuadraticEquation");
        AbstractCancellationDiagramAnalysis instance = null;
        GroupWord expResult = null;
        GroupWord result = instance.getQuadraticEquation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEquivalences method, of class AbstractCancellationDiagramAnalysis.
     */
    @Test
    public void testGetEquivalences() {
        System.out.println("getEquivalences");
        AbstractCancellationDiagramAnalysis instance = null;
        Equivalences expResult = null;
        Equivalences result = instance.getEquivalences();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class AbstractCancellationDiagramAnalysis.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        AbstractCancellationDiagramAnalysis instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}