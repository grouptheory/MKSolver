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

import java.util.TreeMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import letter.Letter;
import letter.Variable;

/**
 *
 * @author grouptheory
 */
public class Constraint {
    private Base _bs;
    private TreeMap _bd2bd;

    Constraint(Base b) {
        _bs = b;
        _bd2bd = new TreeMap();
    }

    void initialize() {
        Base dual = _bs.getDual();
        Variable v = (Variable)_bs.getLabel();
        Variable vdual = (Variable)dual.getLabel();

        boolean flipDualBoundaries = false;
        if ((v.isPositive() && !vdual.isPositive()) ||
            (!v.isPositive() && vdual.isPositive())) {
            flipDualBoundaries = true;
        }

        if (!flipDualBoundaries) {
            this.add(_bs.getBegin(), dual.getBegin());
            this.add(_bs.getEnd(), dual.getEnd());
        }
        else {
            this.add(_bs.getBegin(), dual.getEnd());
            this.add(_bs.getEnd(), dual.getBegin());
        }
    }

    private Constraint(Base b, TreeMap bd2bd2) {
        this(b);
        _bd2bd.putAll(bd2bd2);
    }
    
    Constraint duplicate(TreeMap old2new_bdmap, HashMap old2new_bsmap) {
        Base bs2 = (Base)old2new_bsmap.get(_bs);
        TreeMap bd2bd2 = new TreeMap();
        
        for (Iterator it=_bd2bd.entrySet().iterator(); it.hasNext();) {
            Map.Entry ent = (Map.Entry)it.next();
            Boundary b = (Boundary)ent.getKey();
            Boundary bDual = (Boundary)ent.getValue();
            
            Boundary b2 = (Boundary)old2new_bdmap.get(b);
            Boundary bDual2 = (Boundary)old2new_bdmap.get(bDual);
            bd2bd2.put(b2, bDual2);
        }
        return new Constraint(bs2, bd2bd2);
    }

    void add(Boundary b, Boundary bdual) {
        if ( ! _bs.contains(b)) {
            throw new RuntimeException("Constraint.add: boundary not in base");
        }
        if ( ! _bs.getDual().contains(bdual)) {
            throw new RuntimeException("Constraint.add: dual boundary not in dual base");
        }
        if ( _bd2bd.containsKey(b) ) {
            throw new RuntimeException("Constraint.add: constraint already exists");
        }
        _bd2bd.put(b, bdual);
    }

    void remove(Boundary b, Boundary bdual) {
        if ( ! _bs.contains(b)) {
            throw new RuntimeException("Constraint.remove: boundary "+b+" is not in base "+_bs);
        }
        if ( ! _bs.getDual().contains(bdual)) {
            throw new RuntimeException("Constraint.remove: dual boundary not in dual base");
        }
        if ( ! _bd2bd.containsKey(b) ) {
            throw new RuntimeException("Constraint.remove: constraint doesn't exist");
        }

        _bd2bd.remove(b);

        if (_bd2bd.size() < 2) {
            throw new RuntimeException("Constraint.remove: size < 2");
        }
    }

    void move(Boundary oldBegin, Boundary oldEnd, Boundary newBegin, Boundary newEnd) {
        if (_bd2bd.size() != 2) {

            if ((_bd2bd.size() == 1) && (_bs.isEmpty())) {
                // ok
            }
            else {
                throw new RuntimeException("Constraint.move: _bd2bd.size() != 2, or 1");
            }
        }
        if (newBegin==null) {
            throw new RuntimeException("Constraint.move: newBegin == null");
        }
        if (newEnd==null) {
            throw new RuntimeException("Constraint.move: newEnd == null");
        }
        if (oldBegin==null) {
            throw new RuntimeException("Constraint.move: oldBegin == null");
        }
        if (oldEnd==null) {
            throw new RuntimeException("Constraint.move: oldEnd == null");
        }

        Boundary b2, e2;
        b2 = (Boundary)_bd2bd.get(oldBegin);
        if (b2==null) {
            throw new RuntimeException("Constraint.move: b2 == null");
        }
        e2 = (Boundary)_bd2bd.get(oldEnd);
        if (e2==null) {
            throw new RuntimeException("Constraint.move: e2 == null");
        }
        _bd2bd.remove(oldBegin);
        _bd2bd.remove(oldEnd);
        _bd2bd.put(newBegin, b2);
        _bd2bd.put(newEnd, e2);
    }

    void dualMoved(Boundary oldBegin, Boundary oldEnd, Boundary newBegin, Boundary newEnd) {
        if (newBegin==null) {
            throw new RuntimeException("Constraint.dualMoved: newBegin == null");
        }
        if (newEnd==null) {
            throw new RuntimeException("Constraint.dualMoved: newEnd == null");
        }
        if (oldBegin==null) {
            throw new RuntimeException("Constraint.dualMoved: oldBegin == null");
        }
        if (oldEnd==null) {
            throw new RuntimeException("Constraint.dualMoved: oldEnd == null");
        }

        Boundary b1, e1;
        b1 = (Boundary)_bd2bd.firstKey();
        e1 = (Boundary)_bd2bd.lastKey();
        if (_bd2bd.get(b1) == oldBegin) {
            _bd2bd.remove(b1);
            _bd2bd.put(b1, newBegin);
        }
        else if(_bd2bd.get(b1) == oldEnd) {
            _bd2bd.remove(b1);
            _bd2bd.put(b1, newEnd);
        }
        else {
            throw new RuntimeException("Constraint.dualMoved: cant find binding to beginning");
        }

        if (e1 != b1) {
            if (_bd2bd.get(e1) == oldBegin) {
                _bd2bd.remove(e1);
                _bd2bd.put(e1, newBegin);
            }
            else if(_bd2bd.get(e1) == oldEnd) {
                _bd2bd.remove(e1);
                _bd2bd.put(e1, newEnd);
            }
            else {
                throw new RuntimeException("Constraint.dualMoved: cant find binding to ending");
            }
        }
    }

    void collapse(Boundary src, Boundary target) {
        _bd2bd.clear();
        _bd2bd.put(src, target);
    }

    public Boundary getDual(Boundary b) {
        if ( ! _bd2bd.containsKey(b) ) {
            throw new RuntimeException("Constraint.getDual: constraint doesn't exist");
        }
        return (Boundary)_bd2bd.get(b);
    }

    Boundary minimal() {
        return (Boundary)_bd2bd.firstKey();
    }
    
    Boundary maximal() {
        return (Boundary)_bd2bd.lastKey();
    }

    Base getBase() {
        return _bs;
    }

    public Iterator iteratorBoundary() {
        return _bd2bd.keySet().iterator();
    }

    public int size() {
        return _bd2bd.size();
    }

    public String toString() {
        String s = "";
        for (Iterator it=this.iteratorBoundary(); it.hasNext();) {
            Boundary bd=(Boundary)it.next();
            Boundary bdDual = this.getDual(bd);
            s += bd.toString();
            s += "->";
            s += bdDual.toString();
            if (it.hasNext()) s += ", ";
        }
        return s;
    }

    void validate(Base bs) {
        if (_bs != bs) {
            throw new RuntimeException("Constraint.validate failed");
        }
        for (Iterator it=this.iteratorBoundary(); it.hasNext();) {
            Boundary bd=(Boundary)it.next();
            Boundary bdDual = this.getDual(bd);
            _bs.getOwner().getOwner().validate(bd);
            _bs.getOwner().getOwner().validate(bdDual);
        }
    }
}
