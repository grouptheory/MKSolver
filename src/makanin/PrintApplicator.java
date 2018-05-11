/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package makanin;

import ge.GE;
import ge.Constraint;
import ge.Boundary;
import ge.Base;
import ge.IGETask;
import ge.GETask_ConstraintDeletion;
import ge.GETask_ConstraintAddition;
import ge.GETask_BoundaryInsertion;
import ge.GETask_MoveBase;
import ge.GETask_CollapseBase;
import ge.GETask_DeleteBase;
import ge.GETask_DeleteBoundary;
import ge.BaseComparator;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;

/**
 *
 * @author grouptheory
 */
public class PrintApplicator {

    private TreeMap _old2new_bdmap;
    private HashMap _old2new_bsmap;
    private GE _geqOriginal;
    private GE _geqPrinted;
    private TreeMap _crtr2new_bdmap;

    private TreeMap _new2old_bdmap;
    private HashMap _new2old_bsmap;

    private LinkedList _pendingTasks;
    private LinkedList _completedTasks;

    private void drainTaskQueue() {
        for (Iterator it = _pendingTasks.iterator(); it.hasNext();) {
            IGETask task = (IGETask)it.next();
            try {
                _geqPrinted.validate();
                task.execute();
                _geqPrinted.validate();
            }
            catch (RuntimeException ex) {
                System.out.println("EXCEPTION "+task);
                throw ex;
            }
            
            _completedTasks.addLast(task);
        }
        _pendingTasks.clear();
    }
    private void enqueueTask(IGETask task) {
        _pendingTasks.addLast(task);
    }

    
    PrintApplicator(GE geq, Print p) {
        _old2new_bdmap = new TreeMap();
        _old2new_bsmap = new HashMap();
        
        _geqOriginal = geq;
        _geqOriginal.validate();
        
        _geqPrinted = geq.duplicate(_old2new_bdmap, _old2new_bsmap);
        
        _new2old_bdmap = invertOld2NewBoundaryMap();
        _new2old_bsmap = invertOld2NewBaseMap();
        
        _geqPrinted.validate();
        
        _crtr2new_bdmap = new TreeMap();
        
        _pendingTasks = new LinkedList();
        _completedTasks = new LinkedList();
        
        apply(p);
    }

    GE getPrinted() {
        return _geqPrinted;
    }
    
    private void apply(Print p) {

        _geqPrinted.validate();
        // System.out.println("XYZ XXXXXXXXXX");

        insertNewBoundaries(p);
        _geqPrinted.validate();

        LinkedList additionalTasks = null;
        if (isCriticalBoundaryStrictlyBelowRightBoundary()) {
            // System.out.println("ABC FHGFHGFHGFHGFHFHF");
            reduceConstraintsOnCarrier();
        }
        else {
            Base oldCarrier = getOldCarrier();
            additionalTasks = moveBaseToDualPart1(p, oldCarrier, true);
            moveBaseToDualPart2(additionalTasks);
        }
        _geqPrinted.validate();

        moveTransportBases(p);
        _geqPrinted.validate();

        collapseAlignedVariableBases(p);
        _geqPrinted.validate();

        eliminateBasesStartingLeftOfCriticalBoundary(p);
        _geqPrinted.validate();

        eliminateUselessBoundaries(p);
        _geqPrinted.validate();
    }

    private void eliminateUselessBoundaries(Print p) {

        for (Iterator it = _geqPrinted.iteratorBoundaries(); it.hasNext();) {
            Boundary bdNew = (Boundary)it.next();
            if (_geqPrinted.isUseless(bdNew)) {
                enqueueTask(new GETask_DeleteBoundary(bdNew, _geqPrinted));
            }
            
        }
        drainTaskQueue();
    }

