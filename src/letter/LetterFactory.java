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

package letter;

import java.util.TreeMap;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Iterator;

/**
 * Singleton class which makes {@link Constant} and {@link Variable} objects.
 *
 * @author grouptheory
 */
public class LetterFactory {

    private TreeMap _id2constant_pos;
    private TreeMap _id2constant_neg;
    private TreeMap _id2variable_pos;
    private TreeMap _id2variable_neg;
    private int _highconstant;
    private int _highvariable;

    private static LetterFactory _instance;

    private LetterFactory() {
        utility.ConsoleLogger.instance().debug(this.getClass().getSimpleName(),
                "LetterFactory.ctor");

        _id2constant_pos = new TreeMap();
        _id2constant_neg = new TreeMap();
        _id2variable_pos = new TreeMap();
        _id2variable_neg = new TreeMap();
        _highconstant = 0;
        _highvariable = 0;
    }

    /**
     * Gets singleton LetterFactory instance.
     *
     * @return the unique LetterFactory in existence.
     */
    public static LetterFactory instance() {
        if (_instance == null) {
            _instance = new LetterFactory();
        }
        return _instance;
    }

    /**
     * Parses a String into a LinkedList of Letter objects.  The
     * invariant being that parse(s) returns a list of Letter objects such
     * that the concatenation of the return values of toString()
     * on these Letter objects, would once again yield s.
     *
     * @param s the String.
     * @return a LinkedList of Letter objects.
     */
    public LinkedList parse(String s) {
        LinkedList letters = new LinkedList();
        LetterReaderState lrs;
        do {
            lrs = readNextLetter(s);
            s = lrs._remaining;
            letters.addLast(lrs._extracted);
        }
        while (s.length() > 0);
        return letters;
    }

    /**
     * Creates a new previously unused constant.
     * 
     * @param pos indicates if the exponent is +1 if pos is true, and -1 if pos is false.
     * @return a new Constant object.
     */
    public Constant newUnusedConstant(Boolean pos) {
        Constant c = getConstant(_highconstant+1, pos);
        if (Safety.CHECK) {
            utility.ConsoleLogger.instance().debug(this.getClass().getSimpleName(),
                "LetterFactory.newUnusedConstant returning "+c);
        }
        return c;
    }

    /**
     * Gets a Constant with a given ID (subscript), creating it for the first time
     * if necessary.
     *
     * @param id the subscript.
     * @param pos the exponent (+1 if pos is true, -1 otherwise).
     * @return a Constant object (either pre-existing, or newly made).
     */
    public Constant getConstant(int id, Boolean pos) {
        if (Safety.CHECK && id < 0) {
            throw new RuntimeException("LetterFactory.getConstant: id < 0");
        }

        Constant c = null;
        if (pos) {
            c = (Constant)_id2constant_pos.get(new Integer(id));
        }
        else {
            c = (Constant)_id2constant_neg.get(new Integer(id));
        }

        if (c == null) {
            c = new Constant(id, pos);
            Constant cinv = new Constant(id, !pos);

            utility.ConsoleLogger.instance().debug(this.getClass().getSimpleName(),
                "LetterFactory.getConstant created "+c+" and "+cinv);

            c.setInverse(cinv);
            cinv.setInverse(c);
            if (pos) {
                _id2constant_pos.put(new Integer(id), c);
                _id2constant_neg.put(new Integer(id), cinv);
            }
            else {
                _id2constant_neg.put(new Integer(id), c);
                _id2constant_pos.put(new Integer(id), cinv);
            }
            if (_highconstant < id) {
                _highconstant = id;
            }
        }
        return c;
    }

    /**
     * Creates a new previously unused variable.
     *
     * @param pos indicates if the exponent is +1 if pos is true, and -1 if pos is false.
     * @return a new Variable object.
     */
    public Variable newUnusedVariable(Boolean pos) {
        Variable v = getVariable(_highvariable+1, pos);
        if (Safety.CHECK) {
            utility.ConsoleLogger.instance().debug(this.getClass().getSimpleName(),
                "LetterFactory.newUnusedVariable returning "+v);
        }
        return v;
    }

    private Variable newUnusedVariable(int minID, Boolean pos) {
        if (minID > _highvariable+1) {
            _highvariable = minID-1;
            if (Safety.CHECK) {
                utility.ConsoleLogger.instance().debug(this.getClass().getSimpleName(),
                "LetterFactory.newUnusedVariable _highvariable updated to "+_highvariable);
            }
        }
        
        Variable v = getVariable(_highvariable+1, pos);
        if (Safety.CHECK) {
            utility.ConsoleLogger.instance().debug(this.getClass().getSimpleName(),
                "LetterFactory.newUnusedVariable returning "+v);
        }
        return v;
    }

