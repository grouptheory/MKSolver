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

/**
 *
 * @author grouptheory
 */
public class MetaReport {

    public static void create(String equationAsString, String experimentName, int MAX_SUBTREE) {

        GroupWord problem = new GroupWord(equationAsString);
/*
        CancellationDiagramReport report1 =
                new CancellationDiagramReport(experimentName, problem);
        FilePersistenceReportObserver f1 = new FilePersistenceReportObserver();
        ConsoleReportObserver c1 = new ConsoleReportObserver();
        report1.attachObserver(f1);
        report1.attachObserver(c1);
        report1.create();
        f1.close();


        GEPrintsReport report2 =
                new GEPrintsReport(experimentName, problem);
        FilePersistenceReportObserver f2 = new FilePersistenceReportObserver();
        ConsoleReportObserver c2 = new ConsoleReportObserver();
        report2.attachObserver(f2);
        report2.attachObserver(c2);
        report2.create();
        f2.close();
*/

        SolutionReport report3 =
                new SolutionReport(experimentName, problem, MAX_SUBTREE);
        FilePersistenceReportObserver f3 = new FilePersistenceReportObserver();
        ConsoleReportObserver c3 = new ConsoleReportObserver();
        report3.attachObserver(f3);
        report3.attachObserver(c3);
        report3.create();
        f3.close();

    }
}
