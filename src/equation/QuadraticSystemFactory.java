/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package equation;

import letter.Letter;
import letter.Variable;
import letter.LetterFactory;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author grouptheory
 */
public class QuadraticSystemFactory {

    private static QuadraticSystemFactory _instance;

    private QuadraticSystemFactory() {
    }

    public static QuadraticSystemFactory instance() {
        if (_instance == null) {
            _instance = new QuadraticSystemFactory();
        }
        return _instance;
    }

    public QuadraticSystem newQuadraticSystem(GroupEquation eqn) {
        QuadraticSystem qs = new QuadraticSystem();


        HashSet vars = new HashSet();
        for (GroupEquation.LetterIterator it = eqn.getLetterIterator(); it.hasNext();) {
            Letter let = it.next();
            vars.add(let);
        }
        
        HashMap letter2count = new HashMap();
        for (GroupEquation.LetterIterator it = eqn.getLetterIterator(); it.hasNext();) {
            Letter let = it.next();

            if ( ! let.isConstant()) {
                Variable var = (Variable)let;
                int ct = getCount(letter2count, var);
                if (ct==2) {
                    Variable subs = LetterFactory.instance().newUnusedVariable(vars,0,var.isPositive());
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
