/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cancellation;

import letter.Letter;
import equation.GroupEquation;
import java.util.LinkedList;
import java.util.Iterator;

/**
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
    
    public Diagram getDiagram() {
        return _d;
    }
    
    public String getName() {
        return _name;
    }

    void setLeaf() {
        _leaf = true;
    }

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

        public LinkedList getDiagramTreeNodeList() {
            return _treenodes;
        }
    }
}
