/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package makanin;

import ge.Boundary;
import ge.Base;
import ge.GE;
import java.util.LinkedList;
import java.util.Iterator;
import letter.Letter;
import letter.Variable;

/**
 *
 * @author grouptheory
 */
public class PrintNode {
    
    static class Offset {
        private String _name;
        private Offset(String name) { _name = name; }
        public String toString() { return _name; }
    };

    static Offset ZERO = new Offset("ZERO");
    static Offset NONZERO = new Offset("NONZERO");

    static class Source {
        private String _name;
        private Source(String name) { _name = name; }
        public String toString() { return _name; }
    };

    static Source CARRIER_TR = new Source("CARRIER_TR");
    static Source DUAL = new Source("DUAL");

    private LinkedList _ca_bd;
    private LinkedList _caDual_bd;
    private PrintNode.Offset _offset;
    private Boundary _bd;
    private PrintNode.Source _source;

    PrintNode(GE geq) {
        this();

        Carrier ca = (Carrier)geq.lookupDecorator(Carrier.NAME);
        if (ca == null) {
            throw new RuntimeException("gutierrez.Main: unknown carrier");
        }
        Base carrier_base = ca.getBase();
        if (carrier_base == null) {
            throw new RuntimeException("gutierrez.Main: carrier_base is null");
        }

        _offset = PrintNode.ZERO;
        _bd = null;
        _source = null;

        for (int i=carrier_base.getBegin().getID(); i<=carrier_base.getEnd().getID(); i++) {
            Boundary bd = geq.getNthBoundary(i);
            _ca_bd.addLast(bd);
        }

        Base carrier_dual = carrier_base.getDual();

        Variable v = (Variable)carrier_base.getLabel();
        Variable vdual = (Variable)carrier_dual.getLabel();

        boolean flipDualBoundaries = false;
        if ((v.isPositive() && !vdual.isPositive()) ||
            (!v.isPositive() && vdual.isPositive())) {
            flipDualBoundaries = true;
        }
        
        for (int i=carrier_dual.getBegin().getID(); i<=carrier_dual.getEnd().getID(); i++) {
            Boundary bd = geq.getNthBoundary(i);
            if (!flipDualBoundaries) {
                _caDual_bd.addLast(bd);
            }
            else {
                _caDual_bd.addFirst(bd);
            }
            //System.out.print(""+bd+",");
        }
        //System.out.println("");
    }

    PrintNode(LinkedList ca_bd, LinkedList caDual_bd, 
              PrintNode.Offset offset, Boundary bd, PrintNode.Source source) {
        this();
        _ca_bd.addAll(ca_bd);
        _caDual_bd.addAll(caDual_bd);
        _offset = offset;
        _bd = bd;
        _source = source;
    }

    private PrintNode() {
        _ca_bd = new LinkedList();
        _caDual_bd = new LinkedList();
        _offset = PrintNode.ZERO;
        _bd = null;
        _source = null;
    }

    PrintNode.Source getSource() {
        return _source;
    }

    PrintNode.Offset getOffset() {
        return _offset;
    }

    Boundary getBoundary() {
        return _bd;
    }
    
    PrintNode consumeCarrierBoundary(PrintNode.Offset offset) {
        LinkedList clist = new LinkedList();
        clist.addAll(_ca_bd);
        Boundary bd = (Boundary)clist.getFirst();
        clist.removeFirst();
        return new PrintNode(clist, _caDual_bd, offset, bd, PrintNode.CARRIER_TR);
    }

    PrintNode consumeCarrierDualBoundary(PrintNode.Offset offset) {
        LinkedList clist = new LinkedList();
        clist.addAll(_caDual_bd);
        Boundary bd = (Boundary)clist.getFirst();
        clist.removeFirst();
        return new PrintNode(_ca_bd, clist, offset, bd, PrintNode.DUAL);
    }

    int remainingCarrierBoundaries() {
        return _ca_bd.size();
    }

    int remainingCarrierDualBoundaries() {
        return _caDual_bd.size();
    }

    public String toString() {
        String s="";

        if (_offset==PrintNode.ZERO) {
            s += "=";
        }
        else if (_offset==PrintNode.NONZERO) {
            s += "<";
        }
        else {
            throw new RuntimeException("PrintNode.toString: unknown offset");
        }

        if (_bd==null) {
            s += "[]";
        }
        else {
            s += _bd;

            if (_source==PrintNode.CARRIER_TR) {
                s += "";
            }
            else if (_source==PrintNode.DUAL) {
                s += "*";
            }
        }

        return s;
    }

    public String toStringLong() {
        String s="";

        s+="Offset: ";
        if (_offset==PrintNode.ZERO) {
            s += "=";
        }
        else if (_offset==PrintNode.NONZERO) {
            s += "<";
        }
        else {
            throw new RuntimeException("PrintNode.toString: unknown offset");
        }
        s+="\n";
        
        s+="Boundary: ";
        s += _bd;
        s+="\n";

        s+="Carrier boundaries: ";
        for (Iterator it=_ca_bd.iterator(); it.hasNext();) {
            Boundary bd = (Boundary)it.next();
            s+=bd;
            if (it.hasNext()) s+=",";
        }
        s+="\n";
        
        s+="Carrier Dual boundaries: ";
        for (Iterator it=_caDual_bd.iterator(); it.hasNext();) {
            Boundary bd = (Boundary)it.next();
            s+=bd;
            if (it.hasNext()) s+=",";
        }
        s+="\n";

        return s;
    }
}
