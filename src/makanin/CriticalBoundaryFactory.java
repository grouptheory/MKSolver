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

/**
 *
 * @author grouptheory
 */
public class CriticalBoundaryFactory {

    public static void applyToGE(GE geq) {
        Carrier ca = (Carrier)geq.lookupDecorator(Carrier.NAME);
        if (ca == null) {
            throw new RuntimeException("CriticalBoundarySelector.applyToGE: unknown carrier");
        }
        
        CriticalBoundary cb = CriticalBoundaryFactory.compute(geq, ca);
        cb.attach(CriticalBoundary.NAME, geq);
    }

    private static CriticalBoundary compute(GE geq, Carrier carrier) {
        Base cbs = carrier.getBase();
        
        Boundary cr = cbs.getEnd();
        
        for (Iterator it=geq.iteratorBases(); it.hasNext();) {
            Base bs=(Base)it.next();
            if (bs == cbs) continue;
            // find all bs with property that end(carrier) in col(bs)
            if (bs.getBegin().getID() < cbs.getEnd().getID() &&
                bs.getEnd().getID() > cbs.getEnd().getID()) {
                if (cr == null) {
                    cr = bs.getBegin();
                }
                else {
                    // find bs with minimal begin(bs) that has the above property
                    if (cr.getID() > bs.getBegin().getID()) {
                        cr = bs.getBegin();
                    }
                }
            }
        }

        CriticalBoundary cbd = new CriticalBoundary(cr);
        return cbd;
    }
}
