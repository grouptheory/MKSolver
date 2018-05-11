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
public abstract class AbstractCancellationDiagramAnalysis
    implements ICancellationDiagramAnalysis {

    private GroupEquation _problem;
    private QuadraticSystem _qs;
    private HashMap _equiv;
    private GroupEquation _problemQuadratic;
    private LinkedList _decorators;

    protected AbstractCancellationDiagramAnalysis(GroupEquation problem, QuadraticSystem qs) {
        _problem = problem;
        _qs = qs;

        _problemQuadratic = qs.getEquation();
        _equiv = new HashMap();
        _equiv.putAll(qs.getEquivalences());

        _decorators = new LinkedList();
    }

    public final void addDecorator(ICancellationDiagramAnalysisDecorator dec) {
        _decorators.addLast(dec);
    }

    public final Iterator iteratorDecorators() {
        return _decorators.iterator();
    }

    public final GroupEquation getProblem() {
        return _problem;
    }

    public final QuadraticSystem getQuadraticSystem() {
        return _qs;
    }

    public final GroupEquation getQuadraticEquation() {
        return _problemQuadratic;
    }

    public final HashMap getEquivalences() {
        return _equiv;
    }

    public String toString() {
        String s = "";

        s += ("Quadratic Equation: "+_problemQuadratic+" = 1\n");

        s+="\n\nVariable equivalences:\n\n";
        for (Iterator it=_equiv.keySet().iterator(); it.hasNext();) {
            Letter let = (Letter)it.next();
            s += (let.toString() + "="+ ((Letter)_equiv.get(let)).toString() + "; \n");
        }

        return s;
    }

    static class FilteredIterator implements Iterator {
        private Iterator _it;
        private DiagramTreeNode _nextDTN;

        FilteredIterator(Iterator it) {
            _it = it;
            _nextDTN = nextDTN();
        }
        
        public boolean hasNext() {
            return (_nextDTN!=null);
        }

        public Object next() {
            DiagramTreeNode nextDTN = _nextDTN;
            _nextDTN = nextDTN();
            return nextDTN;
        }

        public void remove() {
            _it.remove();
        }

        private DiagramTreeNode nextDTN() {
            DiagramTreeNode answer = null;

            while (_it.hasNext()) {
                DiagramTreeNode dtn = (DiagramTreeNode)_it.next();
                Diagram nextDiag = dtn.getDiagram();

                if (!DiagramDegeneracyTester.isDegenerate(nextDiag) && dtn.getLeaf()) {
                    answer = dtn;
                    break;
                }
            }

            return answer;
        }
    }
}
