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
public class ExtensionVisitorTest {

    public ExtensionVisitorTest() {
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
     * Test of makeSubVisitor method, of class ExtensionVisitor.
     */
    @Test
    public void testMakeSubVisitor() {
        System.out.println("makeSubVisitor");
        ExtensionVisitor instance = null;
        ExtensionVisitor expResult = null;
        ExtensionVisitor result = instance.makeSubVisitor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of visit method, of class ExtensionVisitor.
     */
    @Test
    public void testVisit() {
        System.out.println("visit");
        DiagramTreeNode dtn = null;
        ExtensionVisitor instance = null;
        instance.visit(dtn);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}