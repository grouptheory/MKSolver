/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cancellation;

/**
 *
 * @author grouptheory
 */
public class Edge {
    private Node _a, _b;
    private int _occupancy;
    private Edge _reverse;
    
    Edge(Node a, Node b) {
        if (a==b) {
            throw new RuntimeException("Edge.ctor: loop edge");
        }
        _a = a;
        _b = b;
        _occupancy = 0;
        _reverse = null;
    }

    void setReverse(Edge rev) {
        _reverse = rev;
    }

    Node getA() {
        return _a;
    }

    Node getB() {
        return _b;
    }

    int getOccupancy() {
        return _occupancy;
    }

    void setOccupancy(int occupancy) {
        _occupancy = occupancy;
    }

    void incrementOccupancy() {
        _occupancy++;
    }

    Node getOpposite(Node x) {
        if (_a == x) return _b;
        else if(_b == x) return _a;
        else {
            throw new RuntimeException("Edge.getOpposite: bad edge");
        }
    }
    
    public String toString() {
        String s = "";
        s += "(" + _a.toStringShort() + "," + _b.toStringShort() + ")";
        if (getOccupancy()==2) s+="*";
        return s;
    }
}
