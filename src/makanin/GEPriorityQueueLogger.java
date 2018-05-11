/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package makanin;

import java.util.Iterator;
import ge.Base;
import ge.GE;
import ge.BaseSolver;
import ge.GESolver;
import equation.GroupEquation;
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
                    GroupEquation sol = BaseSolver.instance().getSolution(bs);
                    ss.put(var, sol);
                }
            }

            System.out.println("Solution: "+ss.toString());
        }
    }
}
