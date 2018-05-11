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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Iterator;
import letter.Variable;
import letter.Letter;

/**
 * A quadratic system is a quadratic equation, together with a set of equalities.
 * Any single GroupWord (i.e. equation) can be represented as a quadratic system.
 *
 * @author grouptheory
 */
public class QuadraticSystem {
    private GroupWord _eqn;
    private Equivalences _equivalences;

    QuadraticSystem() {
        _eqn = new GroupWord();
        _equivalences = new Equivalences();
    }
    
    void appendLetter(Letter let) {
        _eqn.appendLetter(let);
    }

    void addEquivalence(Variable v1, Variable v2) {
        if (v1 == v2) {
            throw new RuntimeException("QuadraticSystem.addEquivalence: v1 == v2");
        }
        _equivalences.put(v1, v2);
    }

    /**
     * Gets the quadratic equation associated with this QuadraticSystem.
     *
     * @return GroupWord, that is guaranteed to be a degree 2 GroupWord.
     */
    public GroupWord getQuadraticEquation() {
        return _eqn.duplicate();
    }
    
    /**
     * Gets a map of Variable equivalences associated with this QuadraticSystem.
     * 
     * @return a HashMap listing each Variable pair that are equal.
     */
    public Equivalences getEquivalences() {
        return _equivalences.duplicate();
    }

    /**
     * Gets the String representation of this QuadraticSystem (both the
     * quadratic GroupWord, and the map of equivalent Variables).
     *
     * @return the String representation.
     */
    public String toString() {
        String s = "";
        s += _eqn.toString() + "=1; \n";
        for (Iterator it=_equivalences.iteratorVariables(); it.hasNext();) {
            Variable let = (Variable)it.next();
            s += (let.toString() + "="+ ((Variable)_equivalences.get(let)).toString() + "; \n");
        }
        s += "\n";
        return s;
    }
}
