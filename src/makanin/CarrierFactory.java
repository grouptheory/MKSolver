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
import ge.GE;
import java.util.TreeSet;
import java.util.Iterator;
import ge.BaseComparator;

/**
 *
 * @author grouptheory
 */
public class CarrierFactory {
    
    public static void applyToGE(GE geq) {
        Carrier ca = CarrierFactory.compute(geq);
        ca.attach(Carrier.NAME, geq);
    }

    private static Carrier compute(GE geq) {
        TreeSet ts = new TreeSet(new BaseComparator());
        for (Iterator it=geq.iteratorBases(); it.hasNext();) {
            Base bs=(Base)it.next();
            ts.add(bs);
        }
        Base cb = (Base)ts.first();

        Carrier carrier = new Carrier(cb);
        return carrier;
    }
}