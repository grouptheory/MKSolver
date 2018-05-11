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

package makanin;

import equation.GroupWord;
import ge.TopLevelGEIterator;
import ge.TopLevelGEIteratorFactory;
import ge.GE;
import ge.GEFactory;
import ge.CancellationDiagramAnalysis_GEDecorator;
import equation.GroupWord;
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

        GroupWord problem = new GroupWord("z1-.c1+.z1+.c1-.");

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
