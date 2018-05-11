/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package makanin;

import ge.Boundary;
import java.util.Iterator;
import java.util.LinkedList;
/**
 *
 * @author grouptheory
 */
public class Print {

    private LinkedList _nodes;
    private boolean _flipped;
    
    Print(boolean flipped) {
        _nodes = new LinkedList();
        _flipped = flipped;
    }

    void append(PrintNode pn) {
        _nodes.addLast(pn);
    }

    PrintNode nextPrintNode(PrintNode pn) {
        int idx = _nodes.indexOf(pn);
        if (idx == _nodes.size()-1) {
            return null;
        }
        else {
            return (PrintNode)_nodes.get(idx+1);
        }
    }


    PrintNode prevPrintNode(PrintNode pn) {
        int idx = _nodes.indexOf(pn);
        if (idx == 0) {
            return null;
        }
        else {
            return (PrintNode)_nodes.get(idx-1);
        }
    }

    public String toString() {
        String s="";
        for (Iterator it=_nodes.iterator();it.hasNext();) {
            PrintNode pn = (PrintNode)it.next();
            s += pn.toString();
        }
        return s;
    }

    public Iterator iteratorPrintNodes(boolean ascending) {
        if (ascending) {
            return _nodes.iterator();
        }
        else {
            return _nodes.descendingIterator();
        }
    }

    PrintNode get(Boundary b1, PrintNode.Source s1) {
        for (Iterator it=_nodes.iterator();it.hasNext();) {
            PrintNode pn = (PrintNode)it.next();
            if ((pn.getSource() == s1) && (pn.getBoundary()==b1)) {
                return pn;
            }
        }
        return null;
    }
    
    boolean contains(Boundary b1, PrintNode.Source s1) {
        return get(b1,s1)!=null;
    }

    Boundary getBegin() {
        Iterator it = (!_flipped) ? _nodes.iterator() : _nodes.descendingIterator();
        for (;it.hasNext();) {
            PrintNode pn = (PrintNode)it.next();
            if (pn.getSource() == PrintNode.DUAL) {
                return pn.getBoundary();
            }
        }
        return null;
    }

    Boundary getEnd() {
        Iterator it = (!_flipped) ? _nodes.descendingIterator() : _nodes.iterator() ;
        for (;it.hasNext();) {
            PrintNode pn = (PrintNode)it.next();
            if (pn.getSource() == PrintNode.DUAL) {
                return pn.getBoundary();
            }
        }
        return null;
    }

    int compare(Boundary b1, PrintNode.Source s1, Boundary b2, PrintNode.Source s2) {
        
        PrintNode p1 = null;
        PrintNode p2 = null;
        PrintNode first = null;
        for (Iterator it=_nodes.iterator();it.hasNext();) {
            PrintNode pn = (PrintNode)it.next();
            if ((pn.getSource() == s1) && (pn.getBoundary()==b1)) {
                p1 = pn;
                if (first==null) first=p1;
            }
            if ((pn.getSource() == s2) && (pn.getBoundary()==b2)) {
                p2 = pn;
                if (first==null) first=p2;
            }
            if (p1!=null && p2!=null) {
                break;
            }
        }

        // System.out.println("Comparing "+b1+":"+s1.toString()+" with "+b2+":"+s2.toString());

        if (p1==null) {
            throw new RuntimeException("Print.compare: boundary "+b1+" not found in "+s1);
        }

        if (p2==null) {
            throw new RuntimeException("Print.compare: boundary "+b2+" not found in "+s2);
        }

        int i1=_nodes.indexOf(p1);
        int i2=_nodes.indexOf(p2);
        int low,high;
        if (first==p1) {
            low=i1; high=i2;
        }
        else {
            low=i2; high=i1;
        }

        int distance = 0;
        for (int i=low+1; i<=high; i++) {
            PrintNode pn = (PrintNode)_nodes.get(i);
            if (pn.getOffset() == PrintNode.NONZERO) {
                distance++;
            }
        }

        distance *= ((first==p1) ? -1 : +1);

        return distance;
    }
}
