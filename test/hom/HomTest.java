/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hom;

import equation.GroupWord;
import letter.LetterFactory;

import java.util.Iterator;
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
public class HomTest {

    public HomTest() {
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
     * Test of augment method, of class Hom.
     */
    @Test
    public void testAugment() {
        System.out.println("augment");
        Variable v = LetterFactory.instance().getVariable(1, Boolean.TRUE);
        GroupWord homv = new GroupWord();
        homv.appendLetter(LetterFactory.instance().getVariable(1, Boolean.TRUE));
        homv.appendLetter(LetterFactory.instance().getVariable(1, Boolean.TRUE));
        Hom instance = new Hom();
        instance.augment(v, homv);
        assertEquals(1, instance.getNontrivialDomain());

        Variable v2 = LetterFactory.instance().getVariable(2, Boolean.TRUE);
        GroupWord homv2 = new GroupWord();
        homv2.appendLetter(LetterFactory.instance().getVariable(2, Boolean.TRUE));
        homv2.appendLetter(LetterFactory.instance().getVariable(2, Boolean.TRUE));
        instance.augment(v2, homv2);
        assertEquals(2, instance.getNontrivialDomain());
    }

    /**
     * Test of apply method, of class Hom.
     */
    @Test
    public void testApply() {
        System.out.println("apply");
        
        Hom instance = new Hom();

        Variable v = LetterFactory.instance().getVariable(1, Boolean.TRUE);
        GroupWord homv = new GroupWord();
        homv.appendLetter(LetterFactory.instance().getVariable(1, Boolean.TRUE));
        homv.appendLetter(LetterFactory.instance().getVariable(1, Boolean.TRUE));
        //System.out.println("homv = "+homv);
        
        instance.augment(v, homv);
        //System.out.println("instance = "+instance);

        //System.out.println("app1 = "+instance.apply(homv));
        assertEquals(4, instance.apply(homv).length());

        Variable v2 = LetterFactory.instance().getVariable(2, Boolean.TRUE);
        GroupWord homv2 = new GroupWord();
        homv2.appendLetter(LetterFactory.instance().getVariable(2, Boolean.TRUE));
        homv2.appendLetter(LetterFactory.instance().getVariable(2, Boolean.TRUE));
        instance.augment(v2, homv2);
        //System.out.println("hom = "+instance);
        
        //System.out.println("app2 = "+instance.apply(instance.apply(homv)));
        assertEquals(8, instance.apply(instance.apply(homv)).length());
    }

    /**
     * Test of isIdentity method, of class Hom.
     */
    @Test
    public void testIsIdentity() {
        System.out.println("isIdentity");
        Hom instance = new Hom();
        boolean expResult = true;
        boolean result = instance.isIdentity();
        assertEquals(expResult, result);

        Variable v = LetterFactory.instance().getVariable(1, Boolean.TRUE);
        GroupWord homv = new GroupWord();
        homv.appendLetter(LetterFactory.instance().getVariable(1, Boolean.TRUE));
        homv.appendLetter(LetterFactory.instance().getVariable(1, Boolean.TRUE));
        instance.augment(v, homv);
        expResult = false;
        result = instance.isIdentity();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLetterIterator method, of class Hom.
     */
    @Test
    public void testGetLetterIterator() {
        System.out.println("getLetterIterator");
        Hom instance = new Hom();
        Iterator result = instance.getDomainIterator();
        assertEquals(true, result!=null);
    }

    /**
     * Test of compose method, of class Hom.
     */
    @Test
    public void testCompose() {
        System.out.println("compose");
        Hom instance1 = new Hom();
        Variable v = LetterFactory.instance().getVariable(1, Boolean.TRUE);
        GroupWord homv = new GroupWord();
        homv.appendLetter(LetterFactory.instance().getVariable(1, Boolean.TRUE));
        homv.appendLetter(LetterFactory.instance().getVariable(2, Boolean.TRUE));
        instance1.augment(v, homv);

        Hom instance2 = new Hom();
        Variable v2 = LetterFactory.instance().getVariable(1, Boolean.TRUE);
        GroupWord homv2 = new GroupWord();
        homv2.appendLetter(LetterFactory.instance().getVariable(1, Boolean.TRUE));
        homv2.appendLetter(LetterFactory.instance().getVariable(2, Boolean.FALSE));
        instance2.augment(v2, homv2);

        Hom result = Hom.compose(instance2, instance1);

        assertEquals(false, instance1.isIdentity());
        assertEquals(false, instance2.isIdentity());
        assertEquals(true, result.isIdentity());
    }

}