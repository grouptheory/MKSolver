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

import ge.Base;
import ge.Boundary;
import ge.GE;
import java.util.Iterator;
import java.util.HashMap;

/**
 *
 * @author grouptheory
 */
public class BaseClassDecoratorFactory {

    private HashMap _base2class;
    
    public static void applyToAllBases(GE geq) {
        BaseClassDecoratorFactory bcf = new BaseClassDecoratorFactory(geq);
        for (Iterator it=geq.iteratorBases(); it.hasNext();) {
            Base bs=(Base)it.next();
            BaseClassDecorator bcd = bcf.newBaseClassDecorator(bs);
            bcd.attach(BaseClassDecorator.NAME, bs);
        }
    }

    private BaseClassDecoratorFactory(GE geq) {

        Carrier ca = (Carrier)geq.lookupDecorator(Carrier.NAME);
        if (ca == null) {
            throw new RuntimeException("BaseClassDecoratorFactory.ctor: unknown carrier");
        }
        Base carrier_base = ca.getBase();
        if (carrier_base == null) {
            throw new RuntimeException("BaseClassDecoratorFactory.ctor: carrier_base is null");
        }

        CriticalBoundary cr = (CriticalBoundary)geq.lookupDecorator(CriticalBoundary.NAME);
        if (cr == null) {
            throw new RuntimeException("BaseClassDecoratorFactory.ctor: unknown critical boundary");
        }
        Boundary critical_boundary = cr.getBoundary();
        if (critical_boundary == null) {
            throw new RuntimeException("BaseClassDecoratorFactory.ctor: critical_boundary is null");
        }

        _base2class = new HashMap();

        BaseClassDecorator abcCarrier = new BaseClassCarrier();
        _base2class.put(carrier_base, abcCarrier);

        for (Iterator it=geq.iteratorBases(); it.hasNext();) {
            Base bs=(Base)it.next();
            if (bs==carrier_base) continue;
            
            if (bs.isEmpty() && bs.getBegin().getID()<carrier_base.getBegin().getID()) {
                BaseClassDecorator abc = new BaseClassSuperfluous();
                _base2class.put(bs, abc);
            }
            else if(carrier_base.getBegin().getID() <= bs.getBegin().getID() &&
                bs.getBegin().getID() < critical_boundary.getID()) {
                BaseClassDecorator abc = new BaseClassTransport();
                _base2class.put(bs, abc);
            }
            else {
                BaseClassDecorator abc = new BaseClassFixed();
                _base2class.put(bs, abc);
            }
        }
    }

    private BaseClassDecorator newBaseClassDecorator(Base bs) {
        return (BaseClassDecorator)_base2class.get(bs);
    }
}
