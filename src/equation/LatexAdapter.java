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
import java.util.HashMap;
import letter.Letter;
import letter.Variable;

/**
 * Converts instances of GroupWord to latex formatted text.
 *
 * @author grouptheory
 */
public class LatexAdapter {

    private static LatexAdapter _instance;

    private LatexAdapter() {
    }

    /**
     * Returns the singleton instance of the LatexAdapter class.
     * @return LatexAdapter instance.
     */
    public static LatexAdapter instance() {
        if (_instance == null) {
            _instance = new LatexAdapter();
        }
        return _instance;
    }

    /**
     * Convert a GroupWord into Latex.
     *
     * @param eq a GroupWord
     * @return a String
     */
    public String renderGroupEquationLHS(GroupWord eq) {
        String s = "";
        for (GroupWord.LetterIterator it = eq.getLetterIterator(); it.hasNext();) {
            Letter let = it.next();
            s += letter.LatexAdapter.instance().render(let);
        }
        return s;
    }

    /**
     * Convert a GroupWord equation into Latex.
     *
     * @param eq a GroupWord
     * @return a String
     */
    public String renderGroupEquation(GroupWord eq) {
        String s = renderGroupEquationLHS(eq);
        s += "=_F 1";
        return s;
    }

    /**
     * Convert a QuadraticSystem equation into Latex.
     *
     * @param qs a QuadraticSystem
     * @return a String
     */
    public String renderQuadraticSystem(QuadraticSystem qs) {
        String s = "";
        s += equation.LatexAdapter.instance().renderGroupEquation(qs.getQuadraticEquation());
        Equivalences equiv = qs.getEquivalences();
        if (equiv.size()>0) {
            s += " $, where $";
            for (Iterator it=equiv.iteratorVariables(); it.hasNext();) {
                Variable let = (Variable)it.next();
                Variable leteq = (Variable)equiv.get(let);

                s += letter.LatexAdapter.instance().render(let);
                s += "=";
                s += letter.LatexAdapter.instance().render(leteq);
                if (it.hasNext()) s+= ", ";
            }
        }
        s += ".";
        return s;
    }
}
