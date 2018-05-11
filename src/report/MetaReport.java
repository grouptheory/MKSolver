/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package report;


import equation.GroupEquation;
import cancellation.CancellationDiagramTree;
import cancellation.CancellationDiagramFactory;

/**
 *
 * @author grouptheory
 */
public class MetaReport {

    public static void create(String equationAsString, String experimentName, int MAX_SUBTREE) {

        GroupEquation problem = new GroupEquation(equationAsString);

        CancellationDiagramReport report1 =
                new CancellationDiagramReport(experimentName, problem);
        GEPrintsReport report2 =
                new GEPrintsReport(experimentName, problem);
        SolutionReport report3 =
                new SolutionReport(experimentName, problem, MAX_SUBTREE);

        FilePersistenceReportObserver f1 = new FilePersistenceReportObserver();
        FilePersistenceReportObserver f2 = new FilePersistenceReportObserver();
        FilePersistenceReportObserver f3 = new FilePersistenceReportObserver();
        
        report1.attachObserver(f1);
        report2.attachObserver(f2);
        report3.attachObserver(f3);

        report1.create();
        report2.create();
        report3.create();

        f1.close();
        f2.close();
        f3.close();
    }
}
