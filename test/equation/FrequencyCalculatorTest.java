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
public class FrequencyCalculatorTest {

    public FrequencyCalculatorTest() {
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
     * Test of frequency method, of class FrequencyCalculator.
     */
    @Test
    public void testFrequency() {
        System.out.println("frequency");
        GroupWord instance = new GroupWord();
        instance.appendLetter(LetterFactory.instance().getVariable(1, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getConstant(1, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getVariable(2, Boolean.FALSE));
        instance.appendLetter(LetterFactory.instance().getConstant(2, Boolean.FALSE));
        instance.appendLetter(LetterFactory.instance().getVariable(3, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getConstant(3, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getVariable(3, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getConstant(3, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getVariable(3, Boolean.FALSE));
        instance.appendLetter(LetterFactory.instance().getConstant(3, Boolean.FALSE));
        instance.appendLetter(LetterFactory.instance().getVariable(2, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getConstant(2, Boolean.TRUE));
        for (int i=1; i<=3; i++) {
            Variable v = LetterFactory.instance().getVariable(i, Boolean.TRUE);
            int result = FrequencyCalculator.frequency(instance, v);
            assertEquals(i, result);

            v = LetterFactory.instance().getVariable(i, Boolean.FALSE);
            result = FrequencyCalculator.frequency(instance, v);
            assertEquals(i, result);
        }
    }

    /**
     * Test of maxFrequency method, of class FrequencyCalculator.
     */
    @Test
    public void testMaxFrequency() {
        System.out.println("maxFrequency");
        GroupWord instance = new GroupWord();
        instance.appendLetter(LetterFactory.instance().getVariable(1, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getConstant(1, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getVariable(2, Boolean.FALSE));
        instance.appendLetter(LetterFactory.instance().getConstant(2, Boolean.FALSE));
        instance.appendLetter(LetterFactory.instance().getVariable(3, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getConstant(3, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getVariable(3, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getConstant(3, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getVariable(3, Boolean.FALSE));
        instance.appendLetter(LetterFactory.instance().getConstant(3, Boolean.FALSE));
        instance.appendLetter(LetterFactory.instance().getVariable(2, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getConstant(2, Boolean.TRUE));
        int expResult = 3;
        int result = FrequencyCalculator.maxFrequency(instance);
        assertEquals(expResult, result);
    }

}