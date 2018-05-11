/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package equation;

import java.util.Iterator;
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
public class EquivalencesTest {

    public EquivalencesTest() {
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
     * Test of duplicate method, of class Equivalences.
     */
    @Test
    public void testDuplicate() {
        System.out.println("duplicate");
        Equivalences instance = new Equivalences();
        Variable let = LetterFactory.instance().getVariable(1, Boolean.TRUE);
        Variable let2 = LetterFactory.instance().getVariable(2, Boolean.TRUE);
        Variable let3 = LetterFactory.instance().getVariable(3, Boolean.TRUE);
        instance.put(let2, let);
        instance.put(let3, let);
        Equivalences instance2 = instance.duplicate();
        assertEquals(instance.size(), instance2.size());
    }

    /**
     * Test of put method, of class Equivalences.
     */
    @Test
    public void testPut() {
        System.out.println("put");
        Equivalences instance = new Equivalences();
        Variable let = LetterFactory.instance().getVariable(1, Boolean.TRUE);
        Variable let2 = LetterFactory.instance().getVariable(2, Boolean.TRUE);
        Variable let3 = LetterFactory.instance().getVariable(3, Boolean.TRUE);
        instance.put(let2, let);
        assertEquals(1, instance.size());
        instance.put(let3, let);
        assertEquals(2, instance.size());
    }

    /**
     * Test of get method, of class Equivalences.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        Equivalences instance = new Equivalences();
        Variable let = LetterFactory.instance().getVariable(1, Boolean.TRUE);
        Variable let2 = LetterFactory.instance().getVariable(2, Boolean.TRUE);
        Variable let3 = LetterFactory.instance().getVariable(3, Boolean.TRUE);
        instance.put(let2, let);
        instance.put(let3, let);
        assertEquals(let, instance.get(let2));
        assertEquals(let, instance.get(let3));
    }

    /**
     * Test of size method, of class Equivalences.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        Equivalences instance = new Equivalences();
        Variable let = LetterFactory.instance().getVariable(1, Boolean.TRUE);
        Variable let2 = LetterFactory.instance().getVariable(2, Boolean.TRUE);
        Variable let3 = LetterFactory.instance().getVariable(3, Boolean.TRUE);
        instance.put(let2, let);
        instance.put(let3, let);
        assertEquals(2, instance.size());
    }

    /**
     * Test of iteratorVariables method, of class Equivalences.
     */
    @Test
    public void testIteratorVariables() {
        System.out.println("iteratorVariables");
        Equivalences instance = new Equivalences();
        Variable let = LetterFactory.instance().getVariable(1, Boolean.TRUE);
        Variable let2 = LetterFactory.instance().getVariable(2, Boolean.TRUE);
        Variable let3 = LetterFactory.instance().getVariable(3, Boolean.TRUE);
        instance.put(let2, let);
        instance.put(let3, let);
        int i=0;
        Iterator it = instance.iteratorVariables();
        for (;it.hasNext();) {
            Variable letter = (Variable)it.next();
            instance.get(letter);
            i++;
        }
        assertEquals(2, i);
    }
}
