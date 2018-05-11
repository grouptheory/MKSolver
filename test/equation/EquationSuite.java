/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package equation;

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
@Suite.SuiteClasses({equation.VariableEnumeratorTest.class,equation.QuadraticSystemTest.class,equation.EquivalencesTest.class,equation.GroupWordTest.class,equation.FrequencyCalculatorTest.class,equation.DegreeCalculatorTest.class,equation.LatexTest.class,equation.QuadraticSystemFactoryTest.class})
public class EquationSuite {

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