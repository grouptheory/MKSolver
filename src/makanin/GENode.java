/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package makanin;

import ge.GE;
import ge.Base;
import ge.Boundary;
import ge.GEDegeneracyTester;
import ge.GETrivialityTester;
import utility.ClassLog;
import java.util.LinkedList;

/**
 *
 * @author grouptheory
 */
public class GENode {
    private GENode _parent;
    private Print _print;
    private PrintApplicator _pa;
    private GE _geq;
    private GEDegeneracyTester _degeneracy_me;
    private GETrivialityTester _triviality_me;
    private boolean _leaf;
    private boolean _solvable;
    private String _name;
    private int _j;
    private ClassLog _log;
    
    public GENode(GE root, String name) {
        _log = new ClassLog();
        _parent = null;
        _print = null;
        _name = name;
        _geq = root.duplicate();
        makeLogEntry_root();
        initialize();
    }

    private GENode(GENode parent, Print print, String name, int j) {
        _log = new ClassLog();
        _parent = parent;
        _print = print;
        _name = name;
        _pa = new PrintApplicator(parent._geq, print);
        _geq = _pa.getPrinted();
        _j = j;

        makeLogEntry_nonroot();
        initialize();
    }

    public String getLog() {
        return _log.toString();
    }

    public void closeLog(String s) {
        if (_parent != null) {
            _log.append("This completes the consideration of "+s+".\\\\"+params.MKParams.REPORT_SPACING+"\n");
        }
        else {
            _log.append("We proceed.\\\\[0.2in]\n");
        }
    }

    public void appendToLog(String s) {
        _log.append(s);
    }

    private void initialize() {
        CarrierFactory.applyToGE(_geq);
        CriticalBoundaryFactory.applyToGE(_geq);
        BaseClassDecoratorFactory.applyToAllBases(_geq);

        _degeneracy_me = new GEDegeneracyTester(_geq);
        _triviality_me = new GETrivialityTester(_geq);

        if (_degeneracy_me.isDegenerate()) {
            _leaf = true;
            _solvable = false;
            
            // System.out.println("ABC degeneracy "+degeneracy_me.toString());
        }
        else if(_triviality_me.isTrivial()) {
            _leaf = true;
            _solvable = true;

            // System.out.println("ABC triviality "+triviality_me.toString());
        }
        else {
            _leaf = false;
            _solvable = true; // wishful thinking
        }

        makeLogEntry_common();
    }

    LinkedList expand() {
        LinkedList children = new LinkedList();
        if (_leaf) {
            return children;
        }
        
        int j=1;
        for (PrintIterator pi = PrintIteratorFactory.instance().newPrintIterator(_geq);
             pi.hasNext();) {
            Print p = (Print)pi.next();

            String child_name = this._name + "." + j;
            
            GENode childNode = new GENode(this, p, child_name, j);
            children.addLast(childNode);
            
            j++;
        }
        return children;
    }

    public String toString() {
        String s = "";
        s += _name;
        s += ":";
        if (_leaf) {
            if (_solvable) {
                s +="true";
            }
            else {
                s +="false";
            }
        }
        else {
            s +="unresolved";
        }
        return s;
    }

    public String getName() {
        return _name;
    }

    public GE getGE() {
        return _geq;
    }

    public GENode getParent() {
        return _parent;
    }

    public boolean isSolvable() {
        return _solvable;
    }

    public boolean isLeaf() {
        return _leaf;
    }

    Boundary getCorrespondingParentBoundary(Boundary bd) {
        return _pa.New2OldBoundary(bd);
    }

    Base getCorrespondingParentBase(Base bs) {
        return _pa.New2OldBase(bs);
    }
    
    private void makeLogEntry_root() {
        String s = "";
        s += "Below is the root GE obtained from the cancellation diagram above.";
        s+=ge.Latex.instance().renderGEasGraphics(_geq);
        _log.append(s);
    }

    private void makeLogEntry_nonroot() {
        String s = "";
        s+=("{We consider its print}\n");
        s+="\\begin{verbatim}\n";
        s+=("     Print "+_j+": "+_print+"\n");
        s+="\\end{verbatim}\n";
        s+=("{\\bf Sequence of actions in performing the Print "+_j+":}\\\\\n");
        s+=_pa.toString();
        s+=("{Upon applying the print, the GE we obtain---which we refer to as "+_name+"---is illustrated below:}\n");
        s+=ge.Latex.instance().renderGEasGraphics(_geq);
        _log.append(s);
    }

    void makeLogEntry_common() {
        String s = "";

        if (params.MKParams.FLAG_REPORT_MAKANIN_CARRIER) {
            s += makanin.Latex.instance().renderGEasText(_geq);
        }

        if (_degeneracy_me.isDegenerate()) {
            s+=("Observe the following facts about this GE:\n");
            s+=_degeneracy_me.toString();
            s+=("These observations show that the GE above is degenerate.  ");
        }
        else {
            s+=("The GE above is non-degenerate.  ");
        }
        appendToLog(s);
    }
}
