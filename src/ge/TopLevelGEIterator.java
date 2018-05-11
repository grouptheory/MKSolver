/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ge;

import equation.GroupEquation;
import equation.QuadraticSystem;
import equation.QuadraticSystemFactory;
import cancellation.Diagram;
import cancellation.DiagramDegeneracyTester;
import cancellation.ComposableDiagramIterator;
import cancellation.DiagramTreeNode;
import utility.CompositeIterator;

/**
 *
 * @author grouptheory
 */
public class TopLevelGEIterator {

    private GroupEquation _eq;
    private QuadraticSystem _qs;
    private GroupEquation _problemQuadratic;
    private Diagram _rootDiagram;
    private ComposableDiagramIterator _cdi;
    private CompositeIterator _compiter;

    private GE _nextGE;
    private boolean _finished;
    
    TopLevelGEIterator(GroupEquation eq) {
        _eq = eq.duplicate();
        _qs = QuadraticSystemFactory.instance().newQuadraticSystem(_eq);
        _problemQuadratic = _qs.getEquation();
        _rootDiagram = new Diagram();
        _cdi = new ComposableDiagramIterator(null, _rootDiagram, _problemQuadratic, 0);
        _compiter = new CompositeIterator(_cdi, false);
        
        _nextGE = nextGE();
    }

    private GE nextGE() {
        boolean finished = false;
        GE geq = null;

        GEDegeneracyTester tester;
        do {
            DiagramTreeNode nextDTN = nextDiagramTreeNode();
            if (nextDTN != null) {
                Diagram nextDiag = nextDTN.getDiagram();
                GEFactory gef = GEFactory.instance();
                geq = gef.newGE(nextDiag, _qs);
            }
            else {
                finished=true;
                break;
            }
            tester = new GEDegeneracyTester(geq);
        }
        while (tester.isDegenerate());

        if (finished) {
            return null;
        }
        else {
            return geq;
        }
    }

    private DiagramTreeNode nextDiagramTreeNode() {
        DiagramTreeNode answer = null;
        while (_compiter.hasNext()) {
            CompositeIterator.State state = (CompositeIterator.State)_compiter.next();
            DiagramTreeNode dtn = (DiagramTreeNode)state.getLeafIteratorState();
            Diagram nextDiag = dtn.getDiagram();

            if (!DiagramDegeneracyTester.isDegenerate(nextDiag) && dtn.getLeaf()) {
                answer = dtn;
                break;
            }
        }
        return answer;
    }
    
    public boolean hasNext() {
        return (_nextGE!=null);
    }

    public Object next() {
        GE nextGE = _nextGE;
        _nextGE = nextGE();
        return nextGE;
    }

    public void remove() {
        throw new RuntimeException("TopLevelGEIterator.remove: not implemented");
    }
}
