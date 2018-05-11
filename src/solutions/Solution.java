/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package solutions;

import letter.Variable;
import equation.GroupEquation;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import utility.ConsoleLogger;

/**
 *
 * @author grouptheory
 */
public class Solution {

    private HashMap _sol;

    public Solution() {
        _sol = new HashMap();
    }

    public GroupEquation get(Variable v) {
        if ( ! _sol.containsKey(v)) {
            throw new RuntimeException("Solution.get unsolved variable.");
        }
        return (GroupEquation)_sol.get(v);
    }

    public void put(Variable v, GroupEquation eq) {
        if (_sol.containsKey(v)) {
            throw new RuntimeException("Solution.put rebinding variable.");
        }
        if (_sol.containsKey(v.getInverse())) {
            GroupEquation eqInverse = (GroupEquation) get((Variable)v.getInverse());
            GroupEquation eq2 = eqInverse.inverse();
            if ( ! GroupEquation.equals(eq, eq2)) {
                ConsoleLogger.instance().fatal("Solution", "v="+v);
                ConsoleLogger.instance().fatal("Solution", "sol(v)="+eq);
                ConsoleLogger.instance().fatal("Solution", "existing v-inv="+v.getInverse());
                ConsoleLogger.instance().fatal("Solution", "existing sol(v-inv)="+eq2);
                throw new RuntimeException("Solution.put a variable and its inverse must have inverse solutions.");
            }
        }
        _sol.put(v, eq);
    }

    public String toString() {
        String s = "";
        for (Iterator it = _sol.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry ent = (Map.Entry)it.next();
            s += (ent.getKey() + "\t:\t" + ent.getValue() + "\n");
        }
        return s;
    }
}
