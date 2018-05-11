/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ge;

import java.util.Comparator;

/**
 *
 * @author grouptheory
 */
public class BaseComparator implements Comparator {
    public int compare(Object o1, Object o2) {
        Base b1 = (Base)o1;
        Base b2 = (Base)o2;

        if (b1.getOwner().getOwner() != b2.getOwner().getOwner()) {
            throw new RuntimeException("BaseComparator.compareTo: incomparable GEs");
        }
        
        // carrier must be first
        // nonempty variable with the smallest left boundary (and longest length in case of ties)

        // variables < constants
        if (!b1.isConstant() && b2.isConstant()) {
            return -1;
        }
        else if (b1.isConstant() && !b2.isConstant()) {
            return +1;
        }
        else {
            // nonempty < empty
            if (!b1.isEmpty() && b2.isEmpty()) {
                return -1;
            }
            else if (b1.isEmpty() && !b2.isEmpty()) {
                return +1;
            }
            else {
                // small left boundary < big left boundary
                 if (b1.getBegin().getID() < b2.getBegin().getID()) {
                    return -1;
                }
                else if (b1.getBegin().getID() > b2.getBegin().getID()) {
                    return +1;
                }
                else {
                    // longer < shorter
                    int len1 = b1.getEnd().getID() - b1.getBegin().getID();
                    int len2 = b2.getEnd().getID() - b2.getBegin().getID();
                    if (len1 > len2) {
                        return -1;
                    }
                    else if (len1 < len2) {
                        return +1;
                    }
                    else {
                        // earlier label < later label
                        if (b1.getLabel().getID() < b2.getLabel().getID()) {
                            return -1;
                        }
                        else if (b1.getLabel().getID() > b2.getLabel().getID()) {
                            return +1;
                        }
                        else {
                            // smaller hashcode < greater hashcode
                            if (b1.hashCode() < b2.hashCode()) {
                                return -1;
                            }
                            else if (b1.hashCode() > b2.hashCode()) {
                                return +1;
                            }
                            else {
                                // equal!
                                return 0;
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean equals(Object obj) {
        return obj==this;
    }
}
