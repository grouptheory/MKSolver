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
public class LetterComparatorTest {

    public LetterComparatorTest() {
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
     * Test of compare method, of class LetterComparator.
     */
    @Test
    public void testCompare() {
        System.out.println("compare");
        Object [] v = new Object[8];
        v[0] = LetterFactory.instance().getVariable(1, Boolean.FALSE);
        v[1] = LetterFactory.instance().getVariable(1, Boolean.TRUE);
        v[2] = LetterFactory.instance().getVariable(2, Boolean.FALSE);
        v[3] = LetterFactory.instance().getVariable(2, Boolean.TRUE);
        v[4] = LetterFactory.instance().getConstant(1, Boolean.FALSE);;
        v[5] = LetterFactory.instance().getConstant(1, Boolean.TRUE);
        v[6] = LetterFactory.instance().getConstant(2, Boolean.FALSE);
        v[7] = LetterFactory.instance().getConstant(2, Boolean.TRUE);

        LetterComparator instance = new LetterComparator();

        for (int i=0; i<8; i++) {
           for (int j=0; j<8; j++){
               int result = instance.compare(v[i], v[j]);
               int expResult = (i<j)?-1:(i>j?+1:0);
                assertEquals(expResult, result);
           }
        }
    }

}