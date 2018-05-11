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


import letter.Letter;
import equation.GroupWord;
import java.util.LinkedList;
import java.util.Iterator;

class ExtensionVisitor {
    private int _maxdepth;
    private GroupWord _eq;

    ExtensionVisitor(GroupWord eq) {
        _eq = eq;
        _maxdepth = eq.length()+1;
    }

    ExtensionVisitor(GroupWord eq, int maxdepth) {
        _eq = eq;
        _maxdepth = maxdepth;
    }

    ExtensionVisitor makeSubVisitor() {
        GroupWord eq2 = _eq.duplicate();
        eq2.popLetter();
        ExtensionVisitor ev2 = new ExtensionVisitor(eq2, _maxdepth);
        return ev2;
    }

    void visit(DiagramTreeNode dtn) {
        if (dtn.getDepth() < _maxdepth) {
            if (_eq.length()>0) {
                GroupWord eq2 = _eq.duplicate();
                Letter let = eq2.popLetter();
                
                boolean last = false;
                if (_eq.length() == 1) last = true;
                dtn.extend(let, last);
            }
            else {
                dtn.setLeaf();
            }
        }
    }
}
