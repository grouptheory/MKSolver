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
import utility.ComposableIterator;

/**
 *
 * @author grouptheory
 */
public class ComposableDiagramIteratorTest {

    public ComposableDiagramIteratorTest() {
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
     * Test of newComposableIterator method, of class ComposableDiagramIterator.
     */
    @Test
    public void testNewComposableIterator() {
        System.out.println("newComposableIterator");
        ComposableIterator parent = null;
        ComposableDiagramIterator instance = null;
        ComposableIterator expResult = null;
        ComposableIterator result = instance.newComposableIterator(parent);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasNext method, of class ComposableDiagramIterator.
     */
    @Test
    public void testHasNext() {
        System.out.println("hasNext");
        ComposableDiagramIterator instance = null;
        boolean expResult = false;
        boolean result = instance.hasNext();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of next method, of class ComposableDiagramIterator.
     */
    @Test
    public void testNext() {
        System.out.println("next");
        ComposableDiagramIterator instance = null;
        Object expResult = null;
        Object result = instance.next();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class ComposableDiagramIterator.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        ComposableDiagramIterator instance = null;
        instance.remove();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class ComposableDiagramIterator.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ComposableDiagramIterator instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}