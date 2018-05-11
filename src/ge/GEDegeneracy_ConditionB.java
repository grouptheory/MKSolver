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
class GEDegeneracy_ConditionB implements IGEDegeneracyCondition {
    
    public boolean test(GE geq, IDegeneracyTestLog log) {
        // BK: mu and delta(mu) do not properly contain each other
        boolean answer = false;
        for (Iterator it=geq.iteratorBases(); it.hasNext();) {
            Base bs = (Base)it.next();
            if (bs.isConstant()) continue;

            Base bsDual = bs.getDual();
            boolean pos = bs.getLabel().isPositive();
            boolean posDual = bsDual.getLabel().isPositive();

            Boundary begin1 = bs.getBegin();
            Boundary end1 = bs.getEnd();
            Boundary begin2 = bsDual.getBegin();
            Boundary end2 = bsDual.getEnd();
            boolean containsProperly =
                    (begin1.getID() <  begin2.getID() && end1.getID() >= end2.getID()) ||
                    (begin2.getID() <  begin1.getID() && end2.getID() >= end1.getID()) ||
                    (begin1.getID() <= begin2.getID() && end1.getID() >  end2.getID()) ||
                    (begin2.getID() <= begin1.getID() && end2.getID() >  end1.getID());
            if (containsProperly) {
                String s = "The base "+bs.toStringShort()+" and its dual are of the same polarity, yet one properly contains the other.  ";
                log.reportEvidence(s);
                answer = true;
            }
        }
        return answer;
    }
}
