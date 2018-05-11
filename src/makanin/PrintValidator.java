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

import ge.GE;
import ge.Base;
import ge.Boundary;
import ge.Constraint;
import letter.Letter;
import letter.Variable;
import java.util.Iterator;
import utility.ConsoleLogger;

/**
 *
 * @author grouptheory
 */
public class PrintValidator {

    static boolean validate(Print p, GE geq) {
        //System.out.println("PrintValidator");
        
        if ( ! canonicalEqualityOrdering(p,geq)) return false;
        if ( ! carrierExactlyOverlapsDual(p,geq)) return false;
        if ( ! carrierConstraintsOverlapsDualConstraints(p,geq)) return false;
        if ( ! nonCollapsing(p,geq)) return false;
        if ( ! consistentTransport(p,geq)) return false;
        if ( ! consistentConstants(p,geq)) return false;

        return true;
    }

    private static boolean consistentConstants(Print p, GE geq) {
        // constant bases move consistently
        boolean answer = true;

        Boundary lp=p.getBegin();
        Boundary rp=p.getEnd();

        for (Iterator it=geq.iteratorBases(); it.hasNext();) {
            Base bs = (Base)it.next();
            if ( ! bs.getLabel().isConstant()) continue;

            // all constant bases
            Boundary left=bs.getBegin();
            Boundary right=bs.getEnd();

            if (left.getID()+1 != right.getID()) {
                throw new RuntimeException("PrintValidator.consistentConstants: constant length > 1");
            }

            if (left.getID()<=lp.getID() && right.getID()<=lp.getID()) {
                // entirely to the left
            }
            else if (left.getID()>=rp.getID() && right.getID()>=rp.getID()) {
                // entirely to right
            }
            else if (left.getID()>=lp.getID() && right.getID()<=rp.getID()) {
                // inside
                int distance = p.compare(left, PrintNode.DUAL, right, PrintNode.DUAL);

                boolean currentanswer = true;

                // absolute value
                if (Math.abs(distance) != 1) {
                    currentanswer = false;
                }

                if (!currentanswer) {
                    String s = "";
                    s+=("consistentConstants rejects print "+p+"");
                    s+=("of ge "+geq+"");
                    s+=("because a constant over the dual "+bs+" SPLITS, dist="+distance);
                    ConsoleLogger.instance().debug("PrintValidator", s);
                }
                
                answer = answer && currentanswer;
            }
            else {
                throw new RuntimeException("PrintValidator.consistentConstants: unexpected constant");
            }


            if ((p.contains(left, PrintNode.CARRIER_TR) &&
                 p.contains(right, PrintNode.CARRIER_TR))) {

                int distance2 = p.compare(left, PrintNode.CARRIER_TR, right, PrintNode.CARRIER_TR);

                boolean currentanswer = true;
                if (distance2 != -1) {
                    currentanswer = false;
                }

                if (!currentanswer) {
                    String s = "";
                    s+=("consistentConstants rejects print "+p+"");
                    s+=("of ge "+geq+"");
                    s+=("because a constant over the carrier "+bs+" STRETCHES");
                    ConsoleLogger.instance().debug("PrintValidator", s);
                }
                
                answer = answer && currentanswer;
            }
        }

        if (!answer) ConsoleLogger.instance().info("PrintValidator", "consistentConstants returns false for print "+p);
        return answer;
    }

    private static boolean consistentTransport(Print p, GE geq) {
        // transport bases move consistently
        boolean answer = true;
        for (Iterator it=geq.iteratorBases(); it.hasNext();) {
            Base bs = (Base)it.next();
            if (bs.getLabel().isConstant()) continue;

            Base dual = bs.getDual();

            BaseClassDecorator bcd = (BaseClassDecorator)bs.lookupDecorator(BaseClassDecorator.NAME);
            BaseClassDecorator bcdDual = (BaseClassDecorator)dual.lookupDecorator(BaseClassDecorator.NAME);

            if ((bcd instanceof BaseClassTransport) &&
                (bcdDual instanceof BaseClassFixed)) {

                // System.out.println("consistentTransport on "+bs+" whose dual is "+dual);
                
                Constraint con = bs.getConstraint();
                Constraint con_dual = dual.getConstraint();

                boolean aligned = false;
                for (Iterator it2 = con.iteratorBoundary(); it2.hasNext();) {
                    Boundary bd = (Boundary)it2.next();
                    Boundary bd_dual = con.getDual(bd);

                    if (p.contains(bd_dual, PrintNode.DUAL)) {
                        int b1 = p.compare(bd, PrintNode.CARRIER_TR,
                                           bd_dual, PrintNode.DUAL);

                        if (b1==0) {
                            aligned = true;
                            break;
                        }
                    }
                }

                if (aligned) {
                    boolean strict = true;
                    boolean currentanswer = baseOverlapsDualConstraints(p, geq, bs, !strict);
                    /*
                    if (answer && !currentanswer) {
                        System.out.println("consistentTransport rejects a print "+p+"");
                        System.out.println("of ge "+geq+"");
                        System.out.println("because of "+bs+" whose dual is "+dual);
                    }
                     */
                    answer = answer && currentanswer;
                }

            }
        }
        return answer;
    }

