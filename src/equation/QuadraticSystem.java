/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package equation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Iterator;
import letter.Variable;
import letter.Letter;

/**
 *
 * @author grouptheory
 */
public class QuadraticSystem {
    private GroupEquation _eqn;
    private HashMap _equivalences;
    private HashMap _equivalencesReverse;

    QuadraticSystem() {
        _eqn = new GroupEquation();
        _equivalences = new HashMap();
        _equivalencesReverse = new HashMap();
    }
    
    void appendLetter(Letter let) {
        _eqn.appendLetter(let);
    }

    void addEquivalence(Variable v1, Variable v2) {
        if (v1 == v2) {
            throw new RuntimeException("QuadraticSystem.addEquivalence: v1 == v2");
        }
        _equivalences.put(v1, v2);
        _equivalencesReverse.put(v2, v1);
    }

    public GroupEquation getEquation() {
        GroupEquation eqn = _eqn.duplicate();
        return eqn;
    }
    
    public HashMap getEquivalences() {
        HashMap equiv = new HashMap();
        equiv.putAll(_equivalences);
        equiv.putAll(_equivalencesReverse);
        return equiv;
    }

    public String toString() {
        String s = "";
        s += _eqn.toString() + "=1; \n";
        for (Iterator it=_equivalences.keySet().iterator(); it.hasNext();) {
            Letter let = (Letter)it.next();
            s += (let.toString() + "="+ ((Letter)_equivalences.get(let)).toString() + "; \n");
        }
        s += "\n";
        return s;
    }
}
