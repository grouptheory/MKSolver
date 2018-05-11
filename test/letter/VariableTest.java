/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package letter;

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
public class VariableTest {

    public VariableTest() {
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
     * Test of isConstant method, of class Variable.
     */
    @Test
    public void testIsConstant() {
        System.out.println("isConstant");
        Variable instance = new Variable(1,true);
        Boolean expResult = false;
        Boolean result = instance.isConstant();
        assertEquals(expResult, result);
    }

    /**
     * Test of isPositive method, of class Letter.
     */
    @Test
    public void testIsPositive() {
        System.out.println("isPositive");
        Letter instance = LetterFactory.instance().getVariable(1, Boolean.TRUE);
        Boolean expResult = true;
        Boolean result = instance.isPositive();
        assertEquals(expResult, result);
    }

    /**
     * Test of isPositive method, of class Letter.
     */
    public void testIsPositiveFail() {
        System.out.println("isPositive");
        Letter instance = LetterFactory.instance().getVariable(1, Boolean.FALSE);
        Boolean expResult = false;
        Boolean result = instance.isPositive();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInverse method, of class Variable.
     */
    @Test
    public void testGetInverse() {
        System.out.println("getInverse");
        Variable instance = LetterFactory.instance().getVariable(1,false);
        Letter inv = instance.getInverse();
        Letter invinv = inv.getInverse();
        assertEquals(instance, invinv);
        assertEquals(inv, invinv.getInverse());
    }

    /**
     * Test of setInverse method, of class Variable.
     */
    @Test
    public void testSetInverse() {
        System.out.println("setInverse");
        Variable cinv = new Variable(1,false);
        Variable c = new Variable(1,true);
        c.setInverse(cinv);
        cinv.setInverse(c);
        assertEquals(c, cinv.getInverse());
        assertEquals(cinv, cinv.getInverse().getInverse());
    }

    /**
     * Test of toString method, of class Variable.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Variable instance = LetterFactory.instance().getVariable(1,false);
        String expResult = "z1-.";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Constant.
     */
    @Test
    public void testToString2() {
        System.out.println("toString");
        Variable instance2 = LetterFactory.instance().getVariable(1,true);
        String expResult2 = "z1+.";
        String result2 = instance2.toString();
        assertEquals(expResult2, result2);
    }
}