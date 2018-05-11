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

package ge;

import java.util.LinkedList;
import java.util.TreeMap;
import java.util.Iterator;

/**
 *
 * @author grouptheory
 */
public class BoundarySet {
    private GE _owner;
    private LinkedList _bdList;

    BoundarySet(GE owner) {
        this(owner,0);
    }
    
    BoundarySet(GE owner, int n) {
        _owner = owner;
        _bdList = new LinkedList();
        for (int i=0; i<n; i++) {
            Boundary b = new Boundary();
            this.add(b);
        }
    }

    GE getOwner() {
        return _owner;
    }

    BoundarySet duplicate(GE owner, TreeMap old2new_bdmap) {
        old2new_bdmap.clear();
        BoundarySet bd2 = new BoundarySet(owner);
        for (Iterator it = this.iterator(); it.hasNext();) {
            Boundary b = (Boundary)it.next();
            Boundary b2 = new Boundary();
            bd2.add(b2);
            old2new_bdmap.put(b, b2);
        }
        return bd2;
    }
    
    int getIndex(Boundary b) {
        if ( ! _bdList.contains(b)) {
            throw new RuntimeException("BoundarySet.getIndex: bad boundary");
        }
        int idx = _bdList.indexOf(b);
        return idx;
    }

    void add(Boundary b) {
        if (_bdList.contains(b)) {
            throw new RuntimeException("BoundarySet.add: duplicate boundary");
        }
        _bdList.addLast(b);
        b._setOwner(this);
    }

    void remove(Boundary b) {
        if ( ! _bdList.contains(b)) {
            throw new RuntimeException("BoundarySet.remove: nonexistent boundary");
        }
        b._setOwner(null);
        _bdList.remove(b);
    }

    Iterator iterator() {
        return _bdList.iterator();
    }

    public Boundary appendNewBoundary() {
        Boundary bd = new Boundary();
        this.add(bd);
        return bd;
    }

    public Boundary insertNewBoundaryAfter(Boundary b) {
        int idx = getIndex(b);
        Boundary newb = new Boundary();
        _bdList.add(idx+1, newb);
        newb._setOwner(this);
        return newb;
    }

    public Boundary insertNewBoundaryBefore(Boundary b) {
        int idx = getIndex(b);
        Boundary newb = new Boundary();
        _bdList.add(idx-1, newb);
        newb._setOwner(this);
        return newb;
    }

    public Boundary getFirst() {
        return (Boundary)_bdList.getFirst();
    }

    Boundary getNth(int i) {
        return (Boundary)_bdList.get(i);
    }
    
    public Boundary getLast() {
        return (Boundary)_bdList.getLast();
    }

    public int getNumberOfBoundaries() {
        return _bdList.size();
    }

    public Boundary nextBoundary(Boundary bd) {
        int idx = _bdList.indexOf(bd);
        if (idx == _bdList.size()-1) {
            return null;
        }
        else {
            return (Boundary)_bdList.get(idx+1);
        }
    }

    public Boundary prevBoundary(Boundary bd) {
        int idx = _bdList.indexOf(bd);
        if (idx == 0) {
            return null;
        }
        else {
            return (Boundary)_bdList.get(idx-1);
        }
    }

    public String toString() {
        String s = "";
        s += "Boundaries: {\n";
        for (Iterator it=this.iterator(); it.hasNext();) {
            Boundary bd=(Boundary)it.next();
            s += "             ";
            s += bd.toString();
            s += " ";
            s += bd.hashCode();
            s += "\n";
        }
        s += "}";
        return s;
    }

    void validate(Boundary bd) {
        int idx = getIndex(bd);
    }

    void validate(GE ge) {
        if (_owner != ge) {
            throw new RuntimeException("BoundarySet.validate failed");
        }
        for (Iterator it=this.iterator(); it.hasNext();) {
            Boundary bd=(Boundary)it.next();
            bd.validate(this);
        }
    }
}
