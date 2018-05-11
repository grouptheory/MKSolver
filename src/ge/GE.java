/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ge;

import utility.AbstractDecorable;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.Iterator;
import letter.Constant;
import letter.Variable;
import letter.Letter;
import equation.GroupEquation;

/**
 *
 * @author grouptheory
 */
public class GE extends AbstractDecorable {
    private BoundarySet _bdSet;
    private BaseSet _bsSet;

    GE() {
        setBoundarySet(new BoundarySet(this));
        setBaseSet(new BaseSet(this));

        appendNewBoundary();
    }

    private void setBoundarySet(BoundarySet bdSet) {
        _bdSet = bdSet;
    }

    private void setBaseSet(BaseSet bsSet) {
        _bsSet = bsSet;
    }

    public GE duplicate() {
        TreeMap old2new_bdmap = new TreeMap();
        HashMap old2new_bsmap = new HashMap();
        return duplicate(old2new_bdmap, old2new_bsmap);
    }


    public GE duplicate(TreeMap old2new_bdmap, HashMap old2new_bsmap) {
        GE ge2 = new GE();
        BoundarySet bd2 = this._bdSet.duplicate(ge2, old2new_bdmap);
        ge2.setBoundarySet(bd2);

        BaseSet bs2 = this._bsSet.duplicate(ge2, old2new_bdmap, old2new_bsmap);
        ge2.setBaseSet(bs2);

        return ge2;
    }

    public Iterator iteratorBoundaries() {
        return _bdSet.iterator();
    }

    public Boundary appendNewBoundary() {
        return _bdSet.appendNewBoundary();
    }

    public Boundary insertNewBoundaryAfter(Boundary b) {
        return _bdSet.insertNewBoundaryAfter(b);
    }

    public Boundary insertNewBoundaryBefore(Boundary b) {
        return _bdSet.insertNewBoundaryBefore(b);
    }

    public int getNumberOfBoundaries() {
        return _bdSet.getNumberOfBoundaries();
    }

    public boolean isUseless(Boundary b) {
        int index = _bdSet.getIndex(b);

        for (Iterator it = this.iteratorBases(); it.hasNext();) {
            Base bs = (Base)it.next();

            if (bs.getBegin() == b) return false;
            if (bs.getEnd() == b) return false;

            if (bs.getLabel().isConstant()) continue;
            
            Constraint c = bs.getConstraint();
            for (Iterator itcon=c.iteratorBoundary(); itcon.hasNext();) {
                Boundary bdused = (Boundary)itcon.next();
                if (bdused == b) {
                    return false;
                }
                Boundary bdusedDual = (Boundary)c.getDual(bdused);
                if (bdusedDual == b) {
                    return false;
                }
            }
        }
        return true;
    }

    public void removeBoundary(Boundary b) {
        validate();
        if ( ! isUseless(b)) {
            throw new RuntimeException("You are removing a non-useless boundary!");
        }
        _bdSet.remove(b);
        validate();
    }

    public Boundary getFirstBoundary() {
        return _bdSet.getFirst();
    }

    public Boundary getNthBoundary(int i) {
        return _bdSet.getNth(i);
    }

    public Boundary getLastBoundary() {
        return _bdSet.getLast();
    }

    public Boundary nextBoundary(Boundary bd) {
        return _bdSet.nextBoundary(bd);
    }

    public Boundary prevBoundary(Boundary bd) {
        return _bdSet.prevBoundary(bd);
    }
    
    public Base addNewConstantBase(Boundary begin, Constant c) {
        Boundary end = _bdSet.nextBoundary(begin);
        if (end==null) {
            end = _bdSet.appendNewBoundary();
        }
        Base bs = new Base(begin, end, c);
        _bsSet.add(bs);
        return bs;
    }

    public Base addNewVariableBase(Boundary begin, Boundary end, Variable v,
                                   Boundary beginDual, Boundary endDual) {
        Base bs = new Base(begin, end, v);

        Variable vDual = v;
        if (beginDual.getID() > endDual.getID()) {
            vDual = (Variable)v.getInverse();
            Boundary swap = beginDual;
            beginDual = endDual;
            endDual = swap;
        }
        Base bsDual = new Base(beginDual, endDual, vDual);

        bs.setDual(bsDual);
        bsDual.setDual(bs);

        _bsSet.add(bs);
        _bsSet.add(bsDual);

        bs.getConstraint().initialize();
        bsDual.getConstraint().initialize();
        
        return bs;
    }

