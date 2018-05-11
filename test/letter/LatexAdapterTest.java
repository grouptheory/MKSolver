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
public class LatexAdapterTest {

    public LatexAdapterTest() {
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
     * Test of instance method, of class LatexAdapter.
     */
    @Test
    public void testInstance() {
        System.out.println("instance");
        LatexAdapter expResult = LatexAdapter.instance();
        LatexAdapter result = LatexAdapter.instance();
        assertEquals(expResult, result);
    }

    /**
     * Test of render method, of class LatexAdapter.
     */
    @Test
    public void testRender() {
        System.out.println("render");
        Letter let = LetterFactory.instance().getConstant(100, Boolean.FALSE);
        LatexAdapter instance = LatexAdapter.instance();
        String expResult = "c_{100}^{-1}";
        String result = instance.render(let);
        assertEquals(expResult, result);
    }

}