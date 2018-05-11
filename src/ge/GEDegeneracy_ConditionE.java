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
class GEDegeneracy_ConditionE implements IGEDegeneracyCondition {
    
    public boolean test(GE geq, IDegeneracyTestLog log) {
        // MK: if h_i is a variable from some coefficient equation...
        boolean answer = false;

        for (Iterator it2=geq.iteratorBases(); it2.hasNext();) {
            Base cbs = (Base)it2.next();
            if ( ! cbs.isConstant()) continue;

            Boundary cbd1 = cbs.getBegin();
            Boundary cbd2 = cbs.getEnd();

            for (Iterator itA=geq.iteratorBases(); itA.hasNext();) {
                Base bsA = (Base)itA.next();
                if (bsA.isConstant()) continue;
                Constraint consA=bsA.getConstraint();

                Boundary x1 = null;
                Boundary x2 = null;
                for (Iterator iterA=consA.iteratorBoundary(); iterA.hasNext();) {
                    Boundary bA = (Boundary)iterA.next();
                    if (bA.getID() == cbd1.getID()) {
                        if (x1 != null) {
                            throw new RuntimeException("GEDegeneracy_ConditionE.test: duplicate constraint");
                        }
                        x1 = bA;
                    }
                    if (bA.getID() == cbd2.getID()) {
                        if (x2 != null) {
                            throw new RuntimeException("GEDegeneracy_ConditionE.test: duplicate constraint");
                        }
                        x2 = bA;
                    }
                }

                if (x1!=null && x2!=null) {
                    Boundary y1 = consA.getDual(x1);
                    Boundary y2 = consA.getDual(x2);
                    int diff = Math.abs(y1.getID() - y2.getID());
                    if (diff != 1) {
                        String s = "The base "+bsA.toStringShort()+" has constraints with its dual that stretch the constant segment "+x1+" - "+x2+" to length different from 1.  ";
                        log.reportEvidence(s);
                        answer = true;
                    }
                }
            }
        }

        return answer;
    }
}