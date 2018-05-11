/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utility;

/**
 *
 * @author grouptheory
 */
public class IntegerComposableIterator 
        extends AbstractComposableIterator
        implements ComposableIterator {

    private int _base;
    private int _depth;
    private int _value;
    
    IntegerComposableIterator(int base, int depth) {
        _base = base;
        _depth = depth;
        _value = -1;
        
        setState(null);
    }

    public ComposableIterator newComposableIterator(ComposableIterator parent) {
        setParent(parent);
        if (_depth > 1) {
            return new IntegerComposableIterator(_base, _depth-1);
        }
        else {
            return null;
        }
    }

    public boolean hasNext() {
        if (_value < _base-1) return true;
        else return false;
    }

    public Object next() {
        _value++;
        return new Integer(_value);
    }

    public void remove() {
        throw new RuntimeException("CompositeIterator.remove: not implemented");
    }


    public String toString() {
        String s="";
        s += " ICI("+_value+")";
        return s;
    }
}
