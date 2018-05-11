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

import letter.Letter;
import letter.Variable;
import letter.LetterFactory;
import java.util.HashMap;
import java.util.HashSet;

/**
 * A singleton factory which turns general equations
 * (i.e. a GroupWord) into a QuadraticSystem.
 *
 * @author grouptheory
 */
public class QuadraticSystemFactory {

    private static QuadraticSystemFactory _instance;

    private QuadraticSystemFactory() {
    }

    /**
     * Singleton getter.
     * 
     * @return the lone QuadraticSystemFactory instance.
     */
    public static QuadraticSystemFactory instance() {
        if (_instance == null) {
            _instance = new QuadraticSystemFactory();
        }
        return _instance;
    }

    /**
     * Factory method which creates a QuadraticSystem from a general equation
     * as embodied in a GroupWord.
     *
     * @param eqn the general equation.
     * @return the equivalent QuadraticSystem.
     */
    public QuadraticSystem newQuadraticSystem(GroupWord eqn) {
        QuadraticSystem qs = new QuadraticSystem();


        HashSet vars = new HashSet();
        for (GroupWord.LetterIterator it = eqn.getLetterIterator(); it.hasNext();) {
            Letter let = it.next();
            vars.add(let);
        }
        
        HashMap letter2count = new HashMap();
        for (GroupWord.LetterIterator it = eqn.getLetterIterator(); it.hasNext();) {
            Letter let = it.next();

            if ( ! let.isConstant()) {
                Variable var = (Variable)let;
                int ct = getCount(letter2count, var);
                if (ct==2) {
                    Variable subs = LetterFactory.instance().getUnusedVariable(vars,0,var.isPositive());
                    vars.add(subs);
                    
                    qs.appendLetter(subs);
                    qs.addEquivalence(var, subs);
                }
                else {
                    qs.appendLetter(var);
                    incrementCount(letter2count, var);
                }
            }
            else {
                qs.appendLetter(let);
            }
        }
        return qs;
    }

    private int getCount(HashMap letter2count, Letter let) {
        Letter key;
        if (let.isPositive()) {
            key = let;
        }
        else {
            key = let.getInverse();
        }
        Integer ct = (Integer)letter2count.get(key);
        if (ct==null) return 0;
        else return ct.intValue();
    }


    private void incrementCount(HashMap letter2count, Letter let) {
        Letter key;
        if (let.isPositive()) {
            key = let;
        }
        else {
            key = let.getInverse();
        }
        Integer ct = (Integer)letter2count.get(key);
        if (ct==null) letter2count.put(key, 1);
        else {
            letter2count.put(key, 1+ct.intValue());
        }
    }
}
