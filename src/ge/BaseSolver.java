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

import equation.GroupWord;
import utility.ConsoleLogger;

/**
 *
 * @author grouptheory
 */
public class BaseSolver {

    private static BaseSolver _instance;

    private BaseSolver() {
    }

    public static BaseSolver instance() {
        if (_instance == null) {
            _instance = new BaseSolver();
        }
        return _instance;
    }

    void clearSolution(Base bs) {
        BaseSolutionDecorator vbsd = (BaseSolutionDecorator)
                bs.lookupDecorator(BaseSolutionDecorator.NAME);
        if (vbsd != null) {
            bs.detachDecorator(vbsd);
        }
    }

    public GroupWord getSolution(Base bs) {
        BaseSolutionDecorator vbsd = (BaseSolutionDecorator)
            bs.lookupDecorator(BaseSolutionDecorator.NAME);
        if (vbsd==null) {
            return null;
        }
        else {
            return vbsd.getSolution();
        }
    }

    public boolean solve(Base bs) {

        if (getSolution(bs) != null) return true;

        GE geq = bs.getOwner().getOwner();

        boolean answer;
        if (bs.isConstant()) {
            ConsoleLogger.instance().debug("Solution", "Const bs:"+bs.toStringShort());
            GroupWord sol = new GroupWord();
            sol.appendLetter(bs.getLabel());
            BaseSolutionDecorator vbsd = new BaseSolutionDecorator(sol);
            bs.attachDecorator(BaseSolutionDecorator.NAME, vbsd);
            answer=true;
        }
        else {
            ConsoleLogger.instance().debug("Solution", "Var bs:"+bs.toStringShort());

            if (bs.isEmpty()) {
                ConsoleLogger.instance().debug("Solution", "Collapsed bs:"+bs.toStringShort());

                if (bs.getUncollapsedBegin()!=null && bs.getUncollapsedEnd()!=null) {
                    ConsoleLogger.instance().debug("Solution", "uncollapsed != null in bs:"+bs.toStringShort());
                    ConsoleLogger.instance().debug("Solution", "_uncollapsedBegin="+bs.getUncollapsedBegin());
                    ConsoleLogger.instance().debug("Solution", "_uncollapsedEnd="+bs.getUncollapsedEnd());

                    answer = GESolver.instance().isSolved(geq, bs.getUncollapsedBegin(), bs.getUncollapsedEnd());

                    if (answer) {
                        ConsoleLogger.instance().debug("Solution", "GE reports isSolved="+answer);
                        
                        GroupWord sol =
                            GESolver.instance().getSolution(geq, bs.getUncollapsedBegin(), bs.getUncollapsedEnd());

                        BaseSolutionDecorator vbsd = new BaseSolutionDecorator(sol);
                        bs.attachDecorator(BaseSolutionDecorator.NAME, vbsd);
                    }
                    else {
                        ConsoleLogger.instance().warn("Solution", "_uncollapsedBegin="+bs.getUncollapsedBegin());
                        ConsoleLogger.instance().warn("Solution", "_uncollapsedEnd="+bs.getUncollapsedEnd());
                        throw new RuntimeException("Base.solve: could not reconstruct solution for bs:"+bs.toStringShort());
                    }
                }
                else {
                    ConsoleLogger.instance().debug("Solution", "uncollapsed == null in bs:"+bs.toStringShort());

                    BaseSolutionDecorator vbsd = (BaseSolutionDecorator)
                            bs.lookupDecorator(BaseSolutionDecorator.NAME);
                    if (vbsd != null) {
                        throw new RuntimeException("Base.solve: voodoo VariableBaseSolutionDecorator!");
                    }
                    else {
                        ConsoleLogger.instance().info("Solution", "Still unable to solve collapsed bs:"+bs.toStringShort());
                        answer = false;
                    }
                }
            }
            else {
                ConsoleLogger.instance().debug("Solution", "Still unable to solve uncollapsed bs:"+bs.toStringShort());
                answer=false;
            }
        }
        return answer;
    }

}
