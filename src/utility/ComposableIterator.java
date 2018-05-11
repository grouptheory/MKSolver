/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utility;

import java.util.Iterator;

/**
 *
 * @author grouptheory
 */
public interface ComposableIterator extends Iterator {
    
    ComposableIterator newComposableIterator(ComposableIterator parent);
    
    Object getState();
    void setState(Object o);

    ComposableIterator getParent();
    void setParent(ComposableIterator p);
}
