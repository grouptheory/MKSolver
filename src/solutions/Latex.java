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

package solutions;

import java.util.Iterator;
import letter.Variable;
import solutions.Solution;
import equation.GroupWord;

/**
 *
 * @author grouptheory
 */
public class Latex {

    private static Latex _instance;

    private Latex() {
    }

    public static Latex instance() {
        if (_instance == null) {
            _instance = new Latex();
        }
        return _instance;
    }

    public String renderSolution(Solution ss) {
        String s = "";
        s += "\\begin{center}\n";
        s += "\\begin{tabular}{|l|l|}\n";
        s += "\\hline\n";
        s += "variable & value\\\\";
        s += "\\hline\n";
        for (Iterator it=ss.iteratorVariables(); it.hasNext();) {
            Variable v = (Variable)it.next();
            GroupWord val = ss.get(v);
            s += "$";
            s += letter.LatexAdapter.instance().render(v);
            s += "$";
            s += " & ";
            s += "$";
            s += equation.LatexAdapter.instance().renderGroupEquationLHS(val);
            s += "$";
            s += "\\\\\n";
        }
        s += "\\hline\n";
        s += "\\end{tabular}\n";
        s += "\\end{center}\n";
        s += "The above table shows the values of the solution, as obtained by tracing upwards from \n";
        s += "this trivially true GE, to the root of the Makanin-Razborov tree.";
        return s;
    }
}
