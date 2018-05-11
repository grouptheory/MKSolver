/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package equation;

import letter.Variable;
import letter.LetterFactory;
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
public class DegreeCalculatorTest {

    public DegreeCalculatorTest() {
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
     * Test of degree method, of class DegreeCalculator.
     */
    @Test
    public void testDegree() {
        System.out.println("degree");

        GroupWord instance = new GroupWord();
        instance.appendLetter(LetterFactory.instance().getVariable(1, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getVariable(1, Boolean.FALSE));
        instance.appendLetter(LetterFactory.instance().getVariable(3, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getVariable(3, Boolean.FALSE));
        instance.appendLetter(LetterFactory.instance().getVariable(2, Boolean.FALSE));
        instance.appendLetter(LetterFactory.instance().getVariable(2, Boolean.TRUE));
        for (int i=1; i<=3; i++) {
            Variable v = LetterFactory.instance().getVariable(i, Boolean.TRUE);
            int result = DegreeCalculator.degree(instance, v);
            assertEquals(0, result);

            v = LetterFactory.instance().getVariable(i, Boolean.FALSE);
            result = DegreeCalculator.degree(instance, v);
            assertEquals(0, result);
        }
    }

    /**
     * Test of maxDegree method, of class DegreeCalculator.
     */
    @Test
    public void testMaxDegree() {
        System.out.println("maxDegree");

        GroupWord instance = new GroupWord();
        instance.appendLetter(LetterFactory.instance().getVariable(1, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getVariable(1, Boolean.FALSE));
        instance.appendLetter(LetterFactory.instance().getVariable(3, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getVariable(3, Boolean.FALSE));
        instance.appendLetter(LetterFactory.instance().getVariable(2, Boolean.FALSE));
        instance.appendLetter(LetterFactory.instance().getVariable(2, Boolean.TRUE));

        int expResult = 0;
        int result = DegreeCalculator.maxDegree(instance);
        assertEquals(expResult, result);
    }

}