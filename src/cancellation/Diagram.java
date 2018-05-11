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

import java.util.HashMap;
import java.util.Iterator;
import java.util.HashSet;
import java.util.LinkedList;
import letter.Letter;
import letter.Variable;

/**
 *
 * @author grouptheory
 */
public class Diagram {

    private int _nextID;
    private int _nodeCount;
    private HashMap _nodes;
    
    private int _edgeCount;
    private HashSet _edges;

    private Node _begin;
    private Node _end;

    LinkedList _labeledPaths;
    
    Diagram() {
        _nodes = new HashMap();
        _nodeCount = 0;
        _edges = new HashSet();
        _edgeCount = 0;

        _labeledPaths = new LinkedList();

        _nextID = 0;
        
        _begin = addNode();
        _end = _begin;
    }

    Diagram(Diagram d) {
        _nodes = new HashMap();
        _nodeCount = 0;
        _edges = new HashSet();
        _edgeCount = 0;
        _labeledPaths = new LinkedList();

        _nextID = d._nextID;
        
        for (Iterator it = d._nodes.keySet().iterator(); it.hasNext();) {
            Integer id = (Integer)it.next();
            this.addNode(id.intValue());
        }

        for (Iterator it = d._edges.iterator(); it.hasNext();) {
            Edge e = (Edge)it.next();
            Node na = (Node)_nodes.get(new Integer(e.getA().getID()));
            Node nb = (Node)_nodes.get(new Integer(e.getB().getID()));
            this.addEdge(na, nb);
        }
        
        _begin = (Node)_nodes.get(new Integer(d._begin.getID()));
        _end = (Node)_nodes.get(new Integer(d._end.getID()));
        
        for (Iterator it = d._labeledPaths.iterator(); it.hasNext();) {
            LabeledPath lp = (LabeledPath)it.next();
            this.addLabeledPath(LabeledPath.project(lp, this));
        }
    }

    Node getBegin() {
        return _begin;
    }

    Node getEnd() {
        return _end;
    }

    boolean isClosed() {
        return this.getBegin()==this.getEnd();
    }
    
    Node addNode() {
        Node nd = addNode(_nextID);
        _nextID++;
        return nd;
    }
    
    private Node addNode(int id) {
        Node nd = new Node(id);
        Node exists = (Node)_nodes.get(id);
        if (exists!=null) {
            throw new RuntimeException("Diagram.addNode: duplicate node "+id);
        }
        _nodes.put(id, nd);
        _nodeCount++;
        return nd;
    }

    Node lookupNode(int id) {
        Node nd = (Node)_nodes.get(id);
        if (nd==null) {
            throw new RuntimeException("Diagram.lookupNode: nonexistent node");
        }
        return nd;
    }

    int getNumberOfNodes() {
        return _nodes.size();
    }

    /**
     * Get an Iterator over the Edge objects constituting this Diagram,
     * in order of the cancellation process.
     *
     * @return an Iterator over edges.
     */
    public Iterator iteratorEdges() {
        return _edges.iterator();
    }

    Iterator iteratorNodes() {
        return _nodes.values().iterator();
    }

    /**
     * Get an Iterator over the LabeledPaths objects constituting this Diagram,
     * in order of the cancellation process.
     *
     * @return an Iterator over LabeledPaths.
     */
    public Iterator iteratorLabeledPaths() {
        return _labeledPaths.iterator();
    }

    /**
     * Get the dual of a LabeledPath in this Diagram,
     * if it exists.  If more than one dual exists in the
     * Diagram, an exception is thrown.
     * 
     * @param lp a LabeledPath.
     * @return its dual.
     */
    public LabeledPath getDual(LabeledPath lp) {
        validateLabeledPath(lp);
        
        Letter let = lp.getLabel();
        if (let.isConstant()) return null;

        LabeledPath answer = null;
        int found = 0;
        for (Iterator it = _labeledPaths.iterator(); it.hasNext();) {
            LabeledPath lp2 = (LabeledPath)it.next();
            Letter let2 = lp2.getLabel();

            if ((let==let2) || (let==let2.getInverse())) {
                if (lp2 == lp) continue;
                else {
                    found++;
                    answer = lp2;
                }
            }
        }

        if (found > 1) {
            throw new RuntimeException("Diagram.getDual: variable occurs more than 2 times");
        }
        return answer;
    }

    /**
     * Get the two LabeledPaths traversing an Edge.
     *
     * @param e an Edge in this Diagram.
     * @return an array containing two LabeledPaths.
     */
    public LabeledPath[] getPaths(Edge e) {
        validateEdge(e);

        LabeledPath[] lpArray = new LabeledPath[2];

        int found = 0;
        for (Iterator it = _labeledPaths.iterator(); it.hasNext();) {
            LabeledPath lp2 = (LabeledPath)it.next();
            Path p2 = lp2.getPath();
            Letter let2 = lp2.getLabel();

            if (p2.hasEdge(e)) {
                if (found >= 2) {
                    throw new RuntimeException("Diagram.getPaths: more than 2 paths on an edge!");
                }
                lpArray[found]=lp2;
                found++;
            }
        }

        if (found != 2) {
            throw new RuntimeException("Diagram.getPaths: didn't find 2 paths on an edge!");
        }

        return lpArray;
    }

