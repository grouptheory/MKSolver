/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cancellation;

import utility.AbstractComposableIterator;
import utility.ComposableIterator;
import equation.GroupEquation;
import letter.Letter;

/**
 *
 * @author grouptheory
 */
public class ComposableDiagramIterator
        extends AbstractComposableIterator
        implements ComposableIterator {

    private Diagram _d;
    private Letter _let;
    private GroupEquation _subeq;
    private ExtensionIterator _eiReading;
    private ExtensionIterator _eiChildren;
    private int _IAmChildNumber;
    private int _YouAreChildNumber;

    public ComposableDiagramIterator(ComposableIterator parent, Diagram d, GroupEquation eq, int childNumber) {
        setParent(parent);
        
        _d = d;

        if (eq.length() > 0) {
            GroupEquation eq2 = eq.duplicate();
            _let = eq2.popLetter();
            _subeq = eq2;

            if (_subeq.length() > 0) {
                _eiReading = ExtensionIteratorFactory.instance().newExtensionIterator(_d, _let);
                _eiChildren = ExtensionIteratorFactory.instance().newExtensionIterator(_d, _let);
            }
            else {
                _eiReading = ExtensionIteratorFactory.instance().newExtensionIteratorLast(_d, _let);
                _eiChildren = ExtensionIteratorFactory.instance().newExtensionIteratorLast(_d, _let);
            }
        }
        else {
            throw new RuntimeException("ComposableDiagramIterator.ctor: empty eqn");
        }
        _IAmChildNumber = childNumber;
        _YouAreChildNumber = 0;
        
        setState(null);
    }

    public ComposableIterator newComposableIterator(ComposableIterator parent) {
        if (_subeq.length() > 0) {
            Extension ex = _eiChildren.next();
            Diagram dnext = ex.apply(_d);
            _YouAreChildNumber++;
            // System.out.println("DEBUG NOT NULL\n");
            return new ComposableDiagramIterator(parent, dnext, _subeq, _YouAreChildNumber);
        }
        else {
            // System.out.println("DEBUG I AM RET NULL\n");
            return null;
        }
    }
    
    public boolean hasNext() {
        return _eiReading.hasNext();
    }

    public Object next() {
        DiagramTreeNode answer = null;

        Extension ex = _eiReading.next();
        Diagram  dnext = ex.apply(_d);

        ComposableIterator parent = this.getParent();
        if (parent==null) {
            // System.out.println("PARENT NULL");
            answer = new DiagramTreeNode(dnext);
        }
        else {
            // System.out.println("*** not null");
            answer = new DiagramTreeNode((DiagramTreeNode)parent.getState(), _IAmChildNumber, dnext);
        }

        if (_subeq.length() == 0) {
            answer.setLeaf();
        }

        return answer;
    }

    public void remove() {
        throw new RuntimeException("ComposableDiagramIterator.remove: not implemented");
    }

    public String toString() {
        String s="";
        s += "ComposableDiagramIterator BEGIN\n";
        if (_let==null) {
            s += "    Letter: null\n";
        }
        else {
            s += "    Letter: "+_let.toString() +"\n";
        }
        s += "   Diagram: "+_d.toString()+"";
        s += "ComposableDiagramIterator END\n";
        return s;
    }
}

