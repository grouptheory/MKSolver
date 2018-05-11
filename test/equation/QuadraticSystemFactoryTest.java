/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package equation;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import letter.LetterFactory;

/**
 *
 * @author grouptheory
 */
public class QuadraticSystemFactoryTest {

    public QuadraticSystemFactoryTest() {
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
     * Test of instance method, of class QuadraticSystemFactory.
     */
    @Test
    public void testInstance() {
        System.out.println("instance");
        QuadraticSystemFactory expResult = QuadraticSystemFactory.instance();
        QuadraticSystemFactory result = QuadraticSystemFactory.instance();
        assertEquals(expResult, result);
    }

    /**
     * Test of newQuadraticSystem method, of class QuadraticSystemFactory.
     */
    @Test
    public void testNewQuadraticSystem() {
        System.out.println("newQuadraticSystem");
        for (int D=2; D<20;D++) {
            GroupWord instance = new GroupWord();
            instance.appendLetter(LetterFactory.instance().getVariable(1, Boolean.TRUE));
            instance.appendLetter(LetterFactory.instance().getConstant(1, Boolean.TRUE));
            instance.appendLetter(LetterFactory.instance().getVariable(2, Boolean.FALSE));
            instance.appendLetter(LetterFactory.instance().getConstant(2, Boolean.FALSE));
            for (int i=0;i<D;i++) {
                instance.appendLetter(LetterFactory.instance().getVariable(3, Boolean.TRUE));
            }
            instance.appendLetter(LetterFactory.instance().getVariable(2, Boolean.TRUE));
            instance.appendLetter(LetterFactory.instance().getConstant(2, Boolean.TRUE));
            assertEquals(D, FrequencyCalculator.maxFrequency(instance));

            QuadraticSystemFactory qsf = QuadraticSystemFactory.instance();
            QuadraticSystem qs = qsf.newQuadraticSystem(instance);
            GroupWord eq = qs.getQuadraticEquation();
            assertEquals(2, FrequencyCalculator.maxFrequency(eq));
        }
    }

}