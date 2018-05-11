/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cancellation;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import report.IReport;

/**
 *
 * @author grouptheory
 */
public class LatexAdapterTest {

    public LatexAdapterTest() {
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
     * Test of instance method, of class LatexAdapter.
     */
    @Test
    public void testInstance() {
        System.out.println("instance");
        LatexAdapter expResult = null;
        LatexAdapter result = LatexAdapter.instance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of renderDiagram method, of class LatexAdapter.
     */
    @Test
    public void testRenderDiagram() {
        System.out.println("renderDiagram");
        Diagram d = null;
        LatexAdapter instance = null;
        String expResult = "";
        String result = instance.renderDiagram(d);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of renderCancellationDiagramAnalysis method, of class LatexAdapter.
     */
    @Test
    public void testRenderCancellationDiagramAnalysis() {
        System.out.println("renderCancellationDiagramAnalysis");
        IReport report = null;
        ICancellationDiagramAnalysis analysis = null;
        LatexAdapter instance = null;
        instance.renderCancellationDiagramAnalysis(report, analysis);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}