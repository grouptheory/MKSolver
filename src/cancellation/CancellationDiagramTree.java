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

/**
 *
 * @author grouptheory
 */
class CancellationDiagramTree 
        extends AbstractCancellationDiagramAnalysis {
    
    private LinkedList _diagrams;
    
    CancellationDiagramTree(GroupWord problem, QuadraticSystem qs, LinkedList diagrams) {
        super(problem, qs);

        _diagrams = new LinkedList();
        _diagrams.addAll(diagrams);
    }

    int getDiagramTreeNodesCount() {
        return _diagrams.size();
    }

    public Iterator iteratorDiagramTreeNodes() {
        // return _diagrams.iterator();
        return new AbstractCancellationDiagramAnalysis.FilteredIterator(_diagrams.iterator());
    }

    public String toString() {
        String s = "";
        s += "CancellationDiagramTree: ";
        s += super.toString();
        
        s+="\n"+_diagrams.size()+" cancellation diagrams enumerated:\n";
        for (Iterator it = _diagrams.iterator();it.hasNext(); ) {
            DiagramTreeNode dtn2 = (DiagramTreeNode)it.next();
            s+=dtn2.getDiagram().toString();
        }
        s += "\n";
        return s;
    }
}
