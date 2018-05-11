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
class GEDegeneracy_ConditionC implements IGEDegeneracyCondition {

    public boolean test(GE geq, IDegeneracyTestLog log) {
        return test1(geq, log) || test2(geq, log);
    }

    private boolean test1(GE geq, IDegeneracyTestLog log) {
        // MK: if two boundary equations...
        boolean answer = false;

        for (Iterator it=geq.iteratorBases(); it.hasNext();) {
            Base bs = (Base)it.next();
            if (bs.isConstant()) continue;

            Base bsDual = bs.getDual();
            boolean pos = bs.getLabel().isPositive();
            boolean posDual = bsDual.getLabel().isPositive();
            int epsiepsi = +1;
            if ((pos && !posDual) || (!pos && posDual)) {
                epsiepsi = -1;
            }

            Boundary b1_prev = null;
            Boundary b1Dual_prev = null;
            Constraint c = bs.getConstraint();
            for (Iterator itcon=c.iteratorBoundary(); itcon.hasNext();) {
                Boundary b1 = (Boundary)itcon.next();
                Boundary b1Dual = c.getDual(b1);

                if (b1_prev==null || b1Dual_prev==null) continue;

                int delta1;
                if (b1.getID() < b1_prev.getID()) {
                    delta1 = -1;
                }
                else if (b1.getID() > b1_prev.getID()) {
                    delta1 = +1;
                }
                else {
                    delta1 = 0;
                }

                int delta2;
                if (b1Dual.getID() < b1Dual_prev.getID()) {
                    delta2 = -1;
                }
                else if (b1Dual.getID() > b1Dual_prev.getID()) {
                    delta2 = +1;
                }
                else {
                    delta2 = 0;
                }

                if (delta1*delta2 != epsiepsi) {
                    String s = "The polarity of "+bs.toStringShort()+" and its dual contradict the order of some of its boundary equations.  ";
                    log.reportEvidence(s);
                    answer = true;
                }

                b1_prev = b1;
                b1Dual_prev = b1Dual;
            }
        }
        return answer;
    }

    private boolean test2(GE geq, IDegeneracyTestLog log) {
        // MK: for a matched pair of bases...
        boolean answer = false;

        for (Iterator it=geq.iteratorBases(); it.hasNext();) {
            Base bs = (Base)it.next();
            if (bs.isConstant()) continue;

            Base bsDual = bs.getDual();

            if (bs.getBegin() == bsDual.getBegin()) {
                Constraint c = bs.getConstraint();
                for (Iterator itcon=c.iteratorBoundary(); itcon.hasNext();) {
                    Boundary b1 = (Boundary)itcon.next();
                    Boundary b1Dual = c.getDual(b1);

                    if (b1.getID() != b1Dual.getID()) {
                        String s = "The "+bs.toStringShort()+" is matched with its dual, yet their boundary equations do not align.  ";
                        log.reportEvidence(s);
                        answer = true;
                    }
                }
            }
        }
        return answer;
    }
}
