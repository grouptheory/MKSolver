/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cancellation;


import letter.Letter;
import equation.GroupEquation;
import java.util.LinkedList;
import java.util.Iterator;

/**
 *
 * @author grouptheory
 */

class ExtensionVisitor {
    private int _maxdepth;
    private GroupEquation _eq;

    ExtensionVisitor(GroupEquation eq) {
        _eq = eq;
        _maxdepth = eq.length()+1;
    }

    ExtensionVisitor(GroupEquation eq, int maxdepth) {
        _eq = eq;
        _maxdepth = maxdepth;
    }

    ExtensionVisitor makeSubVisitor() {
        GroupEquation eq2 = _eq.duplicate();
        eq2.popLetter();
        ExtensionVisitor ev2 = new ExtensionVisitor(eq2, _maxdepth);
        return ev2;
    }

    void visit(DiagramTreeNode dtn) {
        if (dtn.getDepth() < _maxdepth) {
            if (_eq.length()>0) {
                GroupEquation eq2 = _eq.duplicate();
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
