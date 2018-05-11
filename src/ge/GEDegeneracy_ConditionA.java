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

