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
