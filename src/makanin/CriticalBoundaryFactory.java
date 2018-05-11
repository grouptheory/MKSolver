/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
