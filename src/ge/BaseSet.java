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
import java.util.Iterator;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;
import letter.Letter;

/**
 *
 * @author grouptheory
 */
public class BaseSet {
    private GE _owner;
    private LinkedList _bsList;

    BaseSet(GE owner) {
        _owner = owner;
        _bsList = new LinkedList();
    }

    GE getOwner() {
        return _owner;
    }

    BaseSet duplicate(GE owner, TreeMap old2new_bdmap, HashMap old2new_bsmap) {
        old2new_bsmap.clear();
        BaseSet bs2 = new BaseSet(owner);
        for (Iterator it = this.iterator(); it.hasNext();) {
            Base b = (Base)it.next();
            Boundary begin2 = (Boundary)old2new_bdmap.get(b.getBegin());
            Boundary end2 = (Boundary)old2new_bdmap.get(b.getEnd());
            Letter let = b.getLabel();
            Base b2 = new Base(begin2, end2, let);
            bs2.add(b2);
            old2new_bsmap.put(b, b2);
        }
        
        for (Iterator it = this.iterator(); it.hasNext();) {
            Base b = (Base)it.next();
            if (b.isConstant()) continue;
            
            Base bDual = b.getDual();

            Base b2 = (Base)old2new_bsmap.get(b);
            Base bDual2 = (Base)old2new_bsmap.get(bDual);

            b2.setDual(bDual2);
        }
        
        for (Iterator it = this.iterator(); it.hasNext();) {
            Base b = (Base)it.next();
            if (b.isConstant()) continue;

            Constraint c = b.getConstraint();
            Constraint c2 = c.duplicate(old2new_bdmap, old2new_bsmap);

            Base b2 = (Base)old2new_bsmap.get(b);
            b2._setConstraint(c2);
        }
        
        return bs2;
    }

    void add(Base b) {
        _bsList.addLast(b);
        b._setOwner(this);
    }

    void remove(Base b) {
        if ( ! _bsList.contains(b)) {
            throw new RuntimeException("BaseSet.remove: base not present");
        }
        b._setOwner(null);
        _bsList.remove(b);
    }

    Iterator iterator() {
        return _bsList.iterator();
    }

    int getNumberOfBases() {
        return _bsList.size();
    }

    public String toString() {
        String s = "";
        s += "Bases: {\n";

        TreeSet ts = new TreeSet(new BaseComparator());
        ts.addAll(_bsList);

        for (Iterator it=ts.iterator(); it.hasNext();) {
            Base bs=(Base)it.next();
            s += "        ";
            s += bs.toString();
            s += "\n";
        }
        s += "       }";
        return s;
    }

    void validate(GE ge) {
        if (_owner != ge) {
            throw new RuntimeException("BaseSet.validate failed");
        }
        for (Iterator it = this.iterator(); it.hasNext();) {
            Base b = (Base)it.next();
            b.validate(this);
        }
    }
}
