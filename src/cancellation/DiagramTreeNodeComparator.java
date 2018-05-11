/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cancellation;

import java.util.Comparator;

/**
 *
 * @author grouptheory
 */
public class DiagramTreeNodeComparator implements Comparator {

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

    public boolean equals(Object obj) {
        return obj==this;
    }
}
