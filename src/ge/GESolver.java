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

package ge;

import java.util.Iterator;
import equation.GroupWord;
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

    public GroupWord getSolution(GE geq, Boundary begin, Boundary end) {
        GroupWord sol = buildSolutionIncremental(geq, begin, end, null);
        return sol;
    }


    private GroupWord buildSolutionIncremental(GE geq, Boundary begin, Boundary end, GroupWord partial) {
        if (partial == null) {
            partial = new GroupWord();
        }

        ConsoleLogger.instance().debug("Solution2", "buildSolutionIncremental ("+begin+","+end+") partial:"+partial);

        for (Iterator it = geq.iteratorBases(); it.hasNext();) {
            Base bs = (Base)it.next();
            if (bs.getBegin()==begin &&
                bs.getEnd().getID() <=end.getID() &&
                BaseSolver.instance().getSolution(bs)!=null) {

                ConsoleLogger.instance().debug("Solution2", "Found a piece in: "+bs);

                GroupWord partialCopy = partial.duplicate();
                GroupWord augmentBy = BaseSolver.instance().getSolution(bs);
                GroupWord partialAugmented = GroupWord.concatenate(partialCopy, augmentBy);
                if (bs.getEnd().getID() < end.getID()) {
                    GroupWord continuation = buildSolutionIncremental(geq,bs.getEnd(),end,partialAugmented);
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
