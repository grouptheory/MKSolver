/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ge;

import java.util.LinkedList;
import java.util.Iterator;

/**
 *
 * @author grouptheory
 */
public class GETrivialityTester {

    class Evidence {
        private String _s;

        Evidence(String s) {
            _s = s;
        }

        public String toString() {
            return _s;
        }
    }

    private LinkedList _evidence;

    public void reportEvidence(String s) {
        _evidence.addLast(new Evidence(s));
    }
    
    private boolean _isTrivial;

    public GETrivialityTester(GE geq) {
        _evidence = new LinkedList();
        _isTrivial = compute(geq);
    }

    public boolean isTrivial() {
        return _isTrivial;
    }

    private boolean compute(GE geq) {
        boolean answer = true;
        for (Iterator it = geq.iteratorBases(); it.hasNext();) {
            Base bs = (Base)it.next();
            if (bs.getLabel().isConstant()) continue;
            if ( ! bs.isEmpty()) {
                reportEvidence("Variable base "+bs.toStringShort()+" is not empty.  ");
                answer = false;
            }
        }
        return answer;
    }

    public String toString() {
        String s="";
        for (Iterator it = _evidence.iterator(); it.hasNext();) {
            Evidence ev=(Evidence)it.next();
            s+=(""+ev);
        }
        return s;
    }
}