    /**
     * Return a variable whose index that is outside of a given set of off-limit indices
     * and is of a minimum size.
     *
     * @param used list of Variables whose indices are off-limits.
     * @param minID the minimum value of the index.
     * @param pos
     * @return a Variable object.
     */
    public Variable getUnusedVariable(HashSet used, int minID, Boolean pos) {
        TreeMap srch;
        if (pos) {
            srch = _id2variable_pos;
        }
        else {
            srch = _id2variable_neg;
        }

        Variable answer = null;
        for (Iterator it=srch.values().iterator(); it.hasNext();) {
            Variable v = (Variable)it.next();
            if (used.contains(v) || used.contains(v.getInverse())) continue;
            else {
                if (v.getID() < minID) continue;
                else {
                    answer = v;
                    break;
                }
            }
        }

        if (answer==null) {
            answer = newUnusedVariable(minID, pos);
        }

        return answer;
    }

    /**
     * Gets a variable of a specified subscript and exponent (+1 or -1), making the Variable for the
     * first time if necessary.
     *
     * @param id the subscript.
     * @param pos the exponent (+1 if pos is true, -1 otherwise).
     * @return the requested Variable.
     */
    public Variable getVariable(int id, Boolean pos) {
        if (Safety.CHECK && id < 0) {
            throw new RuntimeException("LetterFactory.getVariable: id < 0");
        }

        Variable v = null;
        if (pos) {
            v = (Variable)_id2variable_pos.get(new Integer(id));
        }
        else {
            v = (Variable)_id2variable_neg.get(new Integer(id));
        }

        if (v == null) {
            v = new Variable(id, pos);
            Variable vinv = new Variable(id, !pos);

            if (Safety.CHECK) {
                utility.ConsoleLogger.instance().debug(this.getClass().getSimpleName(),
                "LetterFactory.getVariable created "+v+" and "+vinv);
            }

            v.setInverse(vinv);
            vinv.setInverse(v);
            if (pos) {
                _id2variable_pos.put(new Integer(id), v);
                _id2variable_neg.put(new Integer(id), vinv);
            }
            else {
                _id2variable_neg.put(new Integer(id), v);
                _id2variable_pos.put(new Integer(id), vinv);
            }
            if (_highvariable < id) {
                _highvariable = id;
            }
        }
        return v;
    }

    
    private class LetterReaderState {
        public String _remaining;
        public Letter _extracted;
    }

    private LetterReaderState readNextLetter(String s) {
        
        Boolean var = false;
        if (s.charAt(0) == Letter.VARIABLE_SYMBOL.charAt(0)) {
            var = true;
        }
        else if (s.charAt(0) == Letter.CONSTANT_SYMBOL.charAt(0)) {
            var = false;
        }
        else {
            throw new RuntimeException("Variable.ctor: bad type");
        }

        int endofletter = s.indexOf(Letter.ENDOFLETTER_SYMBOL);

        String idstr = s.substring(1, endofletter-1);

        int id = Integer.parseInt(idstr);

        String posstr = s.substring(endofletter-1, endofletter);
        Boolean pos = false;
        if (posstr.compareTo(Letter.POSITIVE_SYMBOL)==0) {
            pos = true;
        }
        else if (posstr.compareTo(Letter.NEGATIVE_SYMBOL)==0) {
            pos = false;
        }
        else {
            throw new RuntimeException("Variable.ctor: bad pos/neg");
        }

        Letter let;
        if (var) {
            let = getVariable(id, pos);
        }
        else {
            let = getConstant(id, pos);
        }

        LetterReaderState lrs = new LetterReaderState();
        lrs._extracted = let;
        lrs._remaining = s.substring(endofletter+1);

        return lrs;
    }

    /**
     * Creates a String representation of this LetterFactory.
     * 
     * @return a String.
     */
    public String toString() {
        String s = "";
        s+="_id2constant_pos size = "+_id2constant_pos.size()+"\n";
        s+="_id2constant_neg size = "+_id2constant_neg.size()+"\n";
        s+="_id2variable_pos size = "+_id2variable_pos.size()+"\n";
        s+="_id2variable_neg size = "+_id2variable_neg.size()+"\n";
        return s;

    }
}
