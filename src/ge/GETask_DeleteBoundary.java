/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ge;

/**
 *
 * @author grouptheory
 */
public class GETask_DeleteBoundary implements IGETask {

    private Boundary _bd;
    private GE _geq;
    private String _msg;

    public GETask_DeleteBoundary(Boundary bd, GE geq) {
        if (bd==null) {
            throw new RuntimeException("GETask_DeleteBoundary.ctor: bd == null");
        }
        _bd = bd;
        _geq = geq;
        _msg = log();
    }

    public void execute() {
        // System.out.println("executing: "+_msg);
        _geq.removeBoundary(_bd);
    }

    public String toString() {
        return _msg;
    }

    private String log() {
        String s="";
        s += "Deleted (new) boundary "+_bd+" because it is not used inside any base.  This will cause renumbering of higher numbered boundaries.\n";
        return s;
    }
}

