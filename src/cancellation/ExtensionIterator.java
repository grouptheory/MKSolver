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

import java.util.LinkedList;
import java.util.Iterator;
import java.util.ListIterator;

class ExtensionIterator {

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
