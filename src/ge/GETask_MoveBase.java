/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ge;

/**
 *
 * @author grouptheory
 */
public class GETask_MoveBase implements IGETask {
    private Base _bs;
    private Boundary _begin;
    private Boundary _end;
    private GE _geq;
    private String _msg;

    public GETask_MoveBase(Base bs, Boundary begin, Boundary end, GE geq) {
        if (begin==null) {
            throw new RuntimeException("GETask_MoveBase.ctor: begin == null");
        }
        if (end==null) {
            throw new RuntimeException("GETask_MoveBase.ctor: end == null");
        }
        if (bs==null) {
            throw new RuntimeException("GETask_MoveBase.ctor: bs == null");
        }

        _bs = bs;
        _begin = begin;
        _end = end;
        _geq = geq;
        _msg = log1();
    }

    public void execute() {
        // System.out.println("executing: "+_msg);
        _bs.move(_begin, _end);
        _msg += log2();
    }

    public String toString() {
        return _msg;
    }

    private String log1() {
        String s="";
        s += "Moved (old) base "+_bs.toStringShort()+"";
        return s;
    }

    private String log2() {
        String s="";
        s += " to (new) boundaries "+_begin+" - "+_end+".";
        return s;
    }
}
