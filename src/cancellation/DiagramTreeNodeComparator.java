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
package cancellation;

import java.util.Comparator;

/**
 * A Comparator which can order DiagramTreeNode objects.
 *
 * @author grouptheory
 */
public class DiagramTreeNodeComparator implements Comparator {

    /**
     * Compares two DiagramTreeNode objects based on their hashcode.
     * 
     * @param o1 a DiagramTreeNode
     * @param o2 another DiagramTreeNode
     * @return -1, 0, or 1 as per the convention.
     */
    public int compare(Object o1, Object o2) {
        DiagramTreeNode d1 = (DiagramTreeNode)o1;
        DiagramTreeNode d2 = (DiagramTreeNode)o2;


        if (d1.hashCode() < d2.hashCode()) {
            return -1;
        }
        else if (d1.hashCode() > d2.hashCode()) {
            return +1;
        }
        else {
            return 0;
        }
    }

    /**
     * Compares DiagramTreeNode objects based on object equality.
     *
     * @param obj
     * @return true iff this==obj.
     */
    public boolean equals(Object obj) {
        return obj==this;
    }
}
