/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ge;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author grouptheory
 */
public class GEDegeneracyTester implements IDegeneracyTestLog {

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
    
    private LinkedList _conditions;
    private void AddCondition(IGEDegeneracyCondition cond) {
        _conditions.addLast(cond);
    }
    

    private boolean _isDegenerate;
    
    public GEDegeneracyTester(GE geq) {
        _conditions = new LinkedList();
        AddCondition(new GEDegeneracy_ConditionA());
        AddCondition(new GEDegeneracy_ConditionB());
        AddCondition(new GEDegeneracy_ConditionC());
        AddCondition(new GEDegeneracy_ConditionD());
        AddCondition(new GEDegeneracy_ConditionE());

        _evidence = new LinkedList();
        _isDegenerate = compute(geq);
    }

    public boolean isDegenerate() {
        return _isDegenerate;
    }
    
    private boolean compute(GE geq) {
        for (Iterator it = _conditions.iterator(); it.hasNext();) {
            IGEDegeneracyCondition cond = (IGEDegeneracyCondition)it.next();
            if (cond.test(geq, this)) {
                return true;
            }
        }
        return false;
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
