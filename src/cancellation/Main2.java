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
import equation.QuadraticSystem;
import equation.QuadraticSystemFactory;
import utility.CompositeIterator;

/**
 *
 * @author grouptheory
 */
public class Main2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("\nTEST COMPOSABLE ITERATORS:\n");

        GroupWord problem = new GroupWord("z1+.c1+.z2+.c2+.z1-.c3+.z2-.");
        //GroupEquation problem = new GroupEquation("z1+.z3+.z1+.z1+.z3+.z1+.");
        
        System.out.println("Original Equation: "+problem+" = 1\n");

        QuadraticSystem qs = QuadraticSystemFactory.instance().newQuadraticSystem(problem);

        GroupWord problemQuadratic = qs.getQuadraticEquation();
        Diagram d = new Diagram();

        ComposableDiagramIterator cdi = new ComposableDiagramIterator(null, d, problemQuadratic, 0);
        CompositeIterator compiter = new CompositeIterator(cdi, false);

        System.out.println("*********** NEW Analysis: \n\n");

        int good, bad;
        good=bad=0;
        while (compiter.hasNext()) {
            CompositeIterator.State state = (CompositeIterator.State)compiter.next();
            DiagramTreeNode dtn = (DiagramTreeNode)state.getLeafIteratorState();
            Diagram diag = dtn.getDiagram();

            if (diag.getBegin() == diag.getEnd() && dtn.getLeaf()) {
                good++;
                System.out.println("NEW diagram = "+diag);
            }
            else {
                bad++;
            }
        }
        System.out.println("Using ComposableIterators, we found "+good+"/"+bad+" good/bad Diagrams.");

        ICancellationDiagramAnalysis analysis =
                CancellationDiagramFactory.instance().newDiagramTree(problem);

        System.out.println("*********** OLD Analysis: \n\n");
        System.out.println(analysis.toString());
        
        int oldWay = ((CancellationDiagramTree)analysis).getDiagramTreeNodesCount();

        System.out.println("Using ComposableIterators, we found "+good+"/"+bad+" good/bad Diagrams.");
        System.out.println("Using full tree expansion, we found "+oldWay+" Diagrams.");

        //System.out.println("FINAL compiter="+compiter.toString());
    }
}
