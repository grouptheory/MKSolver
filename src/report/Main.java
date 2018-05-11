/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package report;

import equation.GroupEquation;
import cancellation.CancellationDiagramTree;
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

        int MAX_SUBTREE = 8;
        MetaReport.create("z1-.c1+.z1+.c1-.", "exp1", MAX_SUBTREE);
        
    }

}
