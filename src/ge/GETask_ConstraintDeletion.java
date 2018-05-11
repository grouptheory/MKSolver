/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ge;

/**
 *
 * @author grouptheory
 */
public class GETask_ConstraintDeletion implements IGETask {
    private Base _bs;
    private Boundary _bd;
    private Boundary _bd_dual;
    private GE _geq;
    private String _msg;
    private Boundary _cr;
    private Boundary _cr_tr;


    public GETask_ConstraintDeletion(Base bs, Boundary bd, Boundary bd_dual, GE geq) {
        this(bs, bd, bd_dual, geq, null, null);
    }

    public GETask_ConstraintDeletion(Base bs, Boundary bd, Boundary bd_dual, GE geq,
           Boundary cr, Boundary cr_tr) {
        if (bs==null) {
            throw new RuntimeException("GETask_ConstraintDeletion.ctor: bs == null");
        }
        if (bd==null) {
            throw new RuntimeException("GETask_ConstraintDeletion.ctor: bd == null");
        }
        if (bd_dual==null) {
            throw new RuntimeException("GETask_ConstraintDeletion.ctor: bd_dual == null");
        }
        
        _bs = bs;
        _bd = bd;
        _bd_dual = bd_dual;
        _geq = geq;
        _cr = cr;
        _cr_tr = cr_tr;
        
        _msg = log();
    }

    public void execute() {
        // System.out.println("executing: "+_msg);
        _geq.removeConstraint(_bs, _bd, _bd_dual, _cr, _cr_tr);
    }

    public String toString() {
        return _msg;
    }
    
    private String log() {
        String s="";
        s += "Deleted constraint between boundary "+_bd+" in (old) base "+_bs.toStringShort()+" and boundary "+_bd_dual+" in its dual.";
        return s;
    }
}

