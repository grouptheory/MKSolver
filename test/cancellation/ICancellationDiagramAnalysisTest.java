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
public class ICancellationDiagramAnalysisTest {

    public ICancellationDiagramAnalysisTest() {
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
     * Test of addDecorator method, of class ICancellationDiagramAnalysis.
     */
    @Test
    public void testAddDecorator() {
        System.out.println("addDecorator");
        ICancellationDiagramAnalysisDecorator dec = null;
        ICancellationDiagramAnalysis instance = new ICancellationDiagramAnalysisImpl();
        instance.addDecorator(dec);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iteratorDecorators method, of class ICancellationDiagramAnalysis.
     */
    @Test
    public void testIteratorDecorators() {
        System.out.println("iteratorDecorators");
        ICancellationDiagramAnalysis instance = new ICancellationDiagramAnalysisImpl();
        Iterator expResult = null;
        Iterator result = instance.iteratorDecorators();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iteratorDiagramTreeNodes method, of class ICancellationDiagramAnalysis.
     */
    @Test
    public void testIteratorDiagramTreeNodes() {
        System.out.println("iteratorDiagramTreeNodes");
        ICancellationDiagramAnalysis instance = new ICancellationDiagramAnalysisImpl();
        Iterator expResult = null;
        Iterator result = instance.iteratorDiagramTreeNodes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProblem method, of class ICancellationDiagramAnalysis.
     */
    @Test
    public void testGetProblem() {
        System.out.println("getProblem");
        ICancellationDiagramAnalysis instance = new ICancellationDiagramAnalysisImpl();
        GroupWord expResult = null;
        GroupWord result = instance.getProblem();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuadraticSystem method, of class ICancellationDiagramAnalysis.
     */
    @Test
    public void testGetQuadraticSystem() {
        System.out.println("getQuadraticSystem");
        ICancellationDiagramAnalysis instance = new ICancellationDiagramAnalysisImpl();
        QuadraticSystem expResult = null;
        QuadraticSystem result = instance.getQuadraticSystem();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuadraticEquation method, of class ICancellationDiagramAnalysis.
     */
    @Test
    public void testGetQuadraticEquation() {
        System.out.println("getQuadraticEquation");
        ICancellationDiagramAnalysis instance = new ICancellationDiagramAnalysisImpl();
        GroupWord expResult = null;
        GroupWord result = instance.getQuadraticEquation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEquivalences method, of class ICancellationDiagramAnalysis.
     */
    @Test
    public void testGetEquivalences() {
        System.out.println("getEquivalences");
        ICancellationDiagramAnalysis instance = new ICancellationDiagramAnalysisImpl();
        Equivalences expResult = null;
        Equivalences result = instance.getEquivalences();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class ICancellationDiagramAnalysis.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ICancellationDiagramAnalysis instance = new ICancellationDiagramAnalysisImpl();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ICancellationDiagramAnalysisImpl implements ICancellationDiagramAnalysis {

        public void addDecorator(ICancellationDiagramAnalysisDecorator dec) {
        }

        public Iterator iteratorDecorators() {
            return null;
        }

        public Iterator iteratorDiagramTreeNodes() {
            return null;
        }

        public GroupWord getProblem() {
            return null;
        }

        public QuadraticSystem getQuadraticSystem() {
            return null;
        }

        public GroupWord getQuadraticEquation() {
            return null;
        }

        public Equivalences getEquivalences() {
            return null;
        }

        public String toString() {
            return "";
        }
    }

}