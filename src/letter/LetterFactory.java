/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package letter;

import java.util.TreeMap;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Iterator;

/**
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
        _id2constant_pos = new TreeMap();
        _id2constant_neg = new TreeMap();
        _id2variable_pos = new TreeMap();
        _id2variable_neg = new TreeMap();
        _highconstant = 0;
        _highvariable = 0;
    }

    public static LetterFactory instance() {
        if (_instance == null) {
            _instance = new LetterFactory();
        }
        return _instance;
    }

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

    public Variable newUnusedConstant(Boolean pos) {
        return getVariable(_highconstant+1, pos);
    }

    public Constant getConstant(int id, Boolean pos) {
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

    public Variable newUnusedVariable(Boolean pos) {
        //System.out.println(" high is  "+_highvariable);
        return getVariable(_highvariable+1, pos);
    }

    private Variable newUnusedVariable(int minID, Boolean pos) {
        if (minID > _highvariable+1) {
            // System.out.println("setting high to "+(minID-1));
            _highvariable = minID-1;
        }
        
        return getVariable(_highvariable+1, pos);
    }
    
    public Variable newUnusedVariable(HashSet used, int minID, Boolean pos) {
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
        
        /*
        if (minID==0) {
            System.out.println("LetterFactory used set has size: "+used.size());
            for (Iterator it=used.iterator(); it.hasNext();) {
                Letter let = (Letter)it.next();
                System.out.println("used: "+let);
            }
            System.out.println("LetterFactory _highvariable: "+_highvariable);
            if (answer==null) {
                System.out.println("LetterFactory (answer==null)");
            }
        }
        */

        if (answer==null) {
            answer = newUnusedVariable(minID, pos);
        }

        /*
        if (minID==0) {
            System.out.println("POST LetterFactory used set has size: "+used.size());
            for (Iterator it=used.iterator(); it.hasNext();) {
                Letter let = (Letter)it.next();
                System.out.println("POST used: "+let);
            }
            System.out.println("POST LetterFactory _highvariable: "+_highvariable);
            if (answer==null) {
                System.out.println("POST LetterFactory (answer==null)");
            }
            System.out.println("POST LetterFactory returning: "+answer);
        }
        */

        return answer;
    }

    public Variable getVariable(int id, Boolean pos) {
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
                // System.out.println("*** setting high to "+id);
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

        //System.out.println("called w: "+s);
        
        Boolean var = false;
        if (s.charAt(0) == Letter.VARIABLE_SYMBOL.charAt(0)) {
            //System.out.println("var");
            var = true;
        }
        else if (s.charAt(0) == Letter.CONSTANT_SYMBOL.charAt(0)) {
            //System.out.println("const");
            var = false;
        }
        else {
            throw new RuntimeException("Variable.ctor: bad type");
        }

        int endofletter = s.indexOf(Letter.ENDOFLETTER_SYMBOL);
        //System.out.println("endofletter="+endofletter);

        String idstr = s.substring(1, endofletter-1);
        //System.out.println("idstr="+idstr);

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

    public String toString() {
        String s = "";
        s+="_id2constant_pos size = "+_id2constant_pos.size()+"\n";
        s+="_id2constant_neg size = "+_id2constant_neg.size()+"\n";
        s+="_id2variable_pos size = "+_id2variable_pos.size()+"\n";
        s+="_id2variable_neg size = "+_id2variable_neg.size()+"\n";
        return s;

    }
}
