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

/**
 *
 * @author grouptheory
 */
public class Boundary implements java.lang.Comparable {
    private BoundarySet _owner;

    Boundary() {
        _owner = null;
    }

    public int getID() {
        return _owner.getIndex(this);
    }

    BoundarySet getOwner() {
        return _owner;
    }

    void _setOwner(BoundarySet owner) {
        if (this.getOwner() != null && owner!=null) {
            throw new RuntimeException("Boundary._setOwner: boundary is already owned");
        }
        _owner = owner;
    }

    public String toString() {
        String s = "";
        s += ""+getID();
        return s;
    }

    public int compareTo(Object o) {
        if (this.getOwner() == null) {
            throw new RuntimeException("Boundary.compareTo: _owner = null");
        }
        if (! (o instanceof Boundary)) {
            throw new RuntimeException("Boundary.compareTo: bad type");
        }
        Boundary obd = (Boundary)o;
        if (obd.getOwner() == null) {
            throw new RuntimeException("Boundary.compareTo: obd = null");
        }
        if (this.getOwner() != obd.getOwner()) {
            throw new RuntimeException("Boundary.compareTo: obd and this are in different GEs");
        }
        
        if (this.getID() < obd.getID()) return -1;
        else if(this.getID() > obd.getID()) return +1;
        else return 0;
    }

    void validate(BoundarySet bdset) {
        if (_owner != bdset) {
            throw new RuntimeException("Boundary.validate failed");
        }
    }
}
