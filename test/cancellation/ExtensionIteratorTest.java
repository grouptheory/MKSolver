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
public class ExtensionIteratorTest {

    public ExtensionIteratorTest() {
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
     * Test of hasNext method, of class ExtensionIterator.
     */
    @Test
    public void testHasNext() {
        System.out.println("hasNext");
        ExtensionIterator instance = null;
        boolean expResult = false;
        boolean result = instance.hasNext();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of next method, of class ExtensionIterator.
     */
    @Test
    public void testNext() {
        System.out.println("next");
        ExtensionIterator instance = null;
        Extension expResult = null;
        Extension result = instance.next();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class ExtensionIterator.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ExtensionIterator instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}