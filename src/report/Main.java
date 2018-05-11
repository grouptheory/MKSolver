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

package report;

import equation.GroupWord;
import cancellation.CancellationDiagramFactory;
import utility.ConsoleLogger;

/**
 *
 * @author grouptheory
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // ConsoleLogger.instance().setVerbosity("GENodePriorityQueue",ConsoleLogger.INFO);
        ConsoleLogger.instance().setVerbosity("Solution",ConsoleLogger.WARN);
        ConsoleLogger.instance().setVerbosity("Solution2",ConsoleLogger.WARN);
        ConsoleLogger.instance().setDefaultVerbosity(ConsoleLogger.FATAL);

        int MAX_SUBTREE = 16;
        //MetaReport.create("z1-.c1+.z1+.c1-.", "exp1", MAX_SUBTREE);
        //MetaReport.create("z1+.z1+.c1+.c1+.", "exp1", MAX_SUBTREE);
        //MetaReport.create("z1+.z1+.c1+.c2+.c1+.c2+.", "exp1", MAX_SUBTREE);
        MetaReport.create("z1+.z1+.z1+.c1+.c1+.c1+.", "exp1", MAX_SUBTREE);
        //MetaReport.create("z1+.z1+.z1+.c1+.c2+.c1+.c2+.c1+.c2+.", "exp1", MAX_SUBTREE);
    }

}
