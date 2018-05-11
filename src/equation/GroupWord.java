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

import java.util.LinkedList;
import java.util.Iterator;
import letter.Letter;
import letter.LetterFactory;

/**
 *
 * @author grouptheory
 */
public class GroupWord {

    private LinkedList _letters;

    /**
     * Construct a GroupWord from a string representation of it.
     *
     * @param s the string representation.
     */
    public GroupWord(String s) {
        _letters = new LinkedList();
        _letters.addAll(LetterFactory.instance().parse(s));
    }

    private GroupWord(GroupWord eq) {
        _letters = new LinkedList();
        _letters.addAll(eq._letters);
    }

    private GroupWord(LinkedList list) {
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Letter let = (Letter)it.next();
            // all objects in the list must be Letters.
        }
        _letters = new LinkedList();
        _letters.addAll(list);
    }

    /**
     * Make a deep copy of this GroupWord.
     *
     * @return a new GroupWord.
     */
    public GroupWord duplicate() {
        return new GroupWord(this);
    }

    /**
     * Compute the free reduction of this GroupWord.
     *
     * @return a GroupWord that is this element, but is freely reduced.
     */
    public GroupWord reduce() {
        LinkedList reduced = new LinkedList();
        for (Iterator it = _letters.iterator(); it.hasNext(); ) {
            Letter let = (Letter)it.next();
            if (reduced.size()==0) {
                reduced.addLast(let);
            }
            else {
                Letter last = (Letter)reduced.getLast();
                if (Letter.testInverse(let, last)) {
                    reduced.removeLast();
                }
                else {
                    reduced.addLast(let);
                }
            }
        }
        return new GroupWord(reduced);
    }


    /**
     * Compute the cyclic free reduction of this GroupWord.
     *
     * @return a word which represents this GroupWord's conjugacy class.
     */
    public GroupWord cyclicallyReduce() {
        GroupWord red = this.reduce();
        int len = red.length();
        int i=0;
        LinkedList cyclicallyred = new LinkedList();
        for (;i<len/2;i++) {
            Letter let1=(Letter)red._letters.get(i);
            Letter let2=(Letter)red._letters.get(len-1-i);
            if (!Letter.testInverse(let1, let2)) {
                break;
            }
        }
        for (int j=i;j<=len-1-i;j++) {
            Letter let1=(Letter)red._letters.get(i);
            cyclicallyred.addLast(let1);
        }
        return new GroupWord(cyclicallyred);
    }

    /**
     * Compute the inverse of this GroupWord.
     *
     * @return a new GroupWord which when multiplied by this GroupWord would,
     * upon free reduction, yield the identity.
     */
    public GroupWord inverse() {
        GroupWord e2 = new GroupWord();
        for (Iterator it = _letters.iterator(); it.hasNext(); ) {
            Letter let = (Letter)it.next();
            e2._letters.addFirst(let.getInverse());
        }
        return e2;
    }

    /**
     * Concatenate two group words (without performing
     * any free reduction at the boundary).
     *
     * @param eq1 the first GroupWord.
     * @param eq2 the second GroupWord.
     * @return a new GroupWord representing the product.
     */
    public static GroupWord concatenate(GroupWord eq1, GroupWord eq2) {
        GroupWord cat = new GroupWord();
        cat._letters.addAll(eq1._letters);
        cat._letters.addAll(eq2._letters);
        return cat;
    }

    /**
     * Test if two GroupWord instances are graphically equal.
     *
     * @param eq1 the first GroupWord.
     * @param eq2 the second GroupWord.
     * @return true or false depending on whether eq1 == eq2, or not.
     */
    public static boolean graphicallyequal(GroupWord eq1, GroupWord eq2) {
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

    /**
     * Compute the String representation of this GroupWord.
     *
     * @return a String.
     */
    public String toString() {
        String s = "";
        for (Iterator it = _letters.iterator(); it.hasNext(); ) {
            Letter let = (Letter)it.next();
            s += (let.toString());
        }
        return s;
    }
    
    /**
     *  Constructor returning the empty GroupWord.
     */
    public GroupWord() {
        _letters = new LinkedList();
    }
    
    /**
     * Append an initial Letter to the end of this GroupWord.
     * @param let a Letter
     */
    public void appendLetter(Letter let) {
        _letters.addLast(let);
    }

    /**
     * The graphical length of this GroupWord.
     * @return the integer length >= 0.
     */
    public int length() {
        return _letters.size();
    }

    /**
     * Pop a Letter from the beginning of this GroupWord, removing it
     * from the GroupWord in doing so.
     *
     * @return the first Letter in this Word
     */
    public Letter popLetter() {
        return (Letter)_letters.removeFirst();
    }

    /**
     * Gets an Iterator over the sequence of Letters in this GroupWord.
     * @return an Iterator.
     */
    public LetterIterator getLetterIterator() {
        LetterIterator letit = new LetterIterator();
        return letit;
    }

    /**
     * An Iterator over the Letters in this GroupWord.
     */
    public class LetterIterator implements Iterator {
        private java.util.Iterator _iterator;
        
        LetterIterator() {
            _iterator = _letters.iterator();
        }

        /**
         *
         * @return the next Letter
         */
        public Letter next() {
            return (Letter)_iterator.next();
        }
        
        /**
         *
         * @return true if there are more Letters to be returned.
         */
        public boolean hasNext() {
            return _iterator.hasNext();
        }
        
        /**
         * Not supported.
         */
        public void remove() {
            throw new RuntimeException("LetterIterator.remove: not implemented");
        }
    }
}
