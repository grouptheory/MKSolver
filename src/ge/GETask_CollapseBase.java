/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ge;

/**
 *
 * @author grouptheory
 */
public class GETask_CollapseBase implements IGETask {

    private Base _bs;
    private GE _geq;
    private String _msg;

    public GETask_CollapseBase(Base bs, GE geq) {
        if (bs==null) {
            throw new RuntimeException("GETask_CollapseBase.ctor: bs == null");
        }

        if (bs.getLabel().isConstant()) {
            throw new RuntimeException("GETask_CollapseBase.ctor: bs == constant");
        }

        Base dual = bs.getDual();

        if (!dual.isEmpty() &&
           ((bs.getBegin() != dual.getBegin()) ||
            (bs.getEnd() != dual.getEnd()) ||
            (bs.getLabel() != dual.getLabel()))) {
            throw new RuntimeException("GETask_CollapseBase.ctor: bs not collapsible");
        }

        _bs = bs;
        _geq = geq;
        _msg = log();
    }

    public void execute() {
        // System.out.println("executing: "+_msg);
        _geq.collapseBase(_bs);
    }

    public String toString() {
        return _msg;
    }

    private String log() {
        String s="";
        s += "Collapsed (new) base "+_bs.toStringShort()+" to the empty base ("+_bs.getEnd()+","+_bs.getEnd()+").\n";
        return s;
    }
}
