/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ge;

import java.util.Iterator;

/**
 *
 * @author grouptheory
 */
class GEDegeneracy_ConditionA implements IGEDegeneracyCondition {
    
    public boolean test(GE geq, IDegeneracyTestLog log) {
        // MK: if e(mu)=-e(delta(mu) then mu and delta(mu) cannot intersect
        boolean answer = false;
        
        for (Iterator it=geq.iteratorBases(); it.hasNext();) {
            Base bs = (Base)it.next();
            if (bs.isConstant()) continue;

            Base bsDual = bs.getDual();
            boolean pos = bs.getLabel().isPositive();
            boolean posDual = bsDual.getLabel().isPositive();
            if ((pos && !posDual) || (!pos && posDual)) {
                Boundary begin1 = bs.getBegin();
                Boundary end1 = bs.getEnd();
                Boundary begin2 = bsDual.getBegin();
                Boundary end2 = bsDual.getEnd();
                boolean intersects =
                        ((begin1.getID() >= begin2.getID() && begin1.getID() < end2.getID()) ||
                        (end1.getID() > begin2.getID() && end1.getID() <= end2.getID()) ||
                        (begin1.getID() <= begin2.getID() && end1.getID() >= end2.getID()) ||
                        (begin2.getID() <= begin1.getID() && end2.getID() >= end1.getID()));
                if (intersects) {
                    String s = "The base "+bs.toStringShort()+" and its dual are of opposite polarity, yet intersect.  ";
                    log.reportEvidence(s);
                    answer = true;
                }
            }
        }
        return answer;
    }
}

