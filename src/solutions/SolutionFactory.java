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

import equation.GroupWord;
import ge.GE;
import ge.Base;
import ge.BaseSolver;
import ge.GESolver;
import ge.GE;
import makanin.GENode;
import makanin.SolutionPropagator;
import java.util.Iterator;
import letter.Variable;

/**
 *
 * @author grouptheory
 */
public class SolutionFactory {

    private static SolutionFactory _instance;

    private SolutionFactory() {
    }

    public static SolutionFactory instance() {
        if (_instance == null) {
            _instance = new SolutionFactory();
        }
        return _instance;
    }

    public Solution newSolution(GENode node) {
        Solution ss = null;
        
        if (node.isLeaf() && node.isSolvable()) {
            GE geq = node.getGE();

            System.out.println("=========================");
            System.out.println("Found a solution: "+node);
            // System.out.println("GE "+geq);

            GESolver.instance().clearSolution(geq);

            GENode parent;
            do {
                GESolver.instance().computeSolution(geq);

                parent = node.getParent();
                if (parent != null) {
                    GE geq_parent = parent.getGE();
                    GESolver.instance().clearSolution(geq_parent);

                    SolutionPropagator.instance().propagateToParent(node, parent);
                    node = parent;
                    geq = geq_parent;
                }
            }
            while (parent != null);

            ss = new Solution();

            for (Iterator it=geq.iteratorBases(); it.hasNext();) {
                Base bs = (Base)it.next();
                if (BaseSolver.instance().solve(bs)) {
                    if (bs.getLabel().isConstant()) continue;

                    Variable var = (Variable)bs.getLabel();
                    GroupWord sol = BaseSolver.instance().getSolution(bs);
                    ss.put(var, sol);
                }
            }

            System.out.println("Solution: "+ss.toString());
        }
        return ss;
    }
}
