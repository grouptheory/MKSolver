/*
    Copyright 2008 Bilal Khan
    grouptheory@gmail.com

    This file is part of MKSolver.

    MKSolver is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    MKSolver is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
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
public class ConstantTest {

    public ConstantTest() {
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
     * Test of isConstant method, of class Constant.
     */
    @Test
    public void testIsConstant() {
        System.out.println("isConstant");
        Constant instance = new Constant(1,true);
        Boolean expResult = true;
        Boolean result = instance.isConstant();
        assertEquals(expResult, result);
    }

    /**
     * Test of isPositive method, of class Constant.
     */
    @Test
    public void testIsPositive() {
        System.out.println("isPositive");
        Constant instance = new Constant(1,true);
        Boolean expResult = true;
        Boolean result = instance.isPositive();
        assertEquals(expResult, result);
    }

    @Test
    public void testIsPositiveFail() {
        System.out.println("isPositive");
        Constant instance = new Constant(1,false);
        Boolean expResult = false;
        Boolean result = instance.isPositive();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInverse method, of class Constant.
     */
    @Test
    public void testGetInverse() {
        System.out.println("getInverse");
        Constant instance = LetterFactory.instance().getConstant(1,false);
        Letter inv = instance.getInverse();
        Letter invinv = inv.getInverse();
        assertEquals(instance, invinv);
        assertEquals(inv, invinv.getInverse());
    }

    /**
     * Test of setInverse method, of class Constant.
     */
    @Test
    public void testSetInverse() {
        System.out.println("setInverse");
        Constant cinv = new Constant(1,false);
        Constant c = new Constant(1,true);
        c.setInverse(cinv);
        cinv.setInverse(c);
        assertEquals(c, cinv.getInverse());
        assertEquals(cinv, cinv.getInverse().getInverse());
    }

    /**
     * Test of toString method, of class Constant.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Constant instance = LetterFactory.instance().getConstant(1,false);
        String expResult = "c1-.";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Constant.
     */
    @Test
    public void testToString2() {
        System.out.println("toString");
        Constant instance2 = LetterFactory.instance().getConstant(1,true);
        String expResult2 = "c1+.";
        String result2 = instance2.toString();
        assertEquals(expResult2, result2);
    }
}