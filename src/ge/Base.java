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

import utility.AbstractDecorable;
import letter.Letter;
import utility.ConsoleLogger;

/**
 *
 * @author grouptheory
 */
public class Base extends AbstractDecorable {
    private BaseSet _owner;
    private Boundary _begin;
    private Boundary _end;
    private Letter _label;
    private Base _dual;
    private Constraint _cons;

    private Boundary _uncollapsedBegin;
    private Boundary _uncollapsedEnd;

    Base(Boundary begin, Boundary end, Letter label) {
        if ( begin.getID() > end.getID()) {
            throw new RuntimeException("Base.ctor boundaries out of order");
        }
        _begin = begin;
        _end = end;

        _uncollapsedBegin = null;
        _uncollapsedEnd = null;

        _label = label;
        _dual = null;
        
        if (this.getLabel().isConstant()) {
            _cons = null;
        }
        else {
            _cons = new Constraint(this);
        }

        _owner = null;
    }

    void set(Boundary begin, Boundary end) {
        _begin = begin;
        _end = end;
    }
    
    public Boundary getBegin() {
        return _begin;
    }

    public Boundary getEnd() {
        return _end;
    }

    public Boundary getUncollapsedBegin() {
        return _uncollapsedBegin;
    }

    public Boundary getUncollapsedEnd() {
        return _uncollapsedEnd;
    }

    public void collapse() {
        this.collapseSingle();
        this.getDual().collapseSingle();
    }

    void collapseSingle() {
        if (!this.isConstant() && _cons.size() != 2) {
            throw new RuntimeException("Base.collapse: variable base collapsing with _cons.size() != 2");
        }
        Base dual=this.getDual();

        _uncollapsedBegin = _begin;
        _uncollapsedEnd = _end;

        _begin = _end;
        this.getConstraint().collapse(this.getEnd(), dual.getEnd());
    }

    public void move(Boundary newBegin, Boundary newEnd) {
        if (newBegin==null) {
            throw new RuntimeException("Base.move: newBegin == null");
        }
        if (newEnd==null) {
            throw new RuntimeException("Base.move: newEnd == null");
        }
        if (!this.isConstant() && (_cons.size() != 2)) {
            if ((_cons.size() == 1) && (_begin == _end)) {
                // ok
            }
            else {
                throw new RuntimeException("Base.move: variable base moving with _cons.size() = "+_cons.size()+" which is != 2, or 1");
            }
        }
        
        checkBoundaries();


        boolean opp1 = false;

        if (!this.isConstant()) {

            opp1 = this.opposesDual();
            
            Base dual = this.getDual();

            //System.out.println("*** moving "+this+" to = "+newBegin+","+newEnd);
            //System.out.println("dual = "+dual);

            Boundary oldBegin, oldEnd;
            oldBegin = _begin;
            oldEnd = _end;

            //System.out.println("oldBegin, oldEnd: "+ oldBegin+", "+oldEnd);

            //System.out.println("newBegin, newEnd: "+newBegin+", "+newEnd);

            //System.out.println("fixing base constraints");
            _cons.move(oldBegin, oldEnd, newBegin, newEnd);
            //System.out.println("base = "+this);

            //System.out.println("fixing dual constraints");
            _dual.getConstraint().dualMoved(oldBegin, oldEnd, newBegin, newEnd);
            //System.out.println("dual = "+dual);
        }
        
        // checkBoundaries();

        //System.out.println("fixing base boundaries");
        _begin = newBegin;
        _end = newEnd;
        //System.out.println("base = "+this);

        boolean swapped = false;
        if ( _begin.getID() > _end.getID()) {
            _label = _label.getInverse();
            Boundary swap = _begin;
            _begin = _end;
            _end = swap;

            //System.out.println("inverting");
            //System.out.println("base = "+this);
            
            if (!this.isConstant()) {

                Base dual = this.getDual();
                //System.out.println("dual = "+dual);


                boolean opp2 = this.opposesDual();

                if (opp1 != !opp2) {
                    throw new RuntimeException("Base.checkBoundaries: insanity");
                }
            }
        }
        
        checkBoundaries();
    }

