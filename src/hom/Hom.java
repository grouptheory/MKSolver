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

package hom;

import equation.GroupWord;

import letter.Variable;
import java.util.HashMap;
import java.util.Iterator;
import letter.Letter;

/**
 * A free group homomorphism.
 *
 * @author grouptheory
 */
public class Hom {

    private HashMap _images;

    /**
     * An identity homomorphism.
     */
    public Hom() {
        _images = new HashMap();
    }

    /**
     * Augment this homomorphism by mapping v -> homv
     *
     * @param v a Variable
     * @param homv a GroupWord
     */
    public void augment(Variable v, GroupWord homv) {

        GroupWord test = homv.duplicate().reduce();
        if (test.length()==1 && test.popLetter()==v) {
            // don't bother adding identity mappings
            return;
        }
            
        GroupWord image;
        if ( ! v.isPositive()) {
            v = (Variable) v.getInverse();
            image = homv.inverse();
        }
        else {
            image = homv.duplicate();
        }
        image = image.reduce();

        if (_images.containsKey(v)) {
            throw new RuntimeException("Hom.augment: duplicate image in Hom");
        }
        _images.put(v, image);
    }

    /**
     * Determine the GroupWord obtained when one applies this Hom to a GroupWord.
     *
     * @param eq the argument to this Hom.
     * @return the result of applying this Hom to eq.
     */
    public GroupWord apply(GroupWord eq) {
        GroupWord homeq = new GroupWord();
        for (Iterator it=eq.getLetterIterator(); it.hasNext();) {
            Letter let=(Letter)it.next();
            homeq = GroupWord.concatenate(homeq, apply(let));
        }
        homeq = homeq.reduce();
        return homeq;
    }

    /**
     * Test if this Hom is an identity Hom.
     *
     * @return true iff this Hom is the identity on F.
     */
    public boolean isIdentity() {
        return (_images.size() == 0);
    }

    /**
     * Gets an Iterator over the sequence of Letters in the domain of this Hom
     * (which are not mapped via the identity).
     *
     * @return an Iterator.
     */
    public DomainIterator getDomainIterator() {
        DomainIterator letit = new DomainIterator();
        return letit;
    }

    /**
     *
     * Gets the String representation of this Hom.
     *
     * @return the String representation.
     */
    public String toString() {
        String s = "";

        for (DomainIterator it=this.getDomainIterator(); it.hasNext();) {
            Variable v = it.next();
            GroupWord hv = apply(v);
            s += (" " + v + " --> " + hv.toString()+"; ");
        }
        return s;
    }

    /**
     * Determine the hom h2 o h1:F -> F, where h1 and h2 are both Homs.
     *
     * @param h1 the first Hom.
     * @param h2 the second Hom.
     * @return the composite h2 o h1
     */
    public static Hom compose(Hom h1, Hom h2) {
        Hom h2h1 = new Hom();
        for (Iterator it=h1.getDomainIterator(); it.hasNext();) {
            Variable v = (Variable)it.next();
            GroupWord h1_v = h1.apply(v);
            GroupWord h2h1_v = h2.apply(h1_v);
            h2h1.augment(v, h2h1_v);
        }

        for (Iterator it=h2.getDomainIterator(); it.hasNext();) {
            Variable v = (Variable)it.next();
            if ( ! h1.isNontrivialOn(v)) {
                // h1 is trivial on v, but h2 is not.
                GroupWord h2h1_v = h2.apply(v);
                h2h1.augment(v, h2h1_v);
            }
        }
        return h2h1;
    }

    /**
     * Returns the rank of the domain over which this Hom is nontrivial.
     *
     * @return the rank.
     */
    public int getNontrivialDomain() {
        return _images.size();
    }
    
    private boolean isNontrivialOn(Letter let) {
        if (_images.containsKey(let)) {
            return true;
        }
        else {
            return false;
        }
    }

    private GroupWord apply(Letter let) {
        boolean inv = false;
        if ( ! let.isPositive()) {
            let = (Variable) let.getInverse();
            inv = true;
        }
        
        GroupWord homlet = new GroupWord();
        if (isNontrivialOn(let)) {
            homlet = GroupWord.concatenate(homlet, (GroupWord)_images.get(let));
        }
        else {
            homlet.appendLetter(let);;
        }

        if (inv) {
            homlet = homlet.inverse();
        }
        return homlet;
    }


    /**
     * An Iterator over the Letters in the domain of this Hom.
     */
    public class DomainIterator implements Iterator {
        private java.util.Iterator _iterator;

        DomainIterator() {
            _iterator = _images.keySet().iterator();
        }

        /**
         * Get the next domain Variable.
         *
         * @return the next Variable
         */
        public Variable next() {
            return (Variable)_iterator.next();
        }

        /**
         * Determine if the Iterator has more Variables to yield.
         * 
         * @return true if there are more Variable in the domain of the Hom.
         */
        public boolean hasNext() {
            return _iterator.hasNext();
        }

        /**
         * Not supported.
         */
        public void remove() {
            throw new RuntimeException("DomainIterator.remove: not implemented");
        }
    }
}