    public Iterator iteratorBases() {
        return _bsSet.iterator();
    }

    public int getNumberOfBases() {
        return _bsSet.getNumberOfBases();
    }

    public void addNewConstraint(Base bs, Boundary bd, Boundary bdDual) {
        if (bs==null) {
            throw new RuntimeException("GE.addNewConstraint: bs == null");
        }
        if (bd==null) {
            throw new RuntimeException("GE.addNewConstraint: bd == null");
        }
        if (bdDual==null) {
            throw new RuntimeException("GE.addNewConstraint: bdDual == null");
        }
        if (bs.getOwner()!=_bsSet) {
            throw new RuntimeException("GE.addNewConstraint: bs is bad");
        }
        if (bd.getOwner()!=_bdSet) {
            throw new RuntimeException("GE.addNewConstraint: bd is bad");
        }
        if (bdDual.getOwner()!=_bdSet) {
            throw new RuntimeException("GE.addNewConstraint: bdDual is bad");
        }
        Constraint c = bs.getConstraint();
        c.add(bd, bdDual);

        Base bsDual = bs.getDual();
        Constraint cDual = bsDual.getConstraint();
        cDual.add(bdDual, bd);
    }

    public void removeConstraint(Base bs, Boundary bd, Boundary bdDual, Boundary cr, Boundary cr_tr) {
        if (bs==null) {
            throw new RuntimeException("GE.removeConstraint: bs == null");
        }
        if (bd==null) {
            throw new RuntimeException("GE.removeConstraint: bd == null");
        }
        if (bdDual==null) {
            throw new RuntimeException("GE.removeConstraint: bdDual == null");
        }
        Constraint c = bs.getConstraint();
        if (c.size() == 2) {
            if (cr!=null && cr_tr!=null) {
                c.add(cr, cr_tr);
            }
        }
        c.remove(bd, bdDual);

        Base bsDual = bs.getDual();
        Constraint cDual = bsDual.getConstraint();
        if (cDual.size() == 2) {
            if (cr!=null && cr_tr!=null) {
                cDual.add(cr_tr, cr);
            }
        }
        cDual.remove(bdDual, bd);

        bs.set(c.minimal(), c.maximal());
        bsDual.set(cDual.minimal(), cDual.maximal());

        if (c.minimal()!=bs.getBegin()) {
            throw new RuntimeException("GE.removeConstraint: minimal is not _begin");
        }
        if (c.maximal()!=bs.getEnd()) {
            throw new RuntimeException("GE.removeConstraint: maximal is not _end");
        }
        if (cDual.minimal()!=bsDual.getBegin()) {
            throw new RuntimeException("GE.removeConstraint: in dual minimal is not _begin");
        }
        if (cDual.maximal()!=bsDual.getEnd()) {
            throw new RuntimeException("GE.removeConstraint: in dual maximal is not _end");
        }
    }

    public void removeBase(Base bs) {
        if (bs==null) {
            throw new RuntimeException("GE.removeBase: bs == null");
        }
        _bsSet.remove(bs);
    }

    public void collapseBase(Base bs) {
        if (bs==null) {
            throw new RuntimeException("GE.collapseBase: bs == null");
        }
        if (bs.getLabel().isConstant()) {
            throw new RuntimeException("GE.collapseBase: bs == constant");
        }

                //System.out.println("XXXX COLLAPSE "+bs);

        Base dual = bs.getDual();
        if (!dual.isEmpty() &&
           ((bs.getBegin() != dual.getBegin()) ||
            (bs.getEnd() != dual.getEnd()) ||
            (bs.getLabel() != dual.getLabel()))) {
            throw new RuntimeException("GE.collapseBase: bs not collapsible");
        }

        Constraint c = bs.getConstraint();
        do {
            if (c.size() > 2) {
                Iterator it = c.iteratorBoundary();
                Boundary bd = (Boundary)it.next();
                bd = (Boundary)it.next();
                
                Boundary bdDual = c.getDual(bd);
                removeConstraint(bs,bd, bdDual, null, null);
            }
        }
        while (c.size() > 2);

        bs.collapse();
    }

    public String toString() {
        String s = "";
        s += _bdSet.toString();
        s += "\n";
        s += _bsSet.toString();
        s += "\n";
        return s;
    }

    public void validate() {
        _bsSet.validate(this);
        _bdSet.validate(this);
    }

    void validate(Boundary b) {
        _bdSet.validate(b);
    }
}
