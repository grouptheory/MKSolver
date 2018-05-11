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