    private void checkBoundaries() {

        if ( _begin.getID() > _end.getID()) {
            throw new RuntimeException("Base.checkBoundaries failed");
        }

        if (this.isConstant()) return;
        
        Constraint cons=this.getConstraint();
        Constraint consDual=this.getDual().getConstraint();
        Base dual=this.getDual();

        //System.out.println("CHK base = "+this+" begin:"+getBegin()+" end:"+getEnd());
        //System.out.println("CHK dual = "+dual+" begin:"+dual.getBegin()+" end:"+dual.getEnd());

        if (this.opposesDual()) {
            if (consDual.getDual(dual.getEnd())!=getBegin()) {
                throw new RuntimeException("Base.checkBoundaries: failed at duals end");
            }
            if (consDual.getDual(dual.getBegin())!=getEnd()) {
                throw new RuntimeException("Base.checkBoundaries: failed at duals begin");
            }
            if (cons.getDual(getBegin())!=dual.getEnd()) {
                throw new RuntimeException("Base.checkBoundaries: failed at begin");
            }
            if (cons.getDual(getEnd())!=dual.getBegin()) {
                throw new RuntimeException("Base.checkBoundaries: failed at end");
            }
        }
        else {
            if (consDual.getDual(dual.getBegin())!=getBegin()) {
                throw new RuntimeException("Base.checkBoundaries: failed at duals end");
            }
            if (consDual.getDual(dual.getEnd())!=getEnd()) {
                throw new RuntimeException("Base.checkBoundaries: failed at duals begin");
            }
            if (getBegin()!=getEnd() && cons.getDual(getBegin())!=dual.getBegin()) {
                throw new RuntimeException("Base.checkBoundaries: failed at begin");
            }
            if (getBegin()!=getEnd() && cons.getDual(getEnd())!=dual.getEnd()) {
                throw new RuntimeException("Base.checkBoundaries: failed at end");
            }
        }
    }

    private boolean opposesDual() {
        if (this.isConstant()) {
            throw new RuntimeException("Base.opposesDual: cnnot be called on constant");
        }

        if (!this.isConstant() &&
             (( this.getLabel().isPositive() &&
               !this.getDual().getLabel().isPositive()) ||
              (!this.getLabel().isPositive() &&
                this.getDual().getLabel().isPositive()))) {
            return true;
        }
        return false;
    }
    
    public boolean isEmpty() {
        return this.getBegin() == this.getEnd();
    }
    
    boolean isConstant() {
        return this.getLabel().isConstant();
    }
    
    public Letter getLabel() {
        return _label;
    }

    boolean contains(Boundary b) {
        if (_begin==null) {
            throw new RuntimeException("Base.contains: _begin == null");
        }
        if (_end==null) {
            throw new RuntimeException("Base.contains: _end == null");
        }
        if (b==null) {
            throw new RuntimeException("Base.contains: b == null");
        }
        
        return (b.getID() >= _begin.getID() &&
                b.getID() <= _end.getID());
    }

    public Constraint getConstraint() {
        if (this.getLabel().isConstant()) {
            throw new RuntimeException("Base.getConstraint: constant base has no constraint");
        }
        return _cons;
    }

    void _setConstraint(Constraint c) {
        if (this.getLabel().isConstant()) {
            throw new RuntimeException("Base.getConstraint: constant base has no constraint");
        }
        _cons = c;
    }

    public Base getDual() {
        if (this.getLabel().isConstant()) {
            throw new RuntimeException("Base.getDual: constant base has no dual");
        }
        return _dual;
    }

    void setDual(Base dual) {
        if (this.getDual() != null) {
            throw new RuntimeException("Base.setDual: base already has dual ");
        }
        if (this.getLabel().isConstant()) {
            throw new RuntimeException("Base.setDual: constant base has no dual");
        }
        _dual = dual;
    }

    BaseSet getOwner() {
        return _owner;
    }

    void _setOwner(BaseSet owner) {
        if (this.getOwner() != null && owner!=null) {
            throw new RuntimeException("Base._setOwner: base is already owned");
        }
        _owner = owner;
    }

    public String toString() {
        String s = "";
        s += "[";
        s += _begin.toString();
        s += "-";
        s += _end.toString();
        s += ":";
        s += _label.toString();
        s += "] ";
        s += this.hashCode();
        if ( ! this.isConstant()) {
            s += " >> ";
            s += this.getDual().hashCode();
            s += " :: ";
            s += _cons.toString();
        }
        return s;
    }


    public String toStringShort() {
        String s = "";
        s += "[";
        s += _begin.toString();
        s += "-";
        s += _end.toString();
        s += ":";
        s += _label.toString();
        s += "] ";
        return s;
    }

    void validate(BaseSet bset) {
        validate_one(bset);
        if ( ! this.getLabel().isConstant()) {
            this._dual.validate_one(bset);
        }
    }

    void validate_one(BaseSet bset) {
        if (_owner != bset) {
            throw new RuntimeException("Base.validate failed");
        }
        if (!this.getLabel().isConstant() && this.getDual().getDual()!=this) {
            throw new RuntimeException("Base.validate failed");
        }
        if (_begin.getOwner().getOwner() != bset.getOwner()) {
            throw new RuntimeException("Base.validate failed");
        }
        if (_end.getOwner().getOwner() != bset.getOwner()) {
            throw new RuntimeException("Base.validate failed");
        }
        if (this.getLabel().isConstant() && _begin.getID()+1!=_end.getID()) {
            throw new RuntimeException("Base.validate failed: "+_begin+" - "+_end);
        }
        this.getOwner().getOwner().validate(_begin);
        this.getOwner().getOwner().validate(_end);

        if ( _begin.getID() > _end.getID()) {
            throw new RuntimeException("Base.validate failed");
        }
        
        if ( ! this.getLabel().isConstant()) {

            try {
                _cons.validate(this);
            }
            catch (RuntimeException ex) {
                System.out.println("BASE: "+this);
                throw ex;
            }
        }
    }
}
