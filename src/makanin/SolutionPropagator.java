/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package makanin;

import ge.GE;
import java.util.Iterator;
import equation.GroupEquation;
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

    void propagateToParent(GENode child, GENode parent) {
        if (child.getParent() != parent) {
            throw new RuntimeException("SolutionPropagator.propagateToParent child and parent do not have valid relationship");
        }

        GE geq_child = child.getGE();
        
        for (Iterator it=geq_child.iteratorBases(); it.hasNext();) {
            Base bs = (Base)it.next();
            if (BaseSolver.instance().solve(bs)) {
                GroupEquation sol = BaseSolver.instance().getSolution(bs);

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
