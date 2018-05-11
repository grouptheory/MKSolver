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

import java.util.TreeSet;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Iterator;
import letter.Letter;
import equation.GroupWord;
import equation.QuadraticSystem;
import utility.CompositeIterator;

/**
 *
 * @author grouptheory
 */
class CancellationDiagramProbe
        extends AbstractCancellationDiagramAnalysis {
    
    CancellationDiagramProbe(GroupWord problem, QuadraticSystem qs) {
        super(problem, qs);
    }
    
    public Iterator iteratorDiagramTreeNodes() {
        Diagram d = new Diagram();
        GroupWord problemQuadratic = getQuadraticEquation();
        ComposableDiagramIterator cdi = new ComposableDiagramIterator(null, d, problemQuadratic, 0);
        CompositeIterator compiter = new CompositeIterator(cdi, true);
        
        // return compiter;
        return new AbstractCancellationDiagramAnalysis.FilteredIterator(compiter);
    }

    public String toString() {
        String s = "";
        s += "CancellationDiagramProbe: ";
        s += super.toString();
        return s;
    }
}
