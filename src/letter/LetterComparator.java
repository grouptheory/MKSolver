/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package letter;

/**
 * Allows for uniform comparisons between Letter objects.
 *
 * @author grouptheory
 */


public class LetterComparator implements java.util.Comparator {
    /**
     * Compare two letters.  Variables are < Constants.
     * Subscript (IDs) determine ordering of homogenous pairs of Letters.
     * Exponents break ties o subscript (negatives come before positives).
     *
     * @param o1 object 1.
     * @param o2 object 2.
     * @return -1 if o1<o2; +1 if o1>o2; 0 if the two are equal.
     */
    public int compare(Object o1, Object o2) {
        Letter let1=(Letter)o1;
        Letter let2=(Letter)o2;

        if (!let1.isConstant() && let2.isConstant()) return -1;
        if (let1.isConstant() && !let2.isConstant()) return +1;
        else {
            if (let1.getID() < let2.getID()) return -1;
            else if (let1.getID() > let2.getID()) return +1;
            else {
                if (!let1.isPositive() && let2.isPositive()) return -1;
                else if (let1.isPositive() && !let2.isPositive()) return +1;
                else return 0;
            }
        }
    }
}
