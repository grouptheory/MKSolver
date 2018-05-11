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
import ge.Base;
import java.util.Iterator;
import utility.CompositeIterator;
import utility.CompositeIterator.State;

/**
 *
 * @author grouptheory
 */
public class PrintIterator implements Iterator {

    private GE _geq;
    private PrintProbe _pp;
    private Iterator _it;
    private Print _nextPrint;
    
    PrintIterator(GE geq) {
        _geq = geq;
        _pp = new PrintProbe(geq);
        _it = _pp.iteratorTreeNodes();
        
        _nextPrint = nextPrint();
    }

    public boolean hasNext() {
        return (_nextPrint!=null);
    }

    public Object next() {
        Print nextPrint = _nextPrint;
        _nextPrint = nextPrint();
        return nextPrint;
    }

    public void remove() {
        throw new RuntimeException("PrintIterator.remove: not implemented");
    }

    private Print nextPrint() {
        Print answer = null;
        while (_it.hasNext()) {
            Print pr = new Print(isCarrierFlipped());
            CompositeIterator.State cs = (CompositeIterator.State)_it.next();

            for (Iterator itsub=cs.iteratorComposableStates(); itsub.hasNext();) {
                PrintNode pn = (PrintNode)itsub.next();
                pr.append(pn);
            }

            if (PrintValidator.validate(pr, _geq)) {
                answer = pr;
                break;
            }
        }
        return answer;
    }


    private Base getCarrier() {
        Carrier ca = (Carrier)_geq.lookupDecorator(Carrier.NAME);
        if (ca == null) {
            throw new RuntimeException("PrintApplicator.Main: unknown carrier");
        }
        Base carrier_base = ca.getBase();
        if (carrier_base == null) {
            throw new RuntimeException("PrintApplicator.Main: carrier_base is null");
        }
        return carrier_base;
    }

    private Base getCarrierDual() {
        Base carrier_base = getCarrier();
        Base carrier_dual = carrier_base.getDual();
        return carrier_dual;
    }

    private boolean isCarrierFlipped() {
        Base carrier = getCarrier();
        Base carrierDual = getCarrierDual();

        if (( carrier.getLabel().isPositive() && !carrierDual.getLabel().isPositive()) ||
            (!carrier.getLabel().isPositive() &&  carrierDual.getLabel().isPositive())) {
            return true;
        }
        else return false;

    }
}
