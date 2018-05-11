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
public class ExtensionToEdgeExactTest {

    public ExtensionToEdgeExactTest() {
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
     * Test of apply method, of class ExtensionToEdgeExact.
     */
    @Test
    public void testApply() {
        System.out.println("apply");
        Diagram d = null;
        ExtensionToEdgeExact instance = null;
        Diagram expResult = null;
        Diagram result = instance.apply(d);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class ExtensionToEdgeExact.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ExtensionToEdgeExact instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}