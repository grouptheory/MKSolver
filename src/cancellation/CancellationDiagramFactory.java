/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cancellation;

import equation.QuadraticSystem;
import equation.QuadraticSystemFactory;
import equation.GroupEquation;
import java.util.LinkedList;
import java.util.HashMap;
import utility.CompositeIterator;

/**
 *
 * @author grouptheory
 */
public class CancellationDiagramFactory {

    private static CancellationDiagramFactory _instance;

    private CancellationDiagramFactory() {
    }

    public static CancellationDiagramFactory instance() {
        if (_instance == null) {
            _instance = new CancellationDiagramFactory();
        }
        return _instance;
    }

    public ICancellationDiagramAnalysis newCancellationDiagramAnalysis(GroupEquation problem) {
        return newDiagramProbe(problem);
        // return newDiagramTree(problem);
    }

    public ICancellationDiagramAnalysis newDiagramTree(GroupEquation problem) {

        QuadraticSystem qs;
        qs = QuadraticSystemFactory.instance().newQuadraticSystem(problem);
        GroupEquation problemQuadratic = qs.getEquation();
        HashMap equiv = qs.getEquivalences();

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


    public ICancellationDiagramAnalysis newDiagramProbe(GroupEquation problem) {
        QuadraticSystem qs = QuadraticSystemFactory.instance().newQuadraticSystem(problem);
        CancellationDiagramProbe analysis = new CancellationDiagramProbe(problem, qs);
        return analysis;
    }
}
