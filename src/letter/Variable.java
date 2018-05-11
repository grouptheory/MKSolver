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
 * A specialized subclass of Letter, which is used to represent
 * an variables whose values can be elements of a free group.  Each Variable
 * exists as a doubleton (one instance for each exponent +1 and -1).
 * Each instance within the doubleton considers the other to be
 * its inverse.  To enforce this, Variables can only
 * instantiated by using the {@link LetterFactory} singleton.
 *
 * @author grouptheory
 */

public class Variable extends Letter {

    /**
     * Overrides the method in the superclass {@link Letter}.
     * @return false, always.
     */
    public Boolean isConstant() {
        return false;
    }

    private Variable _inverse;

    /**
     * @see Letter
     */
    public Letter getInverse() {
        if (Safety.CHECK && _inverse == null) {
            throw new RuntimeException("Variable.getInverse: _inverse == null");
        }
        return _inverse;
    }

    Variable(int id, Boolean positive) {
        super(id);
        setPositive(positive);
    }

    void setInverse(Variable vinv) {
        _inverse = vinv;
    }

    /**
     * Converts this Variable to a String representation.
     * @return the String representation of this Variable.
     */
    public String toString() {
        String s = "";
        s += VARIABLE_SYMBOL;
        s += _id;
        if (this.isPositive()) {
            s += POSITIVE_SYMBOL;
        }
        else {
            s += NEGATIVE_SYMBOL;
        }
        s += ENDOFLETTER_SYMBOL;
        return s;
    }
}

