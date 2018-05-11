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

package equation;

import java.util.Iterator;
import letter.Letter;
import letter.Variable;

/**
 * Enumerates distinct Variables occurring in a GroupWord.
 * 
 * @author grouptheory
 */
public class VariableEnumerator {

    /**
     * Returns an Iterator over the variables in a GroupWord.
     * Each distinct variable will be listed exactly once by the Iterator.
     * 
     * @param eq a GroupWord.
     * @return Iterator over the set of distinct variables.
     */
    public static GroupWord.LetterIterator iteratorVariables(GroupWord eq) {
        GroupWord eq2 = new GroupWord();
        for (Iterator it1=eq.getLetterIterator(); it1.hasNext();) {
            Letter let1 = (Letter)it1.next();
            if (let1 instanceof Variable) {
                boolean exists = false;
                for (Iterator it2=eq2.getLetterIterator(); it2.hasNext();) {
                    Letter let2 = (Letter)it2.next();
                    if (Letter.testEqualOrInverse(let1, let2)) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    if (let1.isPositive())
                        eq2.appendLetter(let1);
                    else
                        eq2.appendLetter(let1.getInverse());
                }
            }
        }
        return eq2.getLetterIterator();
    }
}
