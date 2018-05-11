/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cancellation;

import java.util.LinkedList;
import java.util.Iterator;

/**
 *
 * @author grouptheory
 */
public class Path {
    private LinkedList _nodeList;
    private LinkedList _edgeList;
    private Node _srcNode;
    private Edge _srcEdge;
    private Diagram _d;

    Path(Node src, Diagram d) {
        _d = d;
        _srcNode = src;
        _srcEdge = null;
        _edgeList = new LinkedList();
        _nodeList = new LinkedList();
        _nodeList.add(src);
    }
    
    Path(Edge src, Diagram d) {
        _d = d;
        _srcNode = null;
        _srcEdge = src;
        _nodeList = new LinkedList();
        _edgeList = new LinkedList();
        _edgeList.add(src);
    }

    static Path project(Path p, Diagram d2) {
        Path p2 = new Path(p, d2);
        p2.validate(d2);
        return p2;
    }
    
    private Path(Path p, Diagram d2) {
        _d = d2;

        if (p._srcNode == null) _srcNode=null;
        else _srcNode = d2.lookupNode(p._srcNode.getID());


        if (p._srcEdge == null) _srcEdge=null;
        else _srcEdge = d2.lookupEdge(p._srcEdge.getA().getID(), p._srcEdge.getB().getID());

        _edgeList = new LinkedList();
        _nodeList = new LinkedList();

        Iterator itNodes = p._nodeList.iterator();
        for ( ;itNodes.hasNext();) {
            Node nd = (Node)itNodes.next();
            _nodeList.add(d2.lookupNode(nd.getID()));
        }

        Iterator itEdges = p._edgeList.iterator();
        for ( ;itEdges.hasNext();) {
            Edge e = (Edge)itEdges.next();
            _edgeList.add(d2.lookupEdge(e.getA().getID(), e.getB().getID()));
        }
    }

    Iterator iteratorEdges() {
        return _edgeList.iterator();
    }
    
    boolean isNodePath() {
        return _srcEdge==null;
    }

    Node getSrcNode() {
        if ( ! isNodePath()) {
            throw new RuntimeException("Path.getSrcNode: on a non NodePath");
        }
        return _srcNode;
    }

    public int length() {
        int sz = _nodeList.size() - 1;
        if (isEdgePath()) sz++;
        return sz;
    }

    boolean isEdgePath() {
        return _srcNode==null;
    }

    Edge getSrcEdge() {
        if ( ! isEdgePath()) {
            throw new RuntimeException("Path.getSrcEdge: on a non EdgePath");
        }
        return _srcEdge;
    }

    void append(Edge e) {
        _d.validateEdge(e);
        
        _edgeList.add(e);
    }

    void append(Node nd) {
        _d.validateNode(nd);

        _nodeList.add(nd);
    }

    boolean hasEdge(Edge e) {
        _d.validateEdge(e);
        return _edgeList.contains(e);
    }

    int getEdgeIndex(Edge e) {
        _d.validateEdge(e);
        return _edgeList.indexOf(e);
    }
    
    void cutEdge(Edge e, Node insert) {
        _d.validateEdge(e);
        _d.validateNode(insert);

        if ( ! _edgeList.contains(e)) {
            throw new RuntimeException("Path.cutEdge: nonexistent edge");
        }
        
        int i = _edgeList.indexOf(e);
        Node a = e.getA();
        Node b = e.getB();
        int ai = _nodeList.indexOf(a);
        int bi = _nodeList.indexOf(b);
        if (ai<0 || bi<0) {
            throw new RuntimeException("Path.cutEdge: nonexistent edge");
        }
        Node first, second;
        int firsti, secondi;
        if (ai < bi) {
            first = a;
            firsti = ai;
            second = b;
            secondi = bi;
        }
        else if (bi < ai) {
            first = b;
            firsti = bi;
            second = a;
            secondi = ai;
        }
        else {
            throw new RuntimeException("Path.cutEdge: loop edge");
        }

        if (firsti+1 != secondi) {
            throw new RuntimeException("Path.cutEdge: nodeList-edgeList inconsistency");
        }

        _edgeList.remove(e);
        _nodeList.add(secondi, insert);
        // System.out.println("DEBUG inserting "+insert);
        Edge e1 = insert.addEdge(first);
        Edge e2 = insert.addEdge(second);
        _edgeList.add(i, e2);
        _edgeList.add(i, e1);
    }

    public String toString() {
        String s = "[len="+length()+"] ";
        Iterator itNodes = _nodeList.iterator();
        Iterator itEdges = _edgeList.iterator();
        if (this.isNodePath()) {
            Node src = (Node)itNodes.next();
            s += src.toStringShort()+", ";
        }
        else if (this.isEdgePath()) {
            Edge src = (Edge)itEdges.next();
            s += src.toString()+", ";
        }

        for ( ;itNodes.hasNext();) {
            if (this.isNodePath()) {
                Edge e = (Edge)itEdges.next();
                s += e.toString()+", ";
                Node nd = (Node)itNodes.next();
                s += nd.toStringShort()+", ";
            }
            else if (this.isEdgePath()) {
                Node nd = (Node)itNodes.next();
                s += nd.toStringShort()+", ";
                if (itEdges.hasNext()) {
                    Edge e = (Edge)itEdges.next();
                    s += e.toString()+", ";
                }
            }
        }
        return s;
    }

    private void validate() {
        Iterator itNodes = _nodeList.iterator();
        for ( ;itNodes.hasNext();) {
            Node nd = (Node)itNodes.next();
            _d.validateNode(nd);
        }

        Iterator itEdges = _edgeList.iterator();
        for ( ;itEdges.hasNext();) {
            Edge e = (Edge)itEdges.next();
            _d.validateEdge(e);
        }

        if ((_srcNode != null) && (_srcNode != _nodeList.getFirst())) {
            throw new RuntimeException("Path.validate: _srcNode inconsistency");
        }
        if ((_srcEdge != null) && (_srcEdge != _edgeList.getFirst())) {
            throw new RuntimeException("Path.validate: _srcEdge inconsistency");
        }
    }
    
    void validate(Diagram d) {
        if (_d != d) {
            throw new RuntimeException("Path.validate: invalid associated Diagram");
        }
        validate();
    }

}