    private static boolean nonCollapsing(Print p, GE geq) {
        // distinct boundaries go to distinct boundaries
        boolean answer = true;

        // System.out.println("non collapsing: "+p);

        Carrier ca = (Carrier)geq.lookupDecorator(Carrier.NAME);
        if (ca == null) {
            throw new RuntimeException("PrintValidator.nonCollapsing: unknown carrier");
        }
        Base carrier_base = ca.getBase();
        if (carrier_base == null) {
            throw new RuntimeException("PrintValidator.nonCollapsing: carrier_base is null");
        }

        Base carrier_dual = carrier_base.getDual();

        int targetValue = -1;
        
        int low = carrier_base.getBegin().getID();
        int high = carrier_base.getEnd().getID();
        for (int i=low;i<high;i++) {
            Boundary smaller=geq.getNthBoundary(i);
            Boundary larger=geq.getNthBoundary(i+1);
            int b1 = p.compare(smaller, PrintNode.CARRIER_TR,
                               larger, PrintNode.CARRIER_TR);

            // System.out.println("Comparing "+smaller+":CARRIER with "+larger+":CARRIER");

            if (b1 * targetValue <= 0) answer = false;
        }

        Variable v = (Variable)carrier_base.getLabel();
        Variable vdual = (Variable)carrier_dual.getLabel();

        boolean flipDualBoundaries = false;
        if ((v.isPositive() && !vdual.isPositive()) ||
            (!v.isPositive() && vdual.isPositive())) {
            flipDualBoundaries = true;
        }

        if (flipDualBoundaries) {
            targetValue *= -1;
        }
        
        int lowdual = carrier_dual.getBegin().getID();
        int highdual = carrier_dual.getEnd().getID();
        for (int i=lowdual;i<highdual;i++) {
            Boundary smaller=geq.getNthBoundary(i);
            Boundary larger=geq.getNthBoundary(i+1);
            int b2 = p.compare(smaller, PrintNode.DUAL,
                               larger, PrintNode.DUAL);
            
            // System.out.println("Comparing "+smaller+":DUAL with "+larger+":DUAL");
            
            if (b2 * targetValue <= 0) answer = false;
        }

        return answer;
    }

    private static boolean carrierConstraintsOverlapsDualConstraints(Print p, GE geq) {
        // the first and last
        boolean answer = true;

        Carrier ca = (Carrier)geq.lookupDecorator(Carrier.NAME);
        if (ca == null) {
            throw new RuntimeException("PrintValidator.carrierConstraintsOverlapsDualConstraints: unknown carrier");
        }
        Base carrier_base = ca.getBase();
        if (carrier_base == null) {
            throw new RuntimeException("PrintValidator.carrierConstraintsOverlapsDualConstraints: carrier_base is null");
        }

        boolean strict = true;
        return baseOverlapsDualConstraints(p, geq, carrier_base, strict);
    }


