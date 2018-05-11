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

package equation;

import letter.Variable;
import java.util.HashMap;
import java.util.Iterator;

/**
 * A map of equivalent variables.  Any equation can be transformed into a system
 * consisting of a single quadratic equation and a set of variable equivalences.
 * 
 * @author grouptheory
 */
public class Equivalences {

    private HashMap _map;

    Equivalences() {
        _map = new HashMap();
    }

    /**
     * Makes a new Equivalence that is a copy of this one.
     *
     * @return Equivalence that is a copy of this one.
     */
    public Equivalences duplicate() {
        Equivalences dupe = new Equivalences();
        dupe._map.putAll(this._map);
        return dupe;
    }

    /**
     * Add a binding to say that v1 = v2.
     *
     * @param v1 a Variable.
     * @param v2 a Variable equivalent to v1.
     */
    public void put(Variable v1, Variable v2) {
        _map.put(v1, v2);
    }

    /**
     * Get a variable that v1 is bound to.
     *
     * @param v1 a Variable.
     * @return a Variable equivalent to v1.
     */
    public Variable get(Variable v1) {
        return (Variable)_map.get(v1);
    }

    /**
     * Get the number of variable equivalences
     * in this Equivalence instance.
     *
     * @return the size.
     */
    public int size() {
        return _map.size();
    }

    /**
     * Gets an Iterator over the set of key Variables.
     *
     * @return an Iterator.
     */
    public Iterator iteratorVariables() {
        return _map.keySet().iterator();
    }

}
