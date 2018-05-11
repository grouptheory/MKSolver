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

import utility.AbstractComposableIterator;
import utility.ComposableIterator;
import equation.GroupWord;
import letter.Letter;

/**
 * An Iterator which is composable and can be used to lazily generate
 * CancellationDiagrams.
 * 
 * @author grouptheory
 */
public class ComposableDiagramIterator
        extends AbstractComposableIterator
        implements ComposableIterator {

    private Diagram _d;
    private Letter _let;
    private GroupWord _subeq;
    private ExtensionIterator _eiReading;
    private ExtensionIterator _eiChildren;
    private int _IAmChildNumber;
    private int _YouAreChildNumber;

    /**
     * Constructor for the root ComposableDiagramIterator (using an empty diagram).
     * 
     * @param eq the quadratic GroupWord defining the equation eq=1 for
     * which CancellationDiagrams are to be generated.
     */
    public ComposableDiagramIterator(GroupWord eq) {
        this(null, new Diagram(), eq, 0);
    }
    
    ComposableDiagramIterator(ComposableIterator parent, Diagram d, GroupWord eq, int childNumber) {
        setParent(parent);
        
        _d = d;

        if (eq.length() > 0) {
            GroupWord eq2 = eq.duplicate();
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
    
    /**
     * Factory method for sub-Iterators.
     * 
     * @param parent ComposableIterator
     * @return the sub-ComposableIterator.
     */
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
    
    /**
     * Determine if there are more DiagramTreeNodes to be generated by this Iterator.
     *
     * @return true if there are more DiagramTreeNodes to be generated.
     */
    public boolean hasNext() {
        return _eiReading.hasNext();
    }

    /**
     * Get the next item.
     *
     * @return the next DiagramTreeNode.
     */
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

    /**
     * Not implemented.
     */
    public void remove() {
        throw new RuntimeException("ComposableDiagramIterator.remove: not implemented");
    }

    /**
     *
     * Compute the String representation of this ComposableDiagramIterator.
     *
     * @return a String.
     */
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