    private void eliminateBasesStartingLeftOfCriticalBoundary(Print p) {
        Boundary crOld = getOldCriticalBoundary();
        Boundary crNew = Old2NewBoundary(crOld);
        Base carrierOld = getOldCarrier();
        Base carrierNew = Old2NewBase(carrierOld);
        Base carrierDualOld = carrierOld.getDual();
        Base carrierDualNew = carrierNew.getDual();

        for (Iterator it = _geqOriginal.iteratorBases(); it.hasNext();) {
            Base baseOld = (Base)it.next();
            Base baseNew = Old2NewBase(baseOld);
            
            if (baseNew.getBegin().getID() < crNew.getID()) {

                if ( ! baseOld.getLabel().isConstant()) {
                    // System.out.println("Original:"+_geqOriginal);
                    // System.out.println("Print:"+p);
                    // System.out.println("New:"+_geqPrinted);
                    // System.out.println("carrierOld:"+carrierOld);
                    // System.out.println("carrierDualOld:"+carrierDualOld);
                    // System.out.println("baseOld:"+baseOld);
                    // System.out.println("crOld:"+crOld);

                    // System.out.println("carrierNew:"+carrierNew);
                    // System.out.println("carrierDualNew:"+carrierDualNew);
                    // System.out.println("baseNew:"+baseNew);
                    // System.out.println("crNew:"+crNew);

                    throw new RuntimeException("PrintApplicator.eliminateBasesStartingLeftOfCriticalBoundary eliminating a variable base "+baseNew);
                }

                enqueueTask(new GETask_DeleteBase(baseNew, _geqPrinted));
            }
        }
        drainTaskQueue();
    }

    private void collapseAlignedVariableBases(Print p) {
        BaseComparator bcomp = new BaseComparator();
        
        for (Iterator it = _geqPrinted.iteratorBases(); it.hasNext();) {
            Base baseNew = (Base)it.next();
            if (baseNew.getLabel().isConstant()) continue;
            
            Base dualNew = baseNew.getDual();

            if (bcomp.compare(baseNew, dualNew) < 0) continue;

            if (!dualNew.isEmpty() &&
                ((baseNew.getBegin() == dualNew.getBegin()) &&
                 (baseNew.getEnd() == dualNew.getEnd()) &&
                 (baseNew.getLabel() == dualNew.getLabel()))) {
                enqueueTask(new GETask_CollapseBase(baseNew, _geqPrinted));
            }
        }
        drainTaskQueue();
    }

    private void moveTransportBases(Print p) {
        for (Iterator it = _geqOriginal.iteratorBases(); it.hasNext();) {
            Base transportOld = (Base)it.next();
            if (transportOld == getOldCarrier()) continue;

            if (transportOld.lookupDecorator(makanin.BaseClassDecorator.NAME) instanceof makanin.BaseClassTransport) {
                addConstraintsToCarrier(transportOld);

                Base transportNew = Old2NewBase(transportOld);
                // System.out.println("XYZ Moving transport base "+transportNew);

                LinkedList additionalTasks = moveBaseToDualPart1(p, transportOld, false);
                moveBaseToDualPart2(additionalTasks);
            }
        }
        drainTaskQueue();
    }

    private void insertNewBoundaries(Print p) {

        boolean flipped = isCarrierFlipped();

        Boundary startOld = p.getBegin();
        Boundary startNew = Old2NewBoundary(startOld);

        // System.out.println("XYZ*** p.getBegin(): "+p.getBegin());
        // System.out.println("XYZ*** p.getEnd(): "+p.getEnd());
        // System.out.println("XYZ*** startNew: "+startNew);

        for (Iterator it=p.iteratorPrintNodes(!flipped); it.hasNext();) {
            PrintNode pn = (PrintNode)it.next();
            // System.out.println("XYZ PrintNode: "+pn);
        }
        
        for (Iterator it=p.iteratorPrintNodes(!flipped); it.hasNext();) {
            PrintNode pn = (PrintNode)it.next();

            if (pn.getSource() == PrintNode.CARRIER_TR) {
                Boundary equiv = getEquivalentExistingBoundaryNew(p, pn);
                if (equiv == null) {
                    boolean after = true;

                    // System.out.println("add after: "+startNew);

                    GETask_BoundaryInsertion task = new GETask_BoundaryInsertion(startNew, after, _geqPrinted);
                    enqueueTask(task);
                    drainTaskQueue();
                    startNew = task.getNewBoundary();

                    // System.out.println("XYZ pn "+pn+" ++> "+startNew);
                    _crtr2new_bdmap.put(pn.getBoundary(), startNew);
                }
                else {
                    // System.out.println("XYZ pn "+pn+" ==> "+equiv);
                    _crtr2new_bdmap.put(pn.getBoundary(), equiv);
                }
            }
            else {
                startNew = Old2NewBoundary(pn.getBoundary());
                // System.out.println("pushed startNew: "+startNew);
            }
        }
    }
    
    private boolean isEquivalentToExistingBoundary(Print p, PrintNode pntest) {
        return (getEquivalentExistingBoundaryNew(p, pntest) != null);
    }

