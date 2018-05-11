/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ge;

/**
 *
 * @author grouptheory
 */
public class GETask_DeleteBase implements IGETask {

    private Base _bs;
    private GE _geq;
    private String _msg;

    public GETask_DeleteBase(Base bs, GE geq) {
        if (bs==null) {
            throw new RuntimeException("GETask_DeleteBase.ctor: bs == null");
        }
        _bs = bs;
        _geq = geq;
        _msg = log();
    }

    public void execute() {
        // System.out.println("executing: "+_msg);
        _geq.removeBase(_bs);
    }

    public String toString() {
        return _msg;
    }

    private String log() {
        String s="";
        s += "Deleted (new) base "+_bs.toStringShort()+" because it begins to the left of the critical boundary.\n";
        return s;
    }
}

