/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cancellation;

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
public class ExtensionTest {

    public ExtensionTest() {
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
     * Test of apply method, of class Extension.
     */
    @Test
    public void testApply() {
        System.out.println("apply");
        Diagram d = null;
        Extension instance = null;
        Diagram expResult = null;
        Diagram result = instance.apply(d);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ExtensionImpl extends Extension {

        public ExtensionImpl() {
            super(null);
        }

        public Diagram apply(Diagram d) {
            return null;
        }
    }

}