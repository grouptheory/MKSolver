/*
    Copyright 2008 Bilal Khan
    grouptheory@gmail.com

    This file is part of MKSolver.

    MKSolver is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    MKSolver is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
*/

package solutions;

import letter.Variable;
import equation.GroupWord;
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

    public GroupWord get(Variable v) {
        if ( ! _sol.containsKey(v)) {
            throw new RuntimeException("Solution.get unsolved variable.");
        }
        return (GroupWord)_sol.get(v);
    }

    public void put(Variable v, GroupWord eq) {
        if (_sol.containsKey(v)) {
            GroupWord eqOld = (GroupWord) get((Variable)v);
            if ( ! GroupWord.graphicallyequal(eq, eqOld)) {
                throw new RuntimeException("Solution.put rebinding variable.");
            }
        }

        if (_sol.containsKey(v.getInverse())) {
            GroupWord eqInverse = (GroupWord) get((Variable)v.getInverse());
            GroupWord eq2 = eqInverse.inverse();
            if ( ! GroupWord.graphicallyequal(eq, eq2)) {
                ConsoleLogger.instance().fatal("Solution", "v="+v);
                ConsoleLogger.instance().fatal("Solution", "sol(v)="+eq);
                ConsoleLogger.instance().fatal("Solution", "existing v-inv="+v.getInverse());
                ConsoleLogger.instance().fatal("Solution", "existing sol(v-inv)="+eq2);
                throw new RuntimeException("Solution.put a variable and its inverse must have inverse solutions.");
            }
        }
        else {
            GroupWord eqInverse = eq.inverse();
            _sol.put(v.getInverse(), eqInverse);
        }
        
        _sol.put(v, eq);
    }

    public Iterator iteratorVariables() {
        return _sol.keySet().iterator();
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
