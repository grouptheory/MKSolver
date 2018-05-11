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

package ge;

import equation.GroupWord;
import equation.QuadraticSystem;
import equation.QuadraticSystemFactory;
import cancellation.Diagram;
import cancellation.DiagramDegeneracyTester;
import cancellation.ComposableDiagramIterator;
import cancellation.DiagramTreeNode;
import utility.CompositeIterator;

/**
 *
 * @author grouptheory
 */
public class TopLevelGEIterator {

    private GroupWord _eq;
    private QuadraticSystem _qs;
    private GroupWord _problemQuadratic;
    private ComposableDiagramIterator _cdi;
    private CompositeIterator _compiter;

    private GE _nextGE;
    private boolean _finished;
    
    TopLevelGEIterator(GroupWord eq) {
        _eq = eq.duplicate();
        _qs = QuadraticSystemFactory.instance().newQuadraticSystem(_eq);
        _problemQuadratic = _qs.getQuadraticEquation();
        _cdi = new ComposableDiagramIterator(_problemQuadratic);
        _compiter = new CompositeIterator(_cdi, false);
        
        _nextGE = nextGE();
    }

    private GE nextGE() {
        boolean finished = false;
        GE geq = null;

        GEDegeneracyTester tester;
        do {
            DiagramTreeNode nextDTN = nextDiagramTreeNode();
            if (nextDTN != null) {
                Diagram nextDiag = nextDTN.getDiagram();
                GEFactory gef = GEFactory.instance();
                geq = gef.newGE(nextDiag, _qs);
            }
            else {
                finished=true;
                break;
            }
            tester = new GEDegeneracyTester(geq);
        }
        while (tester.isDegenerate());

        if (finished) {
            return null;
        }
        else {
            return geq;
        }
    }

    private DiagramTreeNode nextDiagramTreeNode() {
        DiagramTreeNode answer = null;
        while (_compiter.hasNext()) {
            CompositeIterator.State state = (CompositeIterator.State)_compiter.next();
            DiagramTreeNode dtn = (DiagramTreeNode)state.getLeafIteratorState();
            Diagram nextDiag = dtn.getDiagram();

            if (!DiagramDegeneracyTester.isDegenerate(nextDiag) && dtn.getLeaf()) {
                answer = dtn;
                break;
            }
        }
        return answer;
    }
    
    public boolean hasNext() {
        return (_nextGE!=null);
    }

    public Object next() {
        GE nextGE = _nextGE;
        _nextGE = nextGE();
        return nextGE;
    }

    public void remove() {
        throw new RuntimeException("TopLevelGEIterator.remove: not implemented");
    }
}