    private Boundary getEquivalentExistingBoundaryNew(Print p, PrintNode pntest) {
        Boundary beforeBd=isEquivalentToExistingBoundaryNewBefore(p,pntest);
        boolean before = (beforeBd!=null);

        Boundary afterBd=isEquivalentToExistingBoundaryNewAfter(p,pntest);
        boolean after = (afterBd!=null);
        if (before && after && (beforeBd != afterBd)) {
            // System.out.println("XYZ testing for equivs to "+pntest);
            // System.out.println("XYZ equiv to "+afterBd);
            // System.out.println("XYZ equiv to "+beforeBd);
            throw new RuntimeException("PrintApplicator.isEquivalentToExistingBoundary finds something both before and after");
        }
        if (before) {
            return beforeBd;
        }
        if (after) {
            return afterBd;
        }
        return null;
    }
    
    private Boundary isEquivalentToExistingBoundaryNewBefore(Print p, PrintNode pntest) {
        PrintNode pn = pntest;
        do {
            if (pn!=null) {
                if (pn.getOffset() == PrintNode.NONZERO) {
                    return null;
                }

                if (pn.getSource() == PrintNode.DUAL) {
                    return Old2NewBoundary(pn.getBoundary());
                }
                else if (pn.getSource() == PrintNode.CARRIER_TR) {
                    Boundary inserted = CarrierTranspose2NewBoundary(pn.getBoundary());
                    if (inserted!=null) {
                        return inserted;
                    }
                }
            }
            pn = p.prevPrintNode(pn);
        }
        while (pn!=null);
        return null;
    }

    private Boundary isEquivalentToExistingBoundaryNewAfter(Print p, PrintNode pntest) {
        PrintNode pn = pntest;
        if (pn.getSource() == PrintNode.DUAL) {
            return Old2NewBoundary(pn.getBoundary());
        }
        else if (pn.getSource() == PrintNode.CARRIER_TR) {
            Boundary inserted = CarrierTranspose2NewBoundary(pn.getBoundary());
            if (inserted!=null) {
                return inserted;
            }
        }
        
        do {
            pn = p.nextPrintNode(pn);
            if (pn!=null) {
                if (pn.getOffset() == PrintNode.NONZERO) {
                    return null;
                }
                if (pn.getSource() == PrintNode.DUAL) {
                    return Old2NewBoundary(pn.getBoundary());
                }
                else if (pn.getSource() == PrintNode.CARRIER_TR) {
                    Boundary inserted = CarrierTranspose2NewBoundary(pn.getBoundary());
                    if (inserted!=null) {
                        return inserted;
                    }
                }
            }
        }
        while (pn!=null);
        return null;
    }

    private boolean isCriticalBoundaryStrictlyBelowRightBoundary() {
        boolean answer = true;
        Base carrier = getOldCarrier();
        Boundary cr = getOldCriticalBoundary();
        if (cr.getID() < carrier.getEnd().getID()) {
            answer = true;
        }
        else {
            answer = false;
        }
        return answer;
    }

    private boolean isCarrierFlipped() {

        Base carrierOld = getOldCarrier();
        Base carrierNew = Old2NewBase(carrierOld);
        Base carrierDualNew = carrierNew.getDual();

        if (( carrierNew.getLabel().isPositive() && !carrierDualNew.getLabel().isPositive()) ||
            (!carrierNew.getLabel().isPositive() &&  carrierDualNew.getLabel().isPositive())) {
            return true;
        }
        else return false;
    }
    
