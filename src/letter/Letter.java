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

/**
 * An abstract class which is used to represent constants and variables.
 * Each Letter exists as a doubleton (one instance for each exponent +1 and -1).
 * Instances within the doubleton considers the other to be
 * its inverse.  To enforce this, each concrete Letter ({@link Constant}
 * and {@link Variable}) can only instantiated by using the {@link LetterFactory}
 * singleton.
 *
 * @author grouptheory
 */

public abstract class Letter {

    static final String VARIABLE_SYMBOL = "z";
    static final String CONSTANT_SYMBOL = "c";
    static final String POSITIVE_SYMBOL = "+";
    static final String NEGATIVE_SYMBOL = "-";
    static final String ENDOFLETTER_SYMBOL = ".";
            
    /**
     * Determines if this Letter is a constant or a variable.
     * 
     * @return true if this is a {@link Constant} and false if it is a {@link Variable}.
     */
    public abstract Boolean isConstant();
 
    private Boolean _pos;

    /**
     * Determines if this Letter is exponent +1 or -1.
     *
     * @return true if this Letter has exponent +1, and false if it has exponent -1.
     * @see Letter
     */
    public final Boolean isPositive() {
        return _pos;
    }
    
    /**
     *
     * @param pos
     */
    protected void setPositive(Boolean pos) {
        _pos = pos;
    }

    /**
     * Returns the Letter which is inverse to this one.  Note that
     * since Letter objects are immutable, each pair of Letter and its inverse,
     * is instantiated at most once.
     * 
     * @return the unique Constant that is the inverse of this one.
     */
    public abstract Letter getInverse();

    /**
     *
     */
    protected int _id;

    /**
     * Creates a new Letter, unbound to any inverse.
     * @param id a strictly positive integer.
     */
    protected Letter(int id) {
        if (Safety.CHECK && id < 0) {
            throw new RuntimeException("Letter.ctor: id < 0");
        }
        _id = id;
    }
    
    /**
     * Gets the ID of this Letter, that is the subscript associated with this letter,
     * and is a strictly positive integer.
     * 
     * @return the integer number subscript.
     */
    public final int getID() {
        return _id;
    }

    /**
     * Tests if two Letters are equal.
     * @param a1 one Letter
     * @param a2 another Letter
     * @return true if and only if they are the same Letter
     */
    public static Boolean testEquals(Letter a1, Letter a2) {
        if (Safety.CHECK && (a1 != a2) && (a1.getID() == a2.getID()) &&
            ((a1.isPositive() && a2.isPositive()) ||
             (!a1.isPositive() && !a2.isPositive())) &&
            ((a1 instanceof Constant && a2 instanceof Constant) ||
             (a1 instanceof Variable && a2 instanceof Variable))) {
            throw new RuntimeException("Letter.testEquals: two instances of Letters with the same ID");
        }
        return (a1==a2);
    }

    /**
     * Tests if two Letters are inverses of each other.
     * @param a1 one Letter
     * @param a2 another Letter
     * @return true if and only if they are the inverses of each other.
     */
    public static Boolean testInverse(Letter a1, Letter a2) {
        return (a1.getInverse()==a2);
    }

    /**
     * Tests if two Letters are the same or inverses of each other.
     * @param a1 one Letter
     * @param a2 another Letter
     * @return true if and only if they either the same or are the inverses of each other.
     */
    public static Boolean testEqualOrInverse(Letter a1, Letter a2) {
        return (testEquals(a1,a2) || testInverse(a1,a2));
    }
}



