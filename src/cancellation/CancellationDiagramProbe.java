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
import utility.CompositeIterator;

/**
 *
 * @author grouptheory
 */
public class CancellationDiagramProbe
        extends AbstractCancellationDiagramAnalysis {
    
    CancellationDiagramProbe(GroupEquation problem, QuadraticSystem qs) {
        super(problem, qs);
    }
    
    public Iterator iteratorDiagramTreeNodes() {
        Diagram d = new Diagram();
        GroupEquation problemQuadratic = getQuadraticEquation();
        ComposableDiagramIterator cdi = new ComposableDiagramIterator(null, d, problemQuadratic, 0);
        CompositeIterator compiter = new CompositeIterator(cdi, true);
        
        // return compiter;
        return new AbstractCancellationDiagramAnalysis.FilteredIterator(compiter);
    }

    public String toString() {
        String s = "";
        s += "CancellationDiagramProbe: ";
        s += super.toString();
        return s;
    }
}