    private LinkedList moveBaseToDualPart1(Print p, Base baseOld, boolean isCarrier) {
        Base baseNew = Old2NewBase(baseOld);
        
        LinkedList additionalTasks = new LinkedList();

        if (baseOld.getLabel().isConstant()) {

        }
        else {
            Base dualOld = baseOld.getDual();
            Base dualNew = Old2NewBase(dualOld);

            Constraint consOld = baseOld.getConstraint();
            Constraint consdualOld = baseOld.getConstraint();

            Constraint consNew = baseNew.getConstraint();
            Constraint consdualNew = dualNew.getConstraint();


            Iterator itOrig=consOld.iteratorBoundary();
            for (Iterator it=consNew.iteratorBoundary(); it.hasNext();) {
                Boundary bdNew = (Boundary)it.next();
                Boundary bdDualNew = consNew.getDual(bdNew);

                if (bdNew==baseNew.getBegin()) continue;
                if (bdNew==baseNew.getEnd()) continue;
                
                if (!isCarrier) {
                    // System.out.println("XYZ GETask_ConstraintDeletion base "+baseNew.getLabel()+"@"+bdNew+" XXX "+dualNew.getLabel()+"@"+bdDualNew);
                }
                enqueueTask(new GETask_ConstraintDeletion(baseNew, bdNew, bdDualNew, _geqPrinted));

                Boundary bdOrig =(Boundary)itOrig.next();

                Boundary bdOrig_tr_New = CarrierTranspose2NewBoundary(bdOrig);

                /*
                PrintNode pOrig = p.get(bdOrig, PrintNode.CARRIER_TR);
                if (pOrig == null) {
                    throw new RuntimeException("PrintApplicator.moveBaseToDualPart1: pOrig is null");
                }

                bdOrig_tr_New = getEquivalentExistingBoundaryNew(p, pOrig);
                 */

                if (bdOrig_tr_New == null) {
                    // System.out.println("XYZ searching for = "+bdOrig);
                    // System.out.println("XYZ print = "+p);
                    throw new RuntimeException("PrintApplicator.moveBaseToDualPart1: bdOrig_tr is null");
                }

                if (!isCarrier) {
                    // System.out.println("XYZ GETask_ConstraintAddition base "+baseNew.getLabel()+"@"+bdOrig_tr_New+" === "+dualNew.getLabel()+"@"+bdDualNew);
                }

                additionalTasks.addLast(new GETask_ConstraintAddition(baseNew, bdOrig_tr_New, bdDualNew, _geqPrinted));
            }
        }
        
        drainTaskQueue();
        
        Boundary begin = CarrierTranspose2NewBoundary(baseOld.getBegin()); // dualNew.getBegin();
        Boundary end = CarrierTranspose2NewBoundary(baseOld.getEnd()); // dualNew.getEnd();
        /*
        if (isCarrierFlipped()) {
            Boundary swap=begin;
            begin=end;
            end=swap;
        }
         */
        additionalTasks.addFirst(new GETask_MoveBase(baseNew, begin, end, _geqPrinted));

        return additionalTasks;
    }

    private void moveBaseToDualPart2(LinkedList additionalTasks) {
        for (Iterator it=additionalTasks.iterator(); it.hasNext();) {
            IGETask task = (IGETask)it.next();
            enqueueTask(task);
        }
        drainTaskQueue();
    }
    
    private void reduceConstraintsOnCarrier() {
        Base carrierOld = getOldCarrier();
        Boundary crOld = getOldCriticalBoundary();
        Base carrier = Old2NewBase(carrierOld);
        Boundary cr = Old2NewBoundary(crOld);
        Boundary cr_tr = CarrierTranspose2NewBoundary(crOld);
        
        // System.out.println("Carrier is "+carrier);
        
        Constraint cons = carrier.getConstraint();
        for (Iterator it=cons.iteratorBoundary(); it.hasNext();) {
            Boundary bdi=(Boundary)it.next();
            if (bdi.getID() <  cr.getID()) {
                Boundary bdi_dual = cons.getDual(bdi);
                enqueueTask(new GETask_ConstraintDeletion(carrier, bdi, bdi_dual, _geqPrinted,
                        cr, cr_tr));
            }
        }

        drainTaskQueue();
    }

    private void addConstraintsToCarrier(Base transportOld) {
       Base carrierOld = getOldCarrier();
       Boundary crOld = getOldCriticalBoundary();

       Base carrierNew = Old2NewBase(carrierOld);
       Boundary crNew = Old2NewBoundary(crOld);
       Base transportNew = Old2NewBase(transportOld);

       // System.out.println("Carrier is "+carrier);

       Constraint consCarrier = carrierNew.getConstraint();
       
       if (transportNew.getLabel().isConstant()) {
           Boundary leftOld = transportOld.getBegin();
           if (crOld.getID() < leftOld.getID()) {
               Boundary leftNew = Old2NewBoundary(leftOld);
               Boundary left_trNew = CarrierTranspose2NewBoundary(leftOld);
               enqueueTask(new GETask_ConstraintAddition(carrierNew, leftNew, left_trNew, _geqPrinted));
           }

           Boundary rightOld = transportOld.getEnd();
           if (crOld.getID() < rightOld.getID()) {
               Boundary rightNew = Old2NewBoundary(rightOld);
               Boundary right_trNew = CarrierTranspose2NewBoundary(rightOld);
               enqueueTask(new GETask_ConstraintAddition(carrierNew, rightNew, right_trNew, _geqPrinted));
           }
       }
       else {
           Constraint consOld = transportOld.getConstraint();

           for (Iterator it=consOld.iteratorBoundary(); it.hasNext();) {
              Boundary bdtransOld=(Boundary)it.next();
              if (crOld.getID() < bdtransOld.getID()) {
                  Boundary bdtransNew = Old2NewBoundary(bdtransOld);
                  Boundary bdtrans_trNew = CarrierTranspose2NewBoundary(bdtransOld);
                  enqueueTask(new GETask_ConstraintAddition(carrierNew, bdtransNew, bdtrans_trNew, _geqPrinted));
              }
           }
        }
    }

