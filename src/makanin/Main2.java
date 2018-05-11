/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package makanin;

import equation.GroupEquation;
import ge.TopLevelGEIterator;
import ge.TopLevelGEIteratorFactory;
import ge.GE;
import ge.GEFactory;
import ge.CancellationDiagramAnalysis_GEDecorator;
import equation.GroupEquation;
import equation.QuadraticSystem;
import cancellation.Diagram;
import cancellation.DiagramTreeNode;
import cancellation.ICancellationDiagramAnalysis;
import cancellation.CancellationDiagramFactory;
import java.util.Iterator;

/**
 *
 * @author grouptheory
 */
public class Main2 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        GroupEquation problem = new GroupEquation("z1-.c1+.z1+.c1-.");

        ICancellationDiagramAnalysis analysis;
        analysis = CancellationDiagramFactory.instance().newDiagramProbe(problem);

        analysis.addDecorator(new CancellationDiagramAnalysis_GEDecorator());


        int i=1;
        for (Iterator it = analysis.iteratorDiagramTreeNodes(); it.hasNext();) {
            DiagramTreeNode dtn = (DiagramTreeNode)it.next();

            QuadraticSystem qs = analysis.getQuadraticSystem();
            Diagram d = dtn.getDiagram();

            GEFactory gef = GEFactory.instance();
            GE geq = gef.newGE(d, qs);

            String name = "root"+i;
            
            GENode gnode = new GENode(geq, name);
            GENodePriorityQueue pq = new GENodePriorityQueue();
            pq.attachObserver(new GEPriorityQueueLogger());
            
            pq.enqueue(gnode);

            while (!pq.empty()) {
                pq.step();
            }

            i++;
            
            //break;
        }

        System.out.println("Total number of GEs generated: "+i+"\n");
    }
}
