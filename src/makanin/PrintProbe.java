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
