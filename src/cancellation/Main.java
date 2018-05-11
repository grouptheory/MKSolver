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

package cancellation;

import java.util.Iterator;
import letter.LetterFactory;
import letter.Letter;
import equation.GroupWord;
import report.SimpleReport;
import report.ConsoleReportObserver;
import report.FilePersistenceReportObserver;
import report.IReport;

/**
 *
 * @author grouptheory
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        /*
        Diagram d = new Diagram();

        Node n0 = d.getBegin();
        Node n1 = d.addNode();
        Node n2 = d.addNode();
        Node n3 = d.addNode();
        Node n4 = d.addNode();
        Node n5 = d.addNode();
        Edge e1 = d.addEdge(n0, n1);
        Edge e2 = d.addEdge(n1, n2);
        Edge e3 = d.addEdge(n2, n3);
        Edge e4 = d.addEdge(n3, n4);
        Edge e5 = d.addEdge(n4, n5);
        Edge e6 = d.addEdge(n5, n0);
        
        System.out.println("\nTEST diagram construction:\n");
        System.out.println("d: "+d);

        Node dst = n0;
        BFS b1 = new BFS(d,dst);

        System.out.println("\nTEST node path routing:\n");
        Iterator srcNodeIt = b1.reachableNodesIterator();
        for ( ;srcNodeIt.hasNext(); ) {
            Node src = (Node)srcNodeIt.next();
            Path p1 = b1.getPathFrom(src);
            System.out.println("from: "+src+" to: "+dst+" === path: "+p1);
        }

        System.out.println("\nTEST edge path routing:\n");
        Iterator srcEdgeIt = b1.reachableEdgesIterator();
        for ( ;srcEdgeIt.hasNext(); ) {
            Edge e = (Edge)srcEdgeIt.next();
            Path p1 = b1.getPathFrom(e);
            System.out.println("from: "+e+" to: "+dst+" === path: "+p1);
        }
        
        System.out.println("\nTEST extensions by variable:\n");
        Letter varlet = LetterFactory.instance().getVariable(1, Boolean.TRUE);
        ExtensionIterator eit1 = ExtensionIteratorFactory.instance().newExtensionIterator(d, varlet);
        System.out.println(eit1.toString());

        System.out.println("\nTEST extensions by constant:\n");
        Letter constlet = LetterFactory.instance().getConstant(1, Boolean.TRUE);
        ExtensionIterator eit2 = ExtensionIteratorFactory.instance().newExtensionIterator(d, constlet);
        System.out.println(eit2.toString());

        System.out.println("\nTEST node path routing from "+n2+" pre Diagram edge cut\n");
        Path px = b1.getPathFrom(n2);
        System.out.println("pre cut:\n"+px);

        Edge ex = d.lookupEdge(n1.getID(), n2.getID());
        System.out.println("\nTEST cutting Diagram edge "+ex+"\n");
        System.out.println("pre cut:\n"+d);
        Node newNode = d.cutEdge(ex);
        System.out.println("post cut:\n"+d);

        System.out.println("\nTEST cloning cut Diagram:\n");
        System.out.println("d: "+d);
        Diagram dcopy = new Diagram(d);
        System.out.println("dcopy: "+dcopy);

        System.out.println("\nTEST node path routing from "+n2+" post Diagram edge cut "+ex+"\n");
        BFS b2 = new BFS(d,n0);
        px = b2.getPathFrom(n2);
        System.out.println("post cut:\n"+px);

        System.out.println("\nTEST cloning post path routing:\n");
        System.out.println("d: "+d);
        Diagram d2 = new Diagram(d);
        System.out.println("d2: "+d2);

        System.out.println("\nTEST path projection\n");
        System.out.println("path in d:\n"+px);
        Path px2 = Path.project(px, d2);
        System.out.println("path in d2:\n"+px2);

        System.out.println("\nTEST adding labeled path\n");
        System.out.println("pre add:\n");
        System.out.println("d2: "+d2);
        d2.addLabeledPath(new LabeledPath(varlet, px2));
        System.out.println("\npost addition: "+px+"\n");
        System.out.println("d2: "+d2);
        
        System.out.println("\nTEST cloning with labelled paths:\n");
        Diagram d3 = new Diagram(d2);
        System.out.println("d3: "+d3);

        Diagram tree = new Diagram();

        Node v0 = tree.getBegin();
        Node v1 = tree.addNode();
        Node v2 = tree.addNode();
        Node v3 = tree.addNode();
        Edge f1 = tree.addEdge(v0, v1);
        Edge f2 = tree.addEdge(v1, v2);
        Edge f3 = tree.addEdge(v2, v3);

        System.out.println("\nTEST DiagramTreeNode:\n");
        DiagramTreeNode dtn = new DiagramTreeNode(tree);
        dtn.visitedBy(new DiagramTreeNode.ConsoleVisitor());
        
        System.out.println("\nTEST DiagramTreeNode var extension\n");
        dtn.extend(varlet);
        dtn.visitedBy(new DiagramTreeNode.ConsoleVisitor());
        System.out.println("\nThere should be 3+4+3+3=13 (i.e. 0-12)\n");


        System.out.println("\nTEST DiagramTreeNode off same diagram:\n");
        DiagramTreeNode dtn2 = new DiagramTreeNode(tree);
        System.out.println("\nOriginal:\n");
        dtn.visitedBy(new DiagramTreeNode.ConsoleVisitor());
        System.out.println("\nNew:\n");
        dtn2.visitedBy(new DiagramTreeNode.ConsoleVisitor());

        System.out.println("\nTEST DiagramTreeNode const extension\n");
        dtn2.extend(constlet);
        dtn2.visitedBy(new DiagramTreeNode.ConsoleVisitor());
        System.out.println("\nThere should be 1+1+1 (i.e. 0-2)\n");


        

        GroupEquation eqn = new GroupEquation("z1+.c1+.z2+.");
        DiagramTreeNode.ExtensionVisitor vis = new DiagramTreeNode.ExtensionVisitor(eqn, 3);
        
        System.out.println("\nTEST Another DiagramTreeNode off same diagram:\n");
        DiagramTreeNode dtn3 = new DiagramTreeNode(tree);
        System.out.println("\nTree before:\n");
        dtn3.visitedBy(new DiagramTreeNode.ConsoleVisitor());

        dtn3.visitedBy(vis);

        System.out.println("\nTree after:\n");
        dtn3.visitedBy(new DiagramTreeNode.ConsoleVisitor());

        //*************************************************
        //*************************************************

        System.out.println("\nTEST Real-world EQUATION:\n");

        GroupEquation problem = new GroupEquation("z1+.c1+.z2+.");
        System.out.println("equation: "+problem+" = 1\n");

        DiagramTreeNode.ExtensionVisitor queryvis = new DiagramTreeNode.ExtensionVisitor(problem);
        Diagram actual = new Diagram();
        DiagramTreeNode root = new DiagramTreeNode(actual);

        root.visitedBy(queryvis);

        DiagramTreeNode.CollectionVisitor resultsvis = new DiagramTreeNode.CollectionVisitor();
        root.visitedBy(resultsvis);
        
        System.out.println("\nTEST Real-world test RESULTS\n");
        System.out.println(""+resultsvis.toString());

*/
        
        //*************************************************
        //*************************************************

        System.out.println("\nTEST Real-world EQUATION TEST:\n");

        GroupWord prob = new GroupWord("z1+.c1+.z1+.c2+.z1+.");
        System.out.println("Original Equation: "+prob+" = 1\n");

        ICancellationDiagramAnalysis analysis =
                CancellationDiagramFactory.instance().newDiagramTree(prob);
        
        System.out.println("Analysis: \n\n");
        System.out.println(analysis.toString());

        IReport report = new SimpleReport("Output");
        report.attachObserver(new ConsoleReportObserver());

        LatexAdapter.instance().renderCancellationDiagramAnalysis(report, analysis);
    }
}
