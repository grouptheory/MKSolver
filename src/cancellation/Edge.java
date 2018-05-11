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

/**
 * An Edge in a cancellation Diagram.  Note that an Edge is an maximal segment between
 * branch points in a cancellation process.
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
    
    /**
     * Compute the String representation of this Edge.
     *
     * @return a String.
     */
    public String toString() {
        String s = "";
        s += "(" + _a.toStringShort() + "," + _b.toStringShort() + ")";
        if (getOccupancy()==2) s+="*";
        return s;
    }
}
