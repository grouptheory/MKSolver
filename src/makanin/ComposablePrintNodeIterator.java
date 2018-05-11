/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package makanin;

import utility.AbstractComposableIterator;
import utility.ComposableIterator;
import java.util.LinkedList;
import java.util.Iterator;

/**
 *
 * @author grouptheory
 */
public class ComposablePrintNodeIterator
        extends AbstractComposableIterator
        implements ComposableIterator {

    private LinkedList _substates;
    private Iterator _readi;
    private Iterator _childi;


    ComposablePrintNodeIterator(PrintNode pn, boolean root) {
        _substates = new LinkedList();
        if (pn.remainingCarrierBoundaries()>0) {
            _substates.addLast(pn.consumeCarrierBoundary(PrintNode.ZERO));
            if (!root) _substates.addLast(pn.consumeCarrierBoundary(PrintNode.NONZERO));
        }
        if (pn.remainingCarrierDualBoundaries()>0) {
            _substates.addLast(pn.consumeCarrierDualBoundary(PrintNode.ZERO));
            if (!root) _substates.addLast(pn.consumeCarrierDualBoundary(PrintNode.NONZERO));
        }

        _readi = _substates.iterator();
        _childi = _substates.iterator();
    }
    
    public ComposableIterator newComposableIterator(ComposableIterator parent) {
        PrintNode pn = (PrintNode)_childi.next();
        boolean root = false;
        return new ComposablePrintNodeIterator(pn, root);
    }

    public boolean hasNext() {
        return _readi.hasNext();
    }

    public Object next() {
        PrintNode answer = (PrintNode)_readi.next();
        return answer;
    }

    public void remove() {
        throw new RuntimeException("ComposablePrintNodeIterator.remove: not implemented");
    }
}
