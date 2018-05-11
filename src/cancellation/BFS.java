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
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author grouptheory
 */
class BFS {

    private Node _src;
    private LinkedList _frontier;
    private HashSet _markedNodes;
    private HashSet _markedEdges;
    private HashMap _node2prevEdge;
    private HashMap _edge2prevNode;
    private Diagram _d;
    
    BFS(Diagram d, Node src) {
        _d = d;
        _src = src;
        _frontier = new LinkedList();
        _markedNodes = new HashSet();
        _markedEdges = new HashSet();
        _node2prevEdge = new HashMap();
        _edge2prevNode = new HashMap();
        
        _frontier.addLast(src);
        _markedNodes.add(src);
        grow();
    }

    void grow() {
        while (step());
        validate();
    }

    boolean step() {
        Node nd = (Node)_frontier.removeFirst();
        for (Iterator it = nd.edgeIterator(); it.hasNext(); ) {
            Edge e = (Edge)it.next();
            if (e.getOccupancy() == 2) {
                continue;
            }
            Node peer = e.getOpposite(nd);

            if (_markedNodes.contains(peer)) {
                if ( ! _edge2prevNode.containsKey(e)) {
                    _edge2prevNode.put(e, nd);
                }
                _markedEdges.add(e);
                continue;
            }

            if (_frontier.contains(peer)) {
                if ( ! _edge2prevNode.containsKey(e)) {
                    _edge2prevNode.put(e, nd);
                }
                _markedEdges.add(e);
                continue;
            }
            
            _frontier.add(peer);
            _markedEdges.add(e);
            
            _node2prevEdge.put(peer, e);
            _edge2prevNode.put(e, nd);
        }
        _markedNodes.add(nd);
        return _frontier.size()>0;
    }

    boolean reachable(Node nd) {
        _d.validateNode(nd);
        return _markedNodes.contains(nd);
    }

    int countMarkedNodes() {
        return _markedNodes.size();
    }

    int countMarkedEdges() {
        return _markedEdges.size();
    }

    boolean reachable(Edge e) {
        _d.validateEdge(e);
        return _markedEdges.contains(e);
    }
    
    Iterator reachableNodesIterator() {
        return _markedNodes.iterator();
    }

    Iterator reachableEdgesIterator() {
        return _markedEdges.iterator();
    }

    Path getPathFrom(Node nd) {
        _d.validateNode(nd);
        
        Path p = new Path(nd, _d);

        // System.out.println("Trying to go from "+nd+" to "+_src);
        while (nd != _src) {
            // System.out.println("At node "+nd);

            Edge e = (Edge)_node2prevEdge.get(nd);
            if (e==null) {
                //System.out.println("DEBUG BFS\n"+this);
                //System.out.println("DEBUG Diagram "+_d+"\n");
                //System.out.print("DEBUG Reachable nodes: ");
                for (Iterator it = _markedNodes.iterator(); it.hasNext(); ) {
                    Node rn = (Node)it.next();
                    //System.out.print(""+rn.toStringShort()+", ");
                }
                //System.out.println("");

                //System.out.println("DEBUG Missing entry for node "+nd+"\n");
                throw new RuntimeException("DiagramTreeNode.getPathFrom: traceback from node failed");
            }
            nd = (Node)_edge2prevNode.get(e);
            if (nd==null) {
                //System.out.println("DEBUG BFS\n"+this);
                //System.out.println("DEBUG Diagram "+_d+"\n");
                //System.out.println("DEBUG Missing entry for edge "+e+"\n");
                throw new RuntimeException("DiagramTreeNode.getPathFrom: traceback from edge failed");
            }

            p.append(e);
            p.append(nd);
        }
        return p;
    }


    Path getPathFrom(Edge e) {
        _d.validateEdge(e);

        Path p = new Path(e, _d);
        Node nd = (Node)_edge2prevNode.get(e);
        p.append(nd);
        while (nd != _src) {
            Edge e2 = (Edge)_node2prevEdge.get(nd);
            nd = (Node)_edge2prevNode.get(e2);
            p.append(e2);
            p.append(nd);
        }
        return p;
    }

    public String toString() {
        String s = "";
        for (Iterator it = _edge2prevNode.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry ent = (Map.Entry)it.next();
            s += "edge "+ent.getKey()+" ==> node "+ent.getValue()+"\n";
        }
        for (Iterator it = _node2prevEdge.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry ent = (Map.Entry)it.next();
            s += "node "+ent.getKey()+" ==> edge "+ent.getValue()+"\n";
        }
        return s;
    }

    void validate() {
        for (Iterator it = reachableNodesIterator(); it.hasNext();) {
            Node nd = (Node)it.next();
            if (nd == _src) continue;
            
            Edge e = (Edge)_node2prevEdge.get(nd);
            if (e==null) {
                //System.out.println("BAD BFS\n"+this);
                throw new RuntimeException("DiagramTreeNode.getPathFrom: traceback from "+nd+" failed");
            }
        }

        for (Iterator it = reachableEdgesIterator(); it.hasNext();) {
            Edge e = (Edge)it.next();
            Node nd = (Node)_edge2prevNode.get(e);
            if (nd==null) {
                //System.out.println("BAD BFS\n"+this);
                throw new RuntimeException("DiagramTreeNode.getPathFrom: traceback from "+e+" failed");
            }
        }
    }
}
