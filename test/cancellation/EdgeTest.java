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
public class EdgeTest {

    public EdgeTest() {
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
     * Test of setReverse method, of class Edge.
     */
    @Test
    public void testSetReverse() {
        System.out.println("setReverse");
        Edge rev = null;
        Edge instance = null;
        instance.setReverse(rev);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getA method, of class Edge.
     */
    @Test
    public void testGetA() {
        System.out.println("getA");
        Edge instance = null;
        Node expResult = null;
        Node result = instance.getA();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getB method, of class Edge.
     */
    @Test
    public void testGetB() {
        System.out.println("getB");
        Edge instance = null;
        Node expResult = null;
        Node result = instance.getB();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOccupancy method, of class Edge.
     */
    @Test
    public void testGetOccupancy() {
        System.out.println("getOccupancy");
        Edge instance = null;
        int expResult = 0;
        int result = instance.getOccupancy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOccupancy method, of class Edge.
     */
    @Test
    public void testSetOccupancy() {
        System.out.println("setOccupancy");
        int occupancy = 0;
        Edge instance = null;
        instance.setOccupancy(occupancy);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of incrementOccupancy method, of class Edge.
     */
    @Test
    public void testIncrementOccupancy() {
        System.out.println("incrementOccupancy");
        Edge instance = null;
        instance.incrementOccupancy();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOpposite method, of class Edge.
     */
    @Test
    public void testGetOpposite() {
        System.out.println("getOpposite");
        Node x = null;
        Edge instance = null;
        Node expResult = null;
        Node result = instance.getOpposite(x);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Edge.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Edge instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}