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

/**
 *
 * @author grouptheory
 */
public class ICancellationDiagramAnalysisDecoratorTest {

    public ICancellationDiagramAnalysisDecoratorTest() {
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
     * Test of texify method, of class ICancellationDiagramAnalysisDecorator.
     */
    @Test
    public void testTexify() {
        System.out.println("texify");
        ICancellationDiagramAnalysis analysis = null;
        DiagramTreeNode dtn = null;
        ICancellationDiagramAnalysisDecorator instance = new ICancellationDiagramAnalysisDecoratorImpl();
        String expResult = "";
        String result = instance.texify(analysis, dtn);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ICancellationDiagramAnalysisDecoratorImpl implements ICancellationDiagramAnalysisDecorator {

        public String texify(ICancellationDiagramAnalysis analysis, DiagramTreeNode dtn) {
            return "";
        }
    }

}