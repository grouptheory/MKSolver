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
import java.util.Iterator;

class Node {
    private int _id;
    private HashMap _edges;

    Node(int id) {
        _id = id;
        _edges = new HashMap();
    }

    int getID() {
        return _id;
    }

    int degree() {
        return _edges.size();
    }

    private void addEdge(Edge e) {
        Node peer = e.getOpposite(this);
        Edge eprior = (Edge)_edges.get(peer);
        if (eprior == null) {
            _edges.put(peer, e);
        }
        else {
            throw new RuntimeException("Node.addEdge: parallel duplicate edge");
        }
    }

    Edge addEdge(Node peer) {
        Edge e = (Edge)_edges.get(peer);
        if (e == null) {
            e = new Edge(this, peer);
            _edges.put(peer, e);
            peer.addEdge(e);
        }
        return e;
    }

    Edge getEdge(Node peer) {
        Edge e = (Edge)_edges.get(peer);
        if (e == null) {
            throw new RuntimeException("Node.getEdge: edge not found");
        }
        return e;
    }

    private void delEdge(Edge e) {
        Node peer = e.getOpposite(this);
        Edge eprior = (Edge)_edges.get(peer);
        if (eprior != null) {
            _edges.remove(peer);
        }
        else {
            throw new RuntimeException("Node.delEdge: nonexistent edge");
        }
    }

    Edge delEdge(Node peer) {
        Edge e = (Edge)_edges.get(peer);
        if (e != null) {
            _edges.remove(peer);
            peer.delEdge(e);
        }
        return e;
    }

    Iterator edgeIterator() {
        return _edges.values().iterator();
    }
    
    public String toString() {
        String s = "";
        s += _id;
        s += "("+_edges.size()+")";
        return s;
    }
    
    String toStringShort() {
        String s = "";
        s += _id;
        return s;
    }
}
