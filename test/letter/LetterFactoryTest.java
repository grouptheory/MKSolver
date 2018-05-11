/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package letter;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Iterator;
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
public class LetterFactoryTest {

    public LetterFactoryTest() {
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
     * Test of instance method, of class LetterFactory.
     */
    @Test
    public void testInstance() {
        System.out.println("instance");
        LetterFactory expResult = LetterFactory.instance();
        LetterFactory result = LetterFactory.instance();
        assertEquals(expResult, result);
    }

    /**
     * Test of parse method, of class LetterFactory.
     */
    @Test
    public void testParse() {
        System.out.println("parse");
        Letter x;
         x = LetterFactory.instance().getConstant(1, Boolean.TRUE);
        String str = x.toString();
         x = LetterFactory.instance().getVariable(1, Boolean.TRUE);
        str += x.toString();
         x = LetterFactory.instance().getConstant(1, Boolean.FALSE);
        str += x.toString();
          x = LetterFactory.instance().getVariable(1, Boolean.FALSE);
        str += x.toString();

        String expResult = "";
        LinkedList result = LetterFactory.instance().parse(str);

        for (Iterator it=result.iterator(); it.hasNext();) {
            Letter y = (Letter)it.next();
            expResult += y.toString();
        }

        assertEquals(expResult, str);
    }

    /**
     * Test of newUnusedConstant method, of class LetterFactory.
     */
    @Test
    public void testNewUnusedConstant() {
        System.out.println("newUnusedConstant");
        Boolean pos = true;
        LetterFactory instance = LetterFactory.instance();
        Constant result = instance.newUnusedConstant(pos);
        Constant expResult = instance.newUnusedConstant(!pos);
        assertEquals(true, expResult.getID()==result.getID()+1);
    }

    /**
     * Test of getConstant method, of class LetterFactory.
     */
    @Test
    public void testGetConstant() {
        System.out.println("getConstant");
        int id = 1;
        Boolean pos = true;
        LetterFactory instance = LetterFactory.instance();
        Constant expResult = instance.getConstant(id, pos);
        Constant result = instance.getConstant(id, pos);
        assertEquals(expResult, result);
    }

    /**
     * Test of getConstant method, of class LetterFactory.
     */
    @Test
    public void testGetConstant2() {
        System.out.println("getConstant2");
        int id = 1;
        Boolean pos = false;
        LetterFactory instance = LetterFactory.instance();
        Constant expResult = instance.getConstant(id, pos);
        Constant result = instance.getConstant(id, pos);
        assertEquals(expResult, result);
    }

    /**
     * Test of newUnusedVariable method, of class LetterFactory.
     */
    @Test
    public void testNewUnusedVariable() {
        System.out.println("newUnusedVariable");
        Boolean pos = true;
        LetterFactory instance = LetterFactory.instance();
        Variable expResult = instance.newUnusedVariable(pos);
        Variable result = instance.newUnusedVariable(pos);
        assertEquals(true, expResult.getID()+1==result.getID());
    }

    /**
     * Test of getUnusedVariable method, of class LetterFactory.
     */
    @Test
    public void testGetUnusedVariable() {
        System.out.println("getUnusedVariable");
        HashSet used = new HashSet();
        int minID = 100;
        Boolean pos = true;
        LetterFactory instance = LetterFactory.instance();
        Variable expResult = instance.getUnusedVariable(used, minID, pos);
        assertEquals(expResult.getID()==100, true);
        used.add(expResult);
        Variable result = instance.getUnusedVariable(used, minID, pos);
        assertEquals(result.getID()==101, true);
    }

    /**
     * Test of getVariable method, of class LetterFactory.
     */
    @Test
    public void testGetVariable() {
        System.out.println("getVariable");
        int id = 1;
        Boolean pos = true;
        LetterFactory instance = LetterFactory.instance();
        Variable expResult = instance.getVariable(id, pos);
        Variable result = instance.getVariable(id, pos);
        assertEquals(expResult, result);
    }

    /**
     * Test of getVariable method, of class LetterFactory.
     */
    @Test
    public void testGetVariable2() {
        System.out.println("getVariable2");
        int id = 1;
        Boolean pos = false;
        LetterFactory instance = LetterFactory.instance();
        Variable expResult = instance.getVariable(id, pos);
        Variable result = instance.getVariable(id, pos);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class LetterFactory.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        LetterFactory instance = LetterFactory.instance();
        instance.getVariable(1, true);
        String expResult = "";
        String result = instance.toString();
        assertEquals(true, result.toString().length() > 0);
    }

}