    /**
     * Get a LabeledPath that carries a particular Variable label.  Note
     * that there are generally 2 such paths in the Diagram, and this method
     * returns one of them arbitrarily.
     *
     * @param v a Variable.
     * @return the LabeledPath carrying v.
     */
    public LabeledPath getVariablePath(Variable v) {

        // System.out.println("Searching for "+v+"\n");

        LabeledPath answer = null;
        int found = 0;
        for (Iterator it = _labeledPaths.iterator(); it.hasNext();) {
            LabeledPath lp2 = (LabeledPath)it.next();
            Letter let2 = lp2.getLabel();

            if ((v==let2) || (v==let2.getInverse())) {
                found++;
                if (answer==null) {
                    answer = lp2;

                    // System.out.println("Found "+lp2+"\n");
                }
            }
        }

        if (found != 2) {
            throw new RuntimeException("Diagram.getDual: equivalent variable occurs != 2 times");
        }
        return answer;
    }

    int getNumberOfEdges() {
        return _edges.size();
    }

    Edge lookupEdge(int id1, int id2) {
        Node nd1 = lookupNode(id1);
        Node nd2 = lookupNode(id2);
        Edge e = nd1.getEdge(nd2);
        return e;
    }

    Edge addEdge(Node a, Node b) {
        validateNode(a);
        validateNode(b);

        int a1=a.degree();
        int b1=b.degree();

        Edge e = a.addEdge(b);
        _edges.add(e);
        _edgeCount++;

        int a2=a.degree();
        int b2=b.degree();
        if ( a2 != a1+1) {
            throw new RuntimeException("Diagram.addEdge: failed on "+a);
        }
        if ( b2 != b1+1) {
            throw new RuntimeException("Diagram.addEdge: failed on "+b);
        }

        return e;
    }

    void delEdge(Edge e) {
        validateEdge(e);

        Node a = e.getA();
        Node b = e.getB();
        a.delEdge(b);
        b.delEdge(a);
        
        _edges.remove(e);
        _edgeCount--;
    }

    boolean isCuttableEdge(Edge e) {
        for (Iterator it = _labeledPaths.iterator(); it.hasNext();) {
            LabeledPath lp = (LabeledPath)it.next();
            if (lp.hasEdge(e)) {
                Letter let = lp.getLabel();
                if (let.isConstant()) return false;
            }
        }
        return true;
    }

    Node cutEdge(Edge e) {
        validateEdge(e);
        
        Node a = e.getA();
        Node b = e.getB();

        Node c = addNode();
        Edge ac = addEdge(a,c);
        Edge bc = addEdge(b,c);
        ac.setOccupancy(e.getOccupancy());
        bc.setOccupancy(e.getOccupancy());

        for (Iterator it = _labeledPaths.iterator(); it.hasNext();) {
            LabeledPath lp = (LabeledPath)it.next();
            lp.cutEdge(e, c);
        }

        delEdge(e);

        return c;
    }

    void addLabeledPath(LabeledPath lp) {
        lp.validate(this);
        _labeledPaths.addLast(lp);

        for (Iterator it = lp.iteratorEdges(); it.hasNext();) {
            Edge e = (Edge)it.next();
            e.incrementOccupancy();
        }
        
        _end = lp.getNewEnd();
    }
    
    /**
     *
     * Compute the String representation of this Diagra,.
     *
     * @return a String.
     */
    public String toString() {
        String s = "";
        s+=">>>>>>>>>>>>>>>>>>>>>>>>>>>>\n";
        s += "nodes: ";
        for (Iterator it = _nodes.values().iterator(); it.hasNext();) {
            Node nd = (Node)it.next();
            s += nd.toStringShort()+", ";
        }
        s += "\nstart: "+_begin.toStringShort()+" ---> ";
        s += "end: "+_end.toStringShort()+"\n";
        
        s += "edges: ";
        
        for (Iterator it = _edges.iterator(); it.hasNext();) {
            Edge e = (Edge)it.next();
            s += e.toString()+", ";
        }
        s += "\n";
        s += "labeled paths:\n";

        for (Iterator it = _labeledPaths.iterator(); it.hasNext();) {
            LabeledPath lp = (LabeledPath)it.next();
            s += "   "+lp.toString()+"\n";
        }
        s+=" <<<\n";
        
        return s;
    }

    void validateNode(Node node) {
        Node exists = (Node)_nodes.get(node.getID());
        if (exists==null) {
            throw new RuntimeException("Diagram.validateNode: nonexistent node");
        }
        if (exists!=node) {
            throw new RuntimeException("Diagram.validateNode: bad node");
        }
    }

    void validateEdge(Edge e) {
        if ( ! _edges.contains(e)) {
            throw new RuntimeException("Diagram.validateNode: nonexistent edge");
        }
    }

    void validateLabeledPath(LabeledPath lp) {
        if ( ! _labeledPaths.contains(lp)) {
            throw new RuntimeException("Diagram.validateLabeledPath: nonexistent path");
        }
    }

    void validate() {
        if (_edges.size() != _edgeCount) {
            throw new RuntimeException("Diagram.validate: bad _edgeCount");
        }
        if (_nodes.size() != _nodeCount) {
            throw new RuntimeException("Diagram.validate: bad _nodeCount");
        }
        if ( ! _nodes.containsValue(_begin)) {
            throw new RuntimeException("Diagram.validate: bad _begin");
        }
        if ( ! _nodes.containsValue(_end)) {
            throw new RuntimeException("Diagram.validate: bad _end");
        }
    }


    /**
     * A Diagram decorator.
     */
    public interface Decorator {
    }
    
    private Decorator _decorator;

    Decorator getDecorator() {
        return _decorator;
    }

    void setDecorator(Decorator dec) {
        _decorator = dec;
    }
}
