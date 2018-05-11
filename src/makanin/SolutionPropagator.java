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

import ge.GE;
import java.util.Iterator;
import equation.GroupWord;
import ge.BaseSolver;
import ge.Base;
import ge.BaseSolutionDecorator;

/**
 *
 * @author grouptheory
 */
public class SolutionPropagator {

    private static SolutionPropagator _instance;

    private SolutionPropagator() {
    }

    public static SolutionPropagator instance() {
        if (_instance == null) {
            _instance = new SolutionPropagator();
        }
        return _instance;
    }

    public void propagateToParent(GENode child, GENode parent) {
        if (child.getParent() != parent) {
            throw new RuntimeException("SolutionPropagator.propagateToParent child and parent do not have valid relationship");
        }

        GE geq_child = child.getGE();
        
        for (Iterator it=geq_child.iteratorBases(); it.hasNext();) {
            Base bs = (Base)it.next();
            if (BaseSolver.instance().solve(bs)) {
                GroupWord sol = BaseSolver.instance().getSolution(bs);

                Base bs_parent = child.getCorrespondingParentBase(bs);

                if ((!bs_parent.getLabel().isPositive() &&  bs.getLabel().isPositive()) ||
                    ( bs_parent.getLabel().isPositive() && !bs.getLabel().isPositive())) {
                    sol = sol.inverse();
                }
                BaseSolutionDecorator.attachSolution(bs_parent, sol);

            }
        }
    }
}
