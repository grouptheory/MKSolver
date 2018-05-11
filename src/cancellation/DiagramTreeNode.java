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

import letter.Letter;
import equation.GroupWord;
import java.util.LinkedList;
import java.util.Iterator;

/**
 * A node in the tree of all cancellation Diagrams.
 * 
 * @author grouptheory
 */
public class DiagramTreeNode {
    private Diagram _d;
    private ExtensionIterator _ei;
    private LinkedList _children;
    private String _name;
    private int _depth;
    private boolean _leaf;
    
    DiagramTreeNode(Diagram d) {
        _d = d;
        _children = new LinkedList();
        _name = "root";
        _depth = 0;
        _leaf = false;
    }

    DiagramTreeNode(DiagramTreeNode parent, int childNumber, Diagram d) {
        _d = d;
        _children = new LinkedList();
        _name = parent._name+"."+childNumber;
        _depth = parent._depth+1;
        _leaf = false;
    }
    
    /**
     * Gets the Diagram associated with this DiagramTreeNode.
     *
     * @return the associated Diagram.
     */
    public Diagram getDiagram() {
        return _d;
    }
    
    String getName() {
        return _name;
    }

    void setLeaf() {
        _leaf = true;
    }

    /**
     * Determines if this DiagramTreeNode is a leaf.
     * 
     * @return true if this DiagramTreeNode is a leaf.
     */
    public boolean getLeaf() {
        return _leaf;
    }
    
    int getDepth() {
        return _depth;
    }

    void extend(Letter let, boolean last) {
        if (_ei != null) {
            throw new RuntimeException("DiagramTreeNode.extend: multiple calls to extend");
        }

        if (last) {
            _ei = ExtensionIteratorFactory.instance().newExtensionIteratorLast(_d, let);
        }
        else {
            _ei = ExtensionIteratorFactory.instance().newExtensionIterator(_d, let);
        }
        int childNumber = 0;

        // System.out.println("DEBUG OUTSIDE LOOP Extension of _d="+_d);

        for ( ;_ei.hasNext(); ) {
            Extension ex = _ei.next();

            // System.out.println("DEBUG INSIDE LOOP Extension of _d="+_d);
            // System.out.println("DEBUG Extension: "+ex);

            Diagram d2 = ex.apply(_d);
            DiagramTreeNode dtn2 = new DiagramTreeNode(this, childNumber, d2);
            if (last) {
                dtn2.setLeaf();
            }
            _children.add(dtn2);
            childNumber++;
        }
    }

    /**
     * Compute the String representation of this DiagramTreeNode.
     *
     * @return a String.
     */
    public String toString() {
        String s = "DiagramTreeNode BEGIN\n";
        s += _d.toString();
        s += "DiagramTreeNode END\n";
        return s;
    }

    void visitedBy(ExtensionVisitor ev) {
        ev.visit(this);

        // DEBUG
        // this.visitedBy(new DiagramTreeNode.ConsoleVisitor());

        Iterator it = _children.iterator();
        for ( ;it.hasNext(); ) {
            DiagramTreeNode dtn2 = (DiagramTreeNode)it.next();
            ExtensionVisitor ev2 = ev.makeSubVisitor();
            dtn2.visitedBy(ev2);
        }
    }
    
    void visitedBy(ConsoleVisitor pv) {
        pv.visit(this);
        Iterator it = _children.iterator();
        for ( ;it.hasNext(); ) {
            DiagramTreeNode dtn2 = (DiagramTreeNode)it.next();
            dtn2.visitedBy(pv);
        }
    }

    void visitedBy(CollectionVisitor cv) {
        cv.visit(this);
        Iterator it = _children.iterator();
        for ( ;it.hasNext(); ) {
            DiagramTreeNode dtn2 = (DiagramTreeNode)it.next();
            dtn2.visitedBy(cv);
        }
    }

    static class ConsoleVisitor {
        ConsoleVisitor() { }
        void visit(DiagramTreeNode dtn) {
            System.out.println("DiagramTreeNode: "+dtn._name+"(depth="+dtn._depth+")");
            System.out.println(dtn._d);
        }
    }
    
    static class CollectionVisitor {
        private LinkedList _treenodes;

        CollectionVisitor() {
            _treenodes = new LinkedList();
        }

        void visit(DiagramTreeNode dtn) {
            Node begin = dtn._d.getBegin();
            Node end = dtn._d.getEnd();
            if ((begin==end) && dtn.getLeaf()) {
                _treenodes.add(dtn);
            }
        }
        
        public String toString() {
            String s = "";
            Iterator it = _treenodes.iterator();
            for ( ;it.hasNext(); ) {
                DiagramTreeNode dtn2 = (DiagramTreeNode)it.next();
                s+=dtn2._d.toString();
            }
            s+=""+_treenodes.size()+" cancellation diagrams were enumerated.\n";
            return s;
        }

        LinkedList getDiagramTreeNodeList() {
            return _treenodes;
        }
    }
}
