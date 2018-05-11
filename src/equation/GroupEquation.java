/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package equation;

import java.util.LinkedList;
import java.util.Iterator;
import letter.Letter;
import letter.LetterFactory;

/**
 *
 * @author grouptheory
 */
public class GroupEquation {

    private LinkedList _letters;

    public GroupEquation(String s) {
        _letters = new LinkedList();
        _letters.addAll(LetterFactory.instance().parse(s));
    }

    private GroupEquation(GroupEquation eq) {
        _letters = new LinkedList();
        _letters.addAll(eq._letters);
    }

    public GroupEquation duplicate() {
        return new GroupEquation(this);
    }

    public GroupEquation inverse() {
        GroupEquation e2 = new GroupEquation();
        for (Iterator it = _letters.iterator(); it.hasNext(); ) {
            Letter let = (Letter)it.next();
            e2._letters.addFirst(let.getInverse());
        }
        return e2;
    }

    public static GroupEquation concatenate(GroupEquation eq1, GroupEquation eq2) {
        GroupEquation cat = new GroupEquation();
        cat._letters.addAll(eq1._letters);
        cat._letters.addAll(eq2._letters);
        return cat;
    }

    public static boolean equals(GroupEquation eq1, GroupEquation eq2) {
        if (eq1.length() != eq2.length()) return false;
        Iterator it1 = eq1._letters.iterator();
        Iterator it2 = eq2._letters.iterator();
        while (it1.hasNext() && it2.hasNext()) {
            Letter let1 = (Letter)it1.next();
            Letter let2 = (Letter)it2.next();
            if (let1 != let2) return false;
        }
        return true;
    }

    public String toString() {
        String s = "";
        for (Iterator it = _letters.iterator(); it.hasNext(); ) {
            Letter let = (Letter)it.next();
            s += (let.toString());
        }
        return s;
    }
    
    public GroupEquation() {
        _letters = new LinkedList();
    }
    
    public void appendLetter(Letter let) {
        _letters.addLast(let);
    }

    public int length() {
        return _letters.size();
    }

    public Letter popLetter() {
        return (Letter)_letters.removeFirst();
    }

    public LetterIterator getLetterIterator() {
        LetterIterator letit = new LetterIterator();
        return letit;
    }

    public class LetterIterator {
        private java.util.Iterator _iterator;
        
        LetterIterator() {
            _iterator = _letters.iterator();
        }

        public Letter next() {
            return (Letter)_iterator.next();
        }
        
        public Boolean hasNext() {
            return _iterator.hasNext();
        }
    }
}
