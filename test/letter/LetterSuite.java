/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package letter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author grouptheory
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({letter.LetterTest.class,letter.LetterComparatorTest.class,letter.VariableTest.class,letter.LetterFactoryTest.class,letter.ConstantTest.class,letter.LatexAdapterTest.class})
public class LetterSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

}