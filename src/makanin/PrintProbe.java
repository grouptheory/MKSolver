/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package makanin;

import ge.GE;
import java.util.Iterator;
import utility.CompositeIterator;

/**
 *
 * @author grouptheory
 */
public class PrintProbe {

    private GE _geq;

    PrintProbe(GE geq) {
        _geq = geq;
    }

    public Iterator iteratorTreeNodes() {
        PrintNode pn = new PrintNode(_geq);
        boolean root = true;
        ComposablePrintNodeIterator cdi = new ComposablePrintNodeIterator(pn, root);
        CompositeIterator compiter = new CompositeIterator(cdi, false);
        return compiter;
    }

    public String toString() {
        String s = "";
        s += "PrintProbe: ";
        return s;
    }
}
