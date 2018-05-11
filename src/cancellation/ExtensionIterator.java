/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cancellation;

import java.util.LinkedList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 *
 * @author grouptheory
 */
public class ExtensionIterator {

    private LinkedList _extensions;
    private Iterator _it;

    ExtensionIterator(LinkedList exts) {
        _extensions = new LinkedList();
        _extensions.addAll(exts);
        _it = _extensions.iterator();
    }

    boolean hasNext() {
        return _it.hasNext();
    }

    Extension next() {
        return (Extension)_it.next();
    }

    public String toString() {
        String s = "";
        for (Iterator it = _extensions.iterator(); it.hasNext();) {
            s += ((Extension)it.next()).toString()+"\n";
        }
        return s;
    }
}
