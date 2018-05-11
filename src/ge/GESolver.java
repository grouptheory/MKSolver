/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ge;

import java.util.Iterator;
import equation.GroupEquation;
import letter.Letter;
import utility.ConsoleLogger;

/**
 *
 * @author grouptheory
 */
public class GESolver {

    private static GESolver _instance;

    private GESolver() {
    }

    public static GESolver instance() {
        if (_instance == null) {
            _instance = new GESolver();
        }
        return _instance;
    }

    public void computeSolution(GE geq) {
        for (Iterator it = geq.iteratorBases(); it.hasNext();) {
            Base bs = (Base)it.next();
            if ( ! bs.isConstant()) continue;
            BaseSolver.instance().solve(bs);
        }
        for (Iterator it = geq.iteratorBases(); it.hasNext();) {
            Base bs = (Base)it.next();
            if (bs.isConstant()) continue;
            BaseSolver.instance().solve(bs);
        }
    }

    public void clearSolution(GE geq) {
        for (Iterator it = geq.iteratorBases(); it.hasNext();) {
            Base bs = (Base)it.next();
            BaseSolver.instance().clearSolution(bs);
        }
    }

    boolean isSolved(GE geq, Boundary begin, Boundary end) {
        return getSolution(geq, begin, end) != null;
    }

    public GroupEquation getSolution(GE geq, Boundary begin, Boundary end) {
        GroupEquation sol = buildSolutionIncremental(geq, begin, end, null);
        return sol;
    }


    private GroupEquation buildSolutionIncremental(GE geq, Boundary begin, Boundary end, GroupEquation partial) {
        if (partial == null) {
            partial = new GroupEquation();
        }

        ConsoleLogger.instance().debug("Solution2", "buildSolutionIncremental ("+begin+","+end+") partial:"+partial);

        for (Iterator it = geq.iteratorBases(); it.hasNext();) {
            Base bs = (Base)it.next();
            if (bs.getBegin()==begin &&
                bs.getEnd().getID() <=end.getID() &&
                BaseSolver.instance().getSolution(bs)!=null) {

                ConsoleLogger.instance().debug("Solution2", "Found a piece in: "+bs);

                GroupEquation partialCopy = partial.duplicate();
                GroupEquation augmentBy = BaseSolver.instance().getSolution(bs);
                GroupEquation partialAugmented = GroupEquation.concatenate(partialCopy, augmentBy);
                if (bs.getEnd().getID() < end.getID()) {
                    GroupEquation continuation = buildSolutionIncremental(geq,bs.getEnd(),end,partialAugmented);
                    if (continuation != null) {
                        return continuation;
                    }
                }
                else { // bs.getEnd().getID() == end.getID()
                    return partialAugmented;
                }
            }
        }
        return null;
    }
}
