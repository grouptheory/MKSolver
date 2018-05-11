/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cancellation;

import equation.GroupWord;
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
public class CancellationDiagramFactoryTest {

    public CancellationDiagramFactoryTest() {
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
     * Test of instance method, of class CancellationDiagramFactory.
     */
    @Test
    public void testInstance() {
        System.out.println("instance");
        GroupWord eq = new GroupWord();
        eq.appendLetter(LetterFactory.instance().getVariable(1, Boolean.TRUE));
        eq.appendLetter(LetterFactory.instance().getVariable(2, Boolean.TRUE));
        ICancellationDiagramAnalysis result = 
                CancellationDiagramFactory.instance().newDiagramProbe(eq);
        assertEquals("", result.toString());
    }

    /**
     * Test of newCancellationDiagramAnalysis method, of class CancellationDiagramFactory.
     */
    @Test
    public void testNewCancellationDiagramAnalysis() {
        System.out.println("newCancellationDiagramAnalysis");
        GroupWord eq = new GroupWord();
        eq.appendLetter(LetterFactory.instance().getVariable(1, Boolean.TRUE));
        eq.appendLetter(LetterFactory.instance().getVariable(2, Boolean.TRUE));
        ICancellationDiagramAnalysis result =
                CancellationDiagramFactory.instance().newCancellationDiagramAnalysis(eq);
        int c=0;
        while (result.iteratorDiagramTreeNodes().hasNext()) {
            c++;
        }
        assertEquals("", result.toString());
    }

    /**
     * Test of newDiagramTree method, of class CancellationDiagramFactory.
     */
    @Test
    public void testNewDiagramTree() {
        System.out.println("newDiagramTree");
        GroupWord eq = new GroupWord();
        eq.appendLetter(LetterFactory.instance().getVariable(1, Boolean.TRUE));
        eq.appendLetter(LetterFactory.instance().getVariable(2, Boolean.TRUE));
        ICancellationDiagramAnalysis result =
                CancellationDiagramFactory.instance().newDiagramTree(eq);
        assertEquals("", result.toString());
    }

    /**
     * Test of newDiagramProbe method, of class CancellationDiagramFactory.
     */
    @Test
    public void testNewDiagramProbe() {
        System.out.println("newDiagramProbe");
        GroupWord eq = new GroupWord();
        eq.appendLetter(LetterFactory.instance().getVariable(1, Boolean.TRUE));
        eq.appendLetter(LetterFactory.instance().getVariable(2, Boolean.TRUE));
        ICancellationDiagramAnalysis result =
                CancellationDiagramFactory.instance().newDiagramProbe(eq);
        assertEquals("", result.toString());
    }

}