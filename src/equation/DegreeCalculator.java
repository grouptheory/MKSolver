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

import letter.Variable;
import java.util.Iterator;

/**
 * Computes degrees of Variables in a GroupWord.
 *
 * @author grouptheory
 */
public class DegreeCalculator {

    /**
     * Computes the degree of a specific Variable in a GroupWord.
     *
     * @param eq the GroupWord.
     * @param v the Variable (sign is irrelevant, only the ID is relevant).
     * @return the degree of v in eq; that is the sum of the
     * number of occurrences in of v (both positive and negative), in eq.
     * Note that if eq is not freely reduced, then the value
     * returned by this method may be strictly smaller than
     * the Variable's frequency.
     */
    public static int degree(GroupWord eq, Variable v) {
        return FrequencyCalculator.frequency(eq.reduce(), v);
    }

    /**
     * Compute the degree of the highest degree Variable in a GroupWord.
     *
     * @param eq the GroupWord.
     * @return the maximum degree.
     */
    public static int maxDegree(GroupWord eq) {
        int maxdeg = 0;
        for (Iterator it = VariableEnumerator.iteratorVariables(eq); it.hasNext();) {
            Variable v = (Variable)it.next();
            int degv = DegreeCalculator.degree(eq, v);
            if (degv>maxdeg) maxdeg=degv;
        }
        return maxdeg;
    }
}
