/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package equation;

import java.util.HashMap;
import letter.Letter;
import letter.LetterFactory;
import letter.Variable;
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
public class QuadraticSystemTest {

    public QuadraticSystemTest() {
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
     * Test of appendLetter method, of class QuadraticSystem.
     */
    @Test
    public void testAppendLetter() {
        System.out.println("appendLetter");
        Letter let = LetterFactory.instance().getConstant(1, Boolean.TRUE);
        QuadraticSystem instance = new QuadraticSystem();
        instance.appendLetter(let);
        assertEquals(instance.getQuadraticEquation().length(), 1);
        instance.appendLetter(let);
        instance.appendLetter(let);
        instance.appendLetter(let);
        assertEquals(instance.getQuadraticEquation().length(), 4);
    }

    /**
     * Test of addEquivalence method, of class QuadraticSystem.
     */
    @Test
    public void testAddEquivalence() {
        System.out.println("addEquivalence");
        System.out.println("appendLetter");
        Variable let = LetterFactory.instance().getVariable(1, Boolean.TRUE);
        Variable let2 = LetterFactory.instance().getVariable(2, Boolean.TRUE);
        Variable let3 = LetterFactory.instance().getVariable(3, Boolean.TRUE);
        QuadraticSystem instance = new QuadraticSystem();
        instance.appendLetter(let);
        assertEquals(0,instance.getEquivalences().size());
        instance.addEquivalence(let2, let);
        assertEquals(1,instance.getEquivalences().size());
        instance.addEquivalence(let3, let);
        assertEquals(2,instance.getEquivalences().size());
    }

    /**
     * Test of getQuadraticEquation method, of class QuadraticSystem.
     */
    @Test
    public void testGetQuadraticEquation() {
        System.out.println("getQuadraticEquation");
        QuadraticSystem instance = new QuadraticSystem();
        boolean expResult = true;
        boolean result = instance.getQuadraticEquation()!=null;
        assertEquals(expResult, result);
    }

    /**
     * Test of getEquivalences method, of class QuadraticSystem.
     */
    @Test
    public void testGetEquivalences() {
        System.out.println("getEquivalences");
        QuadraticSystem instance = new QuadraticSystem();
        boolean expResult = true;
        boolean result = instance.getEquivalences()!=null;
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class QuadraticSystem.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        QuadraticSystem instance = new QuadraticSystem();
        boolean expResult = true;
        boolean result = instance.toString()!=null;
        assertEquals(expResult, result);
    }

}