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
public class LetterTest {

    public LetterTest() {
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
     * Test of isConstant method, of class Letter.
     */
    @Test
    public void testIsConstant() {
        System.out.println("isConstant");
        Letter instance = LetterFactory.instance().getConstant(1, Boolean.TRUE);
        Boolean expResult = true;
        Boolean result = instance.isConstant();
        assertEquals(expResult, result);
    }

    /**
     * Test of isConstant method, of class Letter.
     */
    @Test
    public void testIsConstantFail() {
        System.out.println("isConstant");
        Letter instance = LetterFactory.instance().getVariable(1, Boolean.TRUE);
        Boolean expResult = false;
        Boolean result = instance.isConstant();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInverse method, of class Letter.
     */
    @Test
    public void testGetInverse() {
        System.out.println("getInverse");
        Letter instance = LetterFactory.instance().getVariable(1, Boolean.TRUE);
        Letter expResult = LetterFactory.instance().getVariable(1, Boolean.FALSE);
        Letter result = instance.getInverse();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInverse method, of class Letter.
     */
    @Test
    public void testGetInverse2() {
        System.out.println("getInverse2");
        Letter instance = LetterFactory.instance().getVariable(1, Boolean.FALSE);
        Letter expResult = LetterFactory.instance().getVariable(1, Boolean.TRUE);
        Letter result = instance.getInverse();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInverse method, of class Letter.
     */
    @Test
    public void testGetInverse3() {
        System.out.println("getInverse3");
        Letter instance = LetterFactory.instance().getConstant(1, Boolean.TRUE);
        Letter expResult = LetterFactory.instance().getConstant(1, Boolean.FALSE);
        Letter result = instance.getInverse();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInverse method, of class Letter.
     */
    @Test
    public void testGetInverse4() {
        System.out.println("getInverse4");
        Letter instance = LetterFactory.instance().getConstant(1, Boolean.FALSE);
        Letter expResult = LetterFactory.instance().getConstant(1, Boolean.TRUE);
        Letter result = instance.getInverse();
        assertEquals(expResult, result);
    }

    /**
     * Test of getID method, of class Letter.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Letter instance = LetterFactory.instance().getConstant(101, Boolean.FALSE);
        int expResult = 101;
        int result = instance.getID();
        assertEquals(expResult, result);
    }

    /**
     * Test of testEquals method, of class  Letter.
     */
    @Test
    public void testTestEquals() {
        System.out.println("testEquals");
        Letter a1 = LetterFactory.instance().getConstant(101, Boolean.FALSE);
        Letter a2 = LetterFactory.instance().getConstant(101, Boolean.FALSE);
        Boolean expResult = true;
        Boolean result = Letter.testEquals(a1, a2);
        assertEquals(expResult, result);
    }

    /**
     * Test of testEquals method, of class  Letter.
     */
    @Test
    public void testTestEquals2() {
        System.out.println("testEquals2");
        Letter a1 = LetterFactory.instance().getConstant(101, Boolean.FALSE);
        Letter a2 = LetterFactory.instance().getConstant(101, Boolean.TRUE);
        Boolean expResult = false;
        Boolean result = Letter.testEquals(a1, a2);
        assertEquals(expResult, result);
    }

    /**
     * Test of testInverse method, of class Letter.
     */
    @Test
    public void testTestInverse() {
        System.out.println("testInverse");
        Letter a1 = LetterFactory.instance().getConstant(101, Boolean.FALSE);
        Letter a2 = LetterFactory.instance().getConstant(101, Boolean.TRUE);
        Boolean expResult = true;
        Boolean result = Letter.testInverse(a1, a2);
        assertEquals(expResult, result);
    }

    /**
     * Test of testInverse method, of class Letter.
     */
    @Test
    public void testTestInverse2() {
        System.out.println("testInverse2");
        Letter a1 = LetterFactory.instance().getConstant(101, Boolean.TRUE);
        Letter a2 = LetterFactory.instance().getConstant(101, Boolean.TRUE);
        Boolean expResult = false;
        Boolean result = Letter.testInverse(a1, a2);
        assertEquals(expResult, result);
    }

    /**
     * Test of testEqualOrInverse method, of class Letter.
     */
    @Test
    public void testTestEqualOrInverse() {
        System.out.println("testEqualOrInverse");
        Letter a1 = LetterFactory.instance().getConstant(101, Boolean.TRUE);
        Letter a2 = LetterFactory.instance().getConstant(101, Boolean.TRUE);
        Boolean expResult = true;
        Boolean result = Letter.testEqualOrInverse(a1, a2);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of testEqualOrInverse method, of class Letter.
     */
    @Test
    public void testTestEqualOrInverse2() {
        System.out.println("testEqualOrInverse2");
        Letter a1 = LetterFactory.instance().getConstant(101, Boolean.FALSE);
        Letter a2 = LetterFactory.instance().getConstant(101, Boolean.TRUE);
        Boolean expResult = true;
        Boolean result = Letter.testEqualOrInverse(a1, a2);
        assertEquals(expResult, result);
    }

    /**
     * Test of isPositive method, of class Letter.
     */
    @Test
    public void testIsPositive() {
        System.out.println("isPositive");
        Letter instance = LetterFactory.instance().getConstant(101, Boolean.FALSE);
        Boolean expResult = false;
        Boolean result = instance.isPositive();
        assertEquals(expResult, result);
    }

    /**
     * Test of isPositive method, of class Letter.
     */
    @Test
    public void testIsPositive2() {
        System.out.println("isPositive2");
        Letter instance = LetterFactory.instance().getConstant(101, Boolean.TRUE);
        Boolean expResult = true;
        Boolean result = instance.isPositive();
        assertEquals(expResult, result);
    }


    /**
     * Test of setPositive method, of class Letter.
     */
    @Test
    public void testSetPositive() {
        System.out.println("setPositive");
        Boolean pos = true;
        Letter instance = LetterFactory.instance().getConstant(101, Boolean.FALSE);
        assertEquals(false, instance.isPositive());
        instance.setPositive(pos);
        assertEquals(true, instance.isPositive());
    }

    public class LetterImpl extends Letter {

        public LetterImpl() {
            super(0);
        }

        public Boolean isConstant() {
            return null;
        }

        public Letter getInverse() {
            return null;
        }
    }
}
