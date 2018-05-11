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
public class DiagramTreeNodeComparatorTest {

    public DiagramTreeNodeComparatorTest() {
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
     * Test of compare method, of class DiagramTreeNodeComparator.
     */
    @Test
    public void testCompare() {
        System.out.println("compare");
        Object o1 = null;
        Object o2 = null;
        DiagramTreeNodeComparator instance = new DiagramTreeNodeComparator();
        int expResult = 0;
        int result = instance.compare(o1, o2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class DiagramTreeNodeComparator.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        DiagramTreeNodeComparator instance = new DiagramTreeNodeComparator();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}