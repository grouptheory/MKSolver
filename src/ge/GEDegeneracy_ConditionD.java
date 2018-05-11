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
class GEDegeneracy_ConditionD implements IGEDegeneracyCondition {
    
    public boolean test(GE geq, IDegeneracyTestLog log) {
        return test1(geq, log) || test2(geq, log);
    }
    
    private boolean test1(GE geq, IDegeneracyTestLog log) {
        // two distinct constants cannot overlap
        boolean answer = false;

        for (Iterator it=geq.iteratorBases(); it.hasNext();) {
            Base bs = (Base)it.next();
            if ( ! bs.isConstant()) continue;

            for (Iterator it2=geq.iteratorBases(); it2.hasNext();) {
                Base bs2 = (Base)it2.next();
                if ( ! bs2.isConstant()) continue;

                Boundary bd = bs.getBegin();
                Boundary bd2 = bs2.getBegin();
                if (bd != bd2) continue;

                if (bs.getLabel() == bs2.getLabel())
                    continue;

                String s = "Two distinct constants appear at boundary "+bd+".  ";
                log.reportEvidence(s);
                answer = true;
            }
        }
        return answer;
    }


    private boolean test2(GE geq, IDegeneracyTestLog log) {
        // MK: a variable cannot occur in two distinct coefficient equations
        boolean answer = false;

        for (Iterator vit=geq.iteratorBases(); vit.hasNext();) {
            Base vbs = (Base)vit.next();
            if (vbs.isConstant()) continue;
            if (vbs.isEmpty()) continue;

            for (Iterator cit=geq.iteratorBases(); cit.hasNext();) {
                Base cbs = (Base)cit.next();
                if ( ! cbs.isConstant()) continue;

                Boundary vbd = vbs.getBegin();
                Boundary cbd = cbs.getBegin();

                if (vbd == cbd) {

                    Base vbs2 = (Base)vbs.getDual();

                    for (Iterator cit2=geq.iteratorBases(); cit2.hasNext();) {
                        Base cbs2 = (Base)cit2.next();
                        if ( ! cbs2.isConstant()) continue;

                        Boundary vbd2 = vbs2.getBegin();
                        Boundary cbd2 = cbs2.getBegin();
                        Boundary vbd2end = vbs2.getEnd();
                        Boundary cbd2end = cbs2.getEnd();

                        if ((vbd2 == cbd) && (vbd2end == cbd2end)) {
                            boolean pos = vbs.getLabel().isPositive();
                            boolean posDual = vbs2.getLabel().isPositive();
                            if ((pos && !posDual) || (!pos && posDual)) {
                                if (cbs.getLabel() != cbs2.getLabel().getInverse()) {
                                    String s = "The base "+vbs.toStringShort()+" appears equal to two distinct constants.  ";
                                    log.reportEvidence(s);
                                    answer = true;
                                }
                            }
                            else {
                                if (cbs.getLabel() != cbs2.getLabel()) {
                                    String s = "The base "+vbs.toStringShort()+" appears equal to two distinct constants.  ";
                                    log.reportEvidence(s);
                                    answer = true;
                                }
                            }
                        }
                    }

                }
            }
        }
        return answer;
    }
}