    private static boolean baseOverlapsDualConstraints(Print p, GE geq, Base base, boolean strict) {
        // the first and last
        boolean answer = true;

        Base dual = base.getDual();

        Variable v = (Variable)base.getLabel();
        Variable vdual = (Variable)dual.getLabel();

        boolean flipDualBoundaries = false;
        if ((v.isPositive() && !vdual.isPositive()) ||
            (!v.isPositive() && vdual.isPositive())) {
            flipDualBoundaries = true;
        }

        Constraint con_carrier = base.getConstraint();
        Constraint con_dual = dual.getConstraint();

        int a_carrier = 0;
        boolean dual_found1 = false;
        for (Iterator it = con_carrier.iteratorBoundary(); it.hasNext();) {
            Boundary bd = (Boundary)it.next();
            Boundary bd_dual = con_carrier.getDual(bd);

            if (p.contains(bd_dual, PrintNode.DUAL)) {
                dual_found1 = true;
                int b1 = p.compare(bd, PrintNode.CARRIER_TR,
                                   bd_dual, PrintNode.DUAL);

                if (b1!=0) a_carrier=b1;
            }
            else {
                if (strict) {
                    throw new RuntimeException("PrintValidator.carrierConstraintsOverlapsDualConstraints: inconsistent strict overlap check 1 fails");
                }
            }
        }

        int a_dual = 0;
        boolean dual_found2 = false;
        for (Iterator it = con_dual.iteratorBoundary(); it.hasNext();) {
            Boundary bd_dual = (Boundary)it.next();
            Boundary bd = con_dual.getDual(bd_dual);

            if (p.contains(bd_dual, PrintNode.DUAL)) {
                dual_found2 = true;

                int b2 = p.compare(bd, PrintNode.CARRIER_TR,
                                   bd_dual, PrintNode.DUAL);

                if (b2!=0) {
                    a_dual=b2;
                }
            }
            else {
                if (strict) {
                    throw new RuntimeException("PrintValidator.carrierConstraintsOverlapsDualConstraints: inconsistent strict overlap check 2 fails");
                }
            }
        }

        if ((a_carrier==0 && a_dual!=0) ||
            (a_carrier!=0 && a_dual==0)) {
            throw new RuntimeException("PrintValidator.carrierConstraintsOverlapsDualConstraints: inconsistent constraints between carrier/dual");  
        }
        
        return (a_carrier==0 && (dual_found2 && a_dual==0));
    }

    private static boolean carrierExactlyOverlapsDual(Print p, GE geq) {
        // the first and last boundaries of the carrier and the dual agree
        boolean answer = true;

        Carrier ca = (Carrier)geq.lookupDecorator(Carrier.NAME);
        if (ca == null) {
            throw new RuntimeException("PrintValidator.carrierExactlyOverlapsDual: unknown carrier");
        }
        Base carrier_base = ca.getBase();
        if (carrier_base == null) {
            throw new RuntimeException("PrintValidator.carrierExactlyOverlapsDual: carrier_base is null");
        }

        Base carrier_dual = carrier_base.getDual();

        Variable v = (Variable)carrier_base.getLabel();
        Variable vdual = (Variable)carrier_dual.getLabel();

        boolean flipDualBoundaries = false;
        if ((v.isPositive() && !vdual.isPositive()) ||
            (!v.isPositive() && vdual.isPositive())) {
            flipDualBoundaries = true;
        }
        
        int b1, b2;
        if (!flipDualBoundaries) {
            b1 = p.compare(carrier_base.getBegin(), PrintNode.CARRIER_TR,
                           carrier_dual.getBegin(), PrintNode.DUAL);
            b2 = p.compare(carrier_base.getEnd(), PrintNode.CARRIER_TR,
                           carrier_dual.getEnd(), PrintNode.DUAL);
            //System.out.println("Comparing "+carrier_base.getBegin()+" & "+carrier_dual.getBegin()+" => "+b1);
            //System.out.println("Comparing "+carrier_base.getEnd()+" & "+carrier_dual.getEnd()+" => "+b2);
        }
        else {
            b1 = p.compare(carrier_base.getBegin(), PrintNode.CARRIER_TR,
                           carrier_dual.getEnd(), PrintNode.DUAL);
            b2 = p.compare(carrier_base.getEnd(), PrintNode.CARRIER_TR,
                           carrier_dual.getBegin(), PrintNode.DUAL);
            //System.out.println("Comparing "+carrier_base.getBegin()+" & "+carrier_dual.getEnd()+" => "+b1);
            //System.out.println("Comparing "+carrier_base.getEnd()+" & "+carrier_dual.getBegin()+" => "+b2);

        }
        answer = ((b1==0) && (b2==0));

        return answer;
    }

    private static boolean canonicalEqualityOrdering(Print p, GE geq) {
        // all equivalence classes of boundaries in the Print
        // should be ordered by listing CARRIER boundaries
        // before DUAL boundaries
        boolean answer = true;
        
        PrintNode pn, pnlast;
        pn = pnlast = null;
        for (Iterator it=p.iteratorPrintNodes(true); it.hasNext();) {
            pnlast = pn;
            pn = (PrintNode)it.next();
            if ((pnlast!=null) &&
                (pnlast.getSource()==PrintNode.DUAL) &&
                (pn.getSource()==PrintNode.CARRIER_TR) &&
                (pn.getOffset()==PrintNode.ZERO)) {
                answer = false;
                break;
            }
        }
        return answer;
    }
}
