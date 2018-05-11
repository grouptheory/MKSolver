/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cancellation;

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
public class ExtensionIteratorFactoryTest {

    public ExtensionIteratorFactoryTest() {
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
     * Test of instance method, of class ExtensionIteratorFactory.
     */
    @Test
    public void testInstance() {
        System.out.println("instance");
        ExtensionIteratorFactory expResult = null;
        ExtensionIteratorFactory result = ExtensionIteratorFactory.instance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of newExtensionIteratorLast method, of class ExtensionIteratorFactory.
     */
    @Test
    public void testNewExtensionIteratorLast() {
        System.out.println("newExtensionIteratorLast");
        Diagram d = null;
        Letter let = null;
        ExtensionIteratorFactory instance = null;
        ExtensionIterator expResult = null;
        ExtensionIterator result = instance.newExtensionIteratorLast(d, let);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of newExtensionIterator method, of class ExtensionIteratorFactory.
     */
    @Test
    public void testNewExtensionIterator() {
        System.out.println("newExtensionIterator");
        Diagram d = null;
        Letter let = null;
        ExtensionIteratorFactory instance = null;
        ExtensionIterator expResult = null;
        ExtensionIterator result = instance.newExtensionIterator(d, let);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}