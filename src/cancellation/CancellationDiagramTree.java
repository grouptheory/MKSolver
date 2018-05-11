/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cancellation;

import java.util.TreeSet;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Iterator;
import letter.Letter;
import equation.GroupEquation;
import equation.QuadraticSystem;

/**
 *
 * @author grouptheory
 */
public class CancellationDiagramTree 
        extends AbstractCancellationDiagramAnalysis {
    
    private LinkedList _diagrams;
    
    CancellationDiagramTree(GroupEquation problem, QuadraticSystem qs, LinkedList diagrams) {
        super(problem, qs);

        _diagrams = new LinkedList();
        _diagrams.addAll(diagrams);
    }

    int getDiagramTreeNodesCount() {
        return _diagrams.size();
    }

    public Iterator iteratorDiagramTreeNodes() {
        // return _diagrams.iterator();
        return new AbstractCancellationDiagramAnalysis.FilteredIterator(_diagrams.iterator());
    }

    public String toString() {
        String s = "";
        s += "CancellationDiagramTree: ";
        s += super.toString();
        
        s+="\n"+_diagrams.size()+" cancellation diagrams enumerated:\n";
        for (Iterator it = _diagrams.iterator();it.hasNext(); ) {
            DiagramTreeNode dtn2 = (DiagramTreeNode)it.next();
            s+=dtn2.getDiagram().toString();
        }
        s += "\n";
        return s;
    }
}
