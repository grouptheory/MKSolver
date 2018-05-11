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
import letter.Variable;
import equation.GroupWord;
import equation.Equivalences;
import equation.QuadraticSystem;

/**
 *
 * @author grouptheory
 */
abstract class AbstractCancellationDiagramAnalysis
    implements ICancellationDiagramAnalysis {

    private GroupWord _problem;
    private QuadraticSystem _qs;
    private Equivalences _equiv;
    private GroupWord _problemQuadratic;
    private LinkedList _decorators;

    protected AbstractCancellationDiagramAnalysis(GroupWord problem, QuadraticSystem qs) {
        _problem = problem;
        _qs = qs;

        _problemQuadratic = qs.getQuadraticEquation();
        _equiv = qs.getEquivalences().duplicate();

        _decorators = new LinkedList();
    }

    public final void addDecorator(ICancellationDiagramAnalysisDecorator dec) {
        _decorators.addLast(dec);
    }

    public final Iterator iteratorDecorators() {
        return _decorators.iterator();
    }

    public final GroupWord getProblem() {
        return _problem;
    }

    public final QuadraticSystem getQuadraticSystem() {
        return _qs;
    }

    public final GroupWord getQuadraticEquation() {
        return _problemQuadratic;
    }

    public final Equivalences getEquivalences() {
        return _equiv;
    }

    public String toString() {
        String s = "";

        s += ("Quadratic Equation: "+_problemQuadratic+" = 1\n");

        s+="\n\nVariable equivalences:\n\n";
        for (Iterator it=_equiv.iteratorVariables(); it.hasNext();) {
            Variable let = (Variable)it.next();
            s += (let.toString() + "="+ ((Letter)_equiv.get(let)).toString() + "; \n");
        }

        return s;
    }

    static class FilteredIterator implements Iterator {
        private Iterator _it;
        private DiagramTreeNode _nextDTN;

        FilteredIterator(Iterator it) {
            _it = it;
            _nextDTN = nextDTN();
        }
        
        public boolean hasNext() {
            return (_nextDTN!=null);
        }

        public Object next() {
            DiagramTreeNode nextDTN = _nextDTN;
            _nextDTN = nextDTN();
            return nextDTN;
        }

        public void remove() {
            _it.remove();
        }

        private DiagramTreeNode nextDTN() {
            DiagramTreeNode answer = null;

            while (_it.hasNext()) {
                DiagramTreeNode dtn = (DiagramTreeNode)_it.next();
                Diagram nextDiag = dtn.getDiagram();

                if (!DiagramDegeneracyTester.isDegenerate(nextDiag) && dtn.getLeaf()) {
                    answer = dtn;
                    break;
                }
            }

            return answer;
        }
    }
}
