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
public class CancellationDiagramProbeTest {

    public CancellationDiagramProbeTest() {
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
     * Test of iteratorDiagramTreeNodes method, of class CancellationDiagramProbe.
     */
    @Test
    public void testIteratorDiagramTreeNodes() {
        System.out.println("iteratorDiagramTreeNodes");
        CancellationDiagramProbe instance = null;
        Iterator expResult = null;
        Iterator result = instance.iteratorDiagramTreeNodes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class CancellationDiagramProbe.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        CancellationDiagramProbe instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}