    private TreeMap invertOld2NewBoundaryMap() {
        TreeMap new2old_bdmap = new TreeMap();
        new2old_bdmap.clear();
        for (Iterator it=_old2new_bdmap.entrySet().iterator(); it.hasNext();) {
            Map.Entry ent = (Map.Entry)it.next();
            new2old_bdmap.put(ent.getValue(), ent.getKey());
        }
        return new2old_bdmap;
    }

    private HashMap invertOld2NewBaseMap() {
        HashMap new2old_bsmap = new HashMap();
        new2old_bsmap.clear();
        for (Iterator it=_old2new_bsmap.entrySet().iterator(); it.hasNext();) {
            Map.Entry ent = (Map.Entry)it.next();
            new2old_bsmap.put(ent.getValue(), ent.getKey());
        }
        return new2old_bsmap;
    }

    private Boundary CarrierTranspose2NewBoundary(Boundary bd) {
        Boundary bdnew = (Boundary)_crtr2new_bdmap.get(bd);
        return bdnew;
    }
    
    private Boundary Old2NewBoundary(Boundary bd) {
        Boundary bdnew = (Boundary)_old2new_bdmap.get(bd);
        if (bdnew==null) {
            throw new RuntimeException("PrintApplicator.Old2NewBoundary unknown boundary");
        }
        return bdnew;
    }
    
    private Base Old2NewBase(Base bs) {
        Base bsnew = (Base)_old2new_bsmap.get(bs);
        if (bsnew==null) {
            throw new RuntimeException("PrintApplicator.Old2NewBase unknown base");
        }
        return bsnew;
    }

    Boundary New2OldBoundary(Boundary bd) {
        Boundary bdold = (Boundary)_new2old_bdmap.get(bd);
        if (bdold==null) {
            throw new RuntimeException("PrintApplicator.New2OldBoundary unknown boundary");
        }
        return bdold;
    }

    Base New2OldBase(Base bs) {
        Base bsold = (Base)_new2old_bsmap.get(bs);
        if (bsold==null) {
            throw new RuntimeException("PrintApplicator.New2OldBase unknown base");
        }
        return bsold;
    }

    private Base getOldCarrier() {
        Carrier ca = (Carrier)_geqOriginal.lookupDecorator(Carrier.NAME);
        if (ca == null) {
            throw new RuntimeException("PrintApplicator.Main: unknown carrier");
        }
        Base carrier_base = ca.getBase();
        if (carrier_base == null) {
            throw new RuntimeException("PrintApplicator.Main: carrier_base is null");
        }
        return carrier_base;
    }

    private Boundary getOldCriticalBoundary() {
        Base carrier_base = getOldCarrier();
        CriticalBoundary cr = (CriticalBoundary)_geqOriginal.lookupDecorator(CriticalBoundary.NAME);
        if (cr == null) {
            throw new RuntimeException("PrintApplicator.Main: unknown critical boundary");
        }
        Boundary critical_boundary = cr.getBoundary();
        if (critical_boundary == null) {
            throw new RuntimeException("PrintApplicator.Main: critical_boundary is null");
        }
        return critical_boundary;
    }

    private Base getOldCarrierDual() {
        Base carrier_base = getOldCarrier();
        Base carrier_dual = carrier_base.getDual();
        return carrier_dual;
    }


    public String toString() {
        String s="";
        int i=1;
        for (Iterator it = _completedTasks.iterator(); it.hasNext();) {
            IGETask task = (IGETask)it.next();
            s+="{\\underline{Step "+i+"}:} ";
            s+=task.toString();
            if (it.hasNext()) {
                s+="\\\\\n";
            }
            else {
                s+="\\\\"+params.MKParams.REPORT_SPACING+"\n";
            }
            i++;
        }
        return s;
    }
}
