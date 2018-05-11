/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package equation;

import letter.LetterFactory;
import letter.Letter;
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
public class LatexTest {

    public LatexTest() {
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
     * Test of instance method, of class Latex.
     */
    @Test
    public void testInstance() {
        System.out.println("instance");
        LatexAdapter expResult = LatexAdapter.instance();
        LatexAdapter result = LatexAdapter.instance();
        assertEquals(expResult, result);
    }

    /**
     * Test of renderGroupEquationLHS method, of class Latex.
     */
    @Test
    public void testRenderGroupEquationLHS() {
        System.out.println("renderGroupEquationLHS");
        GroupWord eq = new GroupWord();
        LatexAdapter instance = LatexAdapter.instance();
        Letter let = LetterFactory.instance().getVariable(1, Boolean.TRUE);
        eq.appendLetter(let);
        String expResult = letter.LatexAdapter.instance().render(let);
        String result = instance.renderGroupEquationLHS(eq);
        //System.out.println("result: "+result);
        assertEquals(expResult, result);
    }

    /**
     * Test of renderGroupEquation method, of class Latex.
     */
     @Test
    public void testRenderGroupEquation() {
        System.out.println("renderGroupEquation");
        GroupWord eq = new GroupWord();
        LatexAdapter instance = LatexAdapter.instance();
        Letter let = LetterFactory.instance().getVariable(1, Boolean.TRUE);
        eq.appendLetter(let);
        String expResult = letter.LatexAdapter.instance().render(let)+"=_F 1";
        String result = instance.renderGroupEquation(eq);
        //System.out.println("result: "+result);
        assertEquals(expResult, result);
    }

    /**
     * Test of renderQuadraticSystem method, of class Latex.
     */
    @Test
    public void testRenderQuadraticSystem() {
        System.out.println("renderQuadraticSystem");
        GroupWord eq = new GroupWord();
        LatexAdapter instance = LatexAdapter.instance();
        Letter let = LetterFactory.instance().getVariable(1, Boolean.TRUE);
        eq.appendLetter(let);
        eq.appendLetter(let);
        eq.appendLetter(let);
        String expResult = "z_{1}z_{1}z_{0}=_F 1 $, where $z_{1}=z_{0}.";
        QuadraticSystem qs = QuadraticSystemFactory.instance().newQuadraticSystem(eq);
        String result = instance.renderQuadraticSystem(qs);
        //System.out.println("result: "+result);
        assertEquals(expResult, result);
    }
}