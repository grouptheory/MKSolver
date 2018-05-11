/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ge;

/**
 *
 * @author grouptheory
 */
public class GETask_BoundaryInsertion implements IGETask {
    private Boundary _bd;
    private boolean _after;
    private Boundary _newbd;
    private GE _geq;
    private String _msg;

    public GETask_BoundaryInsertion(Boundary bd, boolean after, GE geq) {
        if (bd==null) {
            throw new RuntimeException("GETask_BoundaryInsertion.ctor: bd == null");
        }
        
        _bd = bd;
        _after = after;
        _geq = geq;
        _msg = null;
    }

    public void execute() {
        if (_after) {
            _newbd = _geq.insertNewBoundaryAfter(_bd);
        }
        else {
            _newbd = _geq.insertNewBoundaryBefore(_bd);
        }
        _msg = log();
        // System.out.println("executing: "+_msg);
    }

    public Boundary getNewBoundary() {
        return _newbd;
    }

    public String toString() {
        return _msg;
    }
    
    private String log() {
        String s="";
        s += "Added (new) boundary "+_newbd+".";
        return s;
    }
}
