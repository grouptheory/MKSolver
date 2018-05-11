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
public class DiagramDegeneracyTesterTest {

    public DiagramDegeneracyTesterTest() {
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
     * Test of isDegenerate method, of class DiagramDegeneracyTester.
     */
    @Test
    public void testIsDegenerate() {
        System.out.println("isDegenerate");
        Diagram d = null;
        boolean expResult = false;
        boolean result = DiagramDegeneracyTester.isDegenerate(d);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}