/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package equation;

import equation.GroupWord.LetterIterator;
import letter.LetterFactory;
import letter.Letter;
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
public class VariableEnumeratorTest {

    public VariableEnumeratorTest() {
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
     * Test of iteratorVariables method, of class VariableEnumerator.
     */
    @Test
    public void testIteratorVariables() {
        System.out.println("iteratorVariables");
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
        LetterIterator it = VariableEnumerator.iteratorVariables(instance);
        int i=1;
        for (;it.hasNext();) {
            Variable v=(Variable)it.next();
            assertEquals(v.getID(), i);
            i++;
        }
    }

}