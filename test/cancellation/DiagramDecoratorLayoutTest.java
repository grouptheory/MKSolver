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
public class DiagramDecoratorLayoutTest {

    public DiagramDecoratorLayoutTest() {
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
     * Test of getX method, of class DiagramDecoratorLayout.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        Node nd = null;
        DiagramDecoratorLayout instance = null;
        double expResult = 0.0;
        double result = instance.getX(nd);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getY method, of class DiagramDecoratorLayout.
     */
    @Test
    public void testGetY() {
        System.out.println("getY");
        Node nd = null;
        DiagramDecoratorLayout instance = null;
        double expResult = 0.0;
        double result = instance.getY(nd);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class DiagramDecoratorLayout.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        DiagramDecoratorLayout instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validate method, of class DiagramDecoratorLayout.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        Diagram d = null;
        DiagramDecoratorLayout instance = null;
        String expResult = "";
        String result = instance.validate(d);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}