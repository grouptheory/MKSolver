/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utility;

/**
 *
 * @author grouptheory
 */
public abstract class AbstractComposableIterator implements ComposableIterator {

    private Object _state;

    public Object getState() {
        return _state;
    }
    
    public void setState(Object o) {
        _state = o;
    }

    private ComposableIterator _p;

    public ComposableIterator getParent() {
        return _p;
    }

    public void setParent(ComposableIterator p) {
        _p = p;
    }
}
