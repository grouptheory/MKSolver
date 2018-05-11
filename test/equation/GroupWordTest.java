/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package equation;

import equation.GroupWord.LetterIterator;
import letter.Letter;
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
public class GroupWordTest {

    public GroupWordTest() {
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
     * Test of duplicate method, of class GroupWord.
     */
    @Test
    public void testDuplicate() {
        System.out.println("duplicate");
        GroupWord instance = new GroupWord();
        instance.appendLetter(LetterFactory.instance().getConstant(1, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getConstant(2, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getVariable(1, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getVariable(2, Boolean.TRUE));
        String expResult = instance.toString();
        String result = instance.duplicate().toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of reduce method, of class GroupWord.
     */
    @Test
    public void testReduce() {
        System.out.println("reduce");
        GroupWord instance = new GroupWord();
        instance.appendLetter(LetterFactory.instance().getConstant(1, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getConstant(1, Boolean.FALSE));
        instance.appendLetter(LetterFactory.instance().getVariable(3, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getConstant(2, Boolean.FALSE));
        instance.appendLetter(LetterFactory.instance().getConstant(2, Boolean.TRUE));
        GroupWord expResult = new GroupWord();
        expResult.appendLetter(LetterFactory.instance().getVariable(3, Boolean.TRUE));
        GroupWord result = instance.reduce();
        assertEquals(expResult.toString(), result.toString());
    }

    /**
     * Test of cyclicallyReduce method, of class GroupWord.
     */
    @Test
    public void testCyclicallyReduce() {
        System.out.println("cyclicallyReduce");
        GroupWord instance = new GroupWord();
        instance.appendLetter(LetterFactory.instance().getConstant(1, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getConstant(2, Boolean.FALSE));
        instance.appendLetter(LetterFactory.instance().getVariable(3, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getConstant(2, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getConstant(1, Boolean.FALSE));
        GroupWord expResult = new GroupWord();
        expResult.appendLetter(LetterFactory.instance().getVariable(3, Boolean.TRUE));
        GroupWord result = instance.cyclicallyReduce();
        assertEquals(expResult.toString(), result.toString());
    }

    /**
     * Test of cyclicallyReduce method, of class GroupWord.
     */
    @Test
    public void testCyclicallyReduce2() {
        System.out.println("cyclicallyReduce2");
        GroupWord instance = new GroupWord();
        instance.appendLetter(LetterFactory.instance().getConstant(1, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getConstant(2, Boolean.FALSE));
        instance.appendLetter(LetterFactory.instance().getVariable(3, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getVariable(3, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getVariable(3, Boolean.FALSE));
        instance.appendLetter(LetterFactory.instance().getConstant(2, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getConstant(1, Boolean.FALSE));
        GroupWord expResult = new GroupWord();
        expResult.appendLetter(LetterFactory.instance().getVariable(3, Boolean.TRUE));
        GroupWord result = instance.cyclicallyReduce();
        assertEquals(expResult.toString(), result.toString());
    }

    /**
     * Test of inverse method, of class GroupWord.
     */
    @Test
    public void testInverse() {
        System.out.println("inverse");
        GroupWord instance = new GroupWord();
        instance.appendLetter(LetterFactory.instance().getConstant(1, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getConstant(2, Boolean.FALSE));
        instance.appendLetter(LetterFactory.instance().getVariable(3, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getVariable(3, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getVariable(3, Boolean.FALSE));
        instance.appendLetter(LetterFactory.instance().getConstant(2, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getConstant(1, Boolean.FALSE));
        String expResult = instance.toString();
        String result = instance.inverse().inverse().toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of concatenate method, of class GroupWord.
     */
    @Test
    public void testConcatenate() {
        System.out.println("concatenate");
        GroupWord instance = new GroupWord();
        instance.appendLetter(LetterFactory.instance().getConstant(1, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getConstant(2, Boolean.FALSE));
        instance.appendLetter(LetterFactory.instance().getVariable(3, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getVariable(3, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getVariable(3, Boolean.FALSE));
        instance.appendLetter(LetterFactory.instance().getConstant(2, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getConstant(1, Boolean.FALSE));
        GroupWord eq2 = instance.inverse();
        GroupWord expResult = new GroupWord();
        GroupWord result = GroupWord.concatenate(instance, eq2).reduce();
        assertEquals(expResult.toString(), result.toString());
    }

    /**
     * Test of graphicallyequal method, of class GroupWord.
     */
    @Test
    public void testGraphicallyequal() {
        System.out.println("graphicallyequal");
        GroupWord instance = new GroupWord();
        instance.appendLetter(LetterFactory.instance().getConstant(1, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getConstant(2, Boolean.FALSE));
        instance.appendLetter(LetterFactory.instance().getVariable(3, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getVariable(3, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getVariable(3, Boolean.FALSE));
        instance.appendLetter(LetterFactory.instance().getConstant(2, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getConstant(1, Boolean.FALSE));
        GroupWord eq2 = new GroupWord();
        eq2.appendLetter(LetterFactory.instance().getConstant(1, Boolean.TRUE));
        eq2.appendLetter(LetterFactory.instance().getConstant(2, Boolean.FALSE));
        eq2.appendLetter(LetterFactory.instance().getVariable(3, Boolean.TRUE));
        eq2.appendLetter(LetterFactory.instance().getVariable(3, Boolean.TRUE));
        eq2.appendLetter(LetterFactory.instance().getVariable(3, Boolean.FALSE));
        eq2.appendLetter(LetterFactory.instance().getConstant(2, Boolean.TRUE));
        eq2.appendLetter(LetterFactory.instance().getConstant(1, Boolean.FALSE));
        boolean expResult = true;
        boolean result = GroupWord.graphicallyequal(instance, eq2);
        assertEquals(expResult, result);
    }

    /**
     * Test of graphicallyequal2 method, of class GroupWord.
     */
    @Test
    public void testGraphicallyequa2() {
        System.out.println("graphicallyequal2");
        GroupWord instance = new GroupWord();
        instance.appendLetter(LetterFactory.instance().getConstant(1, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getConstant(2, Boolean.FALSE));
        instance.appendLetter(LetterFactory.instance().getVariable(3, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getVariable(3, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getVariable(3, Boolean.FALSE));
        instance.appendLetter(LetterFactory.instance().getConstant(2, Boolean.TRUE));
        instance.appendLetter(LetterFactory.instance().getConstant(1, Boolean.FALSE));
        GroupWord eq2 = new GroupWord();
        eq2.appendLetter(LetterFactory.instance().getConstant(1, Boolean.TRUE));
        eq2.appendLetter(LetterFactory.instance().getConstant(2, Boolean.FALSE));
        eq2.appendLetter(LetterFactory.instance().getVariable(3, Boolean.TRUE));
        eq2.appendLetter(LetterFactory.instance().getVariable(3, Boolean.TRUE));
        eq2.appendLetter(LetterFactory.instance().getVariable(3, Boolean.FALSE));
        eq2.appendLetter(LetterFactory.instance().getConstant(2, Boolean.TRUE));
        eq2.appendLetter(LetterFactory.instance().getConstant(1, Boolean.FALSE));
        eq2 = eq2.cyclicallyReduce();
        boolean expResult = false;
        boolean result = GroupWord.graphicallyequal(instance, eq2);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class GroupWord.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        GroupWord instance = new GroupWord();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class GroupWord.
     */
    @Test
    public void testToString2() {
        System.out.println("toString2");
        GroupWord instance = new GroupWord();
        instance.appendLetter(LetterFactory.instance().getConstant(1, Boolean.TRUE));
        String expResult = LetterFactory.instance().getConstant(1, Boolean.TRUE).toString();
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of appendLetter method, of class GroupWord.
     */
    @Test
    public void testAppendLetter() {
        System.out.println("appendLetter");
        GroupWord instance = new GroupWord();
        for (int i = 0; i < 10; i++) {
            Letter let = LetterFactory.instance().getVariable(i, Boolean.TRUE);
            instance.appendLetter(let);
        }
        assertEquals(instance.length(), 10);
    }

    /**
     * Test of length method, of class GroupWord.
     */
    @Test
    public void testLength() {
        System.out.println("length");
        GroupWord instance = new GroupWord();
        for (int i = 0; i < 10; i++) {
            Letter let = LetterFactory.instance().getVariable(i, Boolean.TRUE);
            instance.appendLetter(let);
        }
        assertEquals(instance.length(), 10);
    }

    /**
     * Test of popLetter method, of class GroupWord.
     */
    @Test
    public void testPopLetter() {
        System.out.println("popLetter");
        GroupWord instance = new GroupWord();
        for (int i = 0; i < 10; i++) {
            Letter let = LetterFactory.instance().getVariable(i, Boolean.TRUE);
            instance.appendLetter(let);

            Letter let2=(Letter)instance.popLetter();
            assertEquals(let, let2);
        }
        assertEquals(instance.length(), 0);
    }

    /**
     * Test of getLetterIterator method, of class GroupWord.
     */
    @Test
    public void testGetLetterIterator() {
        System.out.println("getLetterIterator");
        GroupWord instance = new GroupWord();
        for (int i = 0; i < 10; i++) {
            Letter let = LetterFactory.instance().getVariable(i, Boolean.TRUE);
            instance.appendLetter(let);
        }
        LetterIterator it=instance.getLetterIterator();
        int i=0;
        for (;it.hasNext();) {
            Letter let2 = it.next();
            assertEquals(let2.getID(), i);
            i++;
        }
    }

}