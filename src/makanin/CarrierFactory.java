/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
