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

package makanin;

import java.util.Iterator;
import ge.Base;
import ge.GE;
import ge.BaseSolver;
import ge.GESolver;
import equation.GroupWord;
import solutions.Solution;
import letter.Variable;

/**
 *
 * @author grouptheory
 */
public class GEPriorityQueueLogger implements IGENodePriorityQueueObserver {
    public void notify(GENodePriorityQueue q, GENode node) {
        
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

            Solution ss = new Solution();

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
    }
}
