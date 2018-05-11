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

import equation.QuadraticSystem;
import equation.QuadraticSystemFactory;
import equation.Equivalences;
import equation.GroupWord;
import java.util.LinkedList;
import java.util.HashMap;
import utility.CompositeIterator;

/**
 * Singleton factory for ICancellationDiagramAnalysis objects.
 *
 * @author grouptheory
 */
public class CancellationDiagramFactory {

    private static CancellationDiagramFactory _instance;

    private CancellationDiagramFactory() {
    }

    /**
     * Singleton getter.
     * 
     * @return the singleton ICancellationDiagramAnalysis
     */
    public static CancellationDiagramFactory instance() {
        if (_instance == null) {
            _instance = new CancellationDiagramFactory();
        }
        return _instance;
    }

    /**
     * Factory method to create new ICancellationDiagramAnalysis.
     * The result is a probe of the space of all possibilities and
     * constructs diagrams lazily, as requested.
     * This method defers to {@link newDiagramProbe}).
     *
     * @param problem quadratic GroupWord that equals 1.
     * @return the ICancellationDiagramAnalysis
     */
    public ICancellationDiagramAnalysis newCancellationDiagramAnalysis(GroupWord problem) {
        return newDiagramProbe(problem);
        // return newDiagramTree(problem);
    }

    /**
     * Factory method to create new ICancellationDiagramAnalysis.
     * The result is a tree of the space of all possibilities, 
     * constructed exhaustively in advance.
     *
     * @param problem quadratic GroupWord that equals 1.
     * @return the ICancellationDiagramAnalysis
     */
    public ICancellationDiagramAnalysis newDiagramTree(GroupWord problem) {

        QuadraticSystem qs;
        qs = QuadraticSystemFactory.instance().newQuadraticSystem(problem);
        GroupWord problemQuadratic = qs.getQuadraticEquation();
        Equivalences equiv = qs.getEquivalences();

        ExtensionVisitor queryvis = new ExtensionVisitor(problemQuadratic);
        Diagram actual = new Diagram();
        DiagramTreeNode root = new DiagramTreeNode(actual);
        root.visitedBy(queryvis);

        DiagramTreeNode.CollectionVisitor resultsvis = new DiagramTreeNode.CollectionVisitor();
        root.visitedBy(resultsvis);

        LinkedList diagrams = resultsvis.getDiagramTreeNodeList();
        
        CancellationDiagramTree analysis = new CancellationDiagramTree(problem, qs, diagrams);
        return analysis;
    }


    /**
     * Factory method to create new ICancellationDiagramAnalysis.
     * The result is a probe of the space of all possibilities and
     * constructs diagrams lazily, as requested.
     *
     * @param problem quadratic GroupWord that equals 1.
     * @return the ICancellationDiagramAnalysis
     */
    public ICancellationDiagramAnalysis newDiagramProbe(GroupWord problem) {
        QuadraticSystem qs = QuadraticSystemFactory.instance().newQuadraticSystem(problem);
        CancellationDiagramProbe analysis = new CancellationDiagramProbe(problem, qs);
        return analysis;
    }
}
