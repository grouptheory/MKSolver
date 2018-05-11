/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ge;

import equation.QuadraticSystem;
import cancellation.Diagram;
import cancellation.LabeledPath;
import cancellation.Path;
import cancellation.Edge;
import letter.LetterFactory;
import letter.Letter;
import letter.Constant;
import letter.Variable;
import java.util.Iterator;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author grouptheory
 */
public class GEFactory {

    private static GEFactory _instance;

    private GEFactory() {
    }

    public static GEFactory instance() {
        if (_instance == null) {
            _instance = new GEFactory();
        }
        return _instance;
    }

    public GE newGE(Diagram d, QuadraticSystem qs) {

        GE ge = new GE();
        Boundary last=ge.getFirstBoundary();

        int counter=0;
        HashMap lp2begin = new HashMap();
        HashMap lp2end = new HashMap();
        
        for (Iterator it = d.iteratorLabeledPaths(); it.hasNext();) {
           
            LabeledPath lp = (LabeledPath)it.next();

            int beginIndex=counter;
            lp2begin.put(lp,new Integer(beginIndex));

            Path p = lp.getPath();
            int len = p.length();
            for (int i=0; i<len;i++) {
                ge.appendNewBoundary();
            }

            counter +=len;
            
            int endIndex=beginIndex+len;
            lp2end.put(lp,new Integer(endIndex));
        }

        HashSet vars = new HashSet();

        for (Iterator it = d.iteratorLabeledPaths(); it.hasNext();) {
            LabeledPath lp = (LabeledPath)it.next();
            Letter let = lp.getLabel();
            Path p = lp.getPath();
            int len = p.length();

            int beginIndex=((Integer)lp2begin.get(lp)).intValue();
            int endIndex=((Integer)lp2end.get(lp)).intValue();
            Boundary begin = ge.getNthBoundary(beginIndex);
            Boundary end = ge.getNthBoundary(endIndex);
            
            if (let.isConstant()) {
                if (endIndex-beginIndex != 1) {
                    throw new RuntimeException("GEFactory.newGE: diagram contains constant base w/ length != 1");
                }
                ge.addNewConstantBase(begin, (Constant)let);
            }
            else {
                vars.add(let);
                
                if (endIndex-beginIndex <= 0) {
                    throw new RuntimeException("GEFactory.newGE: diagram contains variable base w/ length <= 0");
                }

                LabeledPath lp2 = d.getDual(lp);

                Letter let2;
                boolean swap = false;
                boolean forced = false;

                if (lp2 == null) {
                    HashMap lut = qs.getEquivalences();
                    Letter equiv = (Letter)lut.get(let);
                    if (equiv==null) {
                        equiv = (Letter)lut.get(let.getInverse());
                        equiv = equiv.getInverse();
                    }
                    
                    if (equiv==null) {
                        throw new RuntimeException("GEFactory.newGE: diagram contains variable of degree 1");
                    }
                    else {
                        // System.out.println("letter "+let+" == "+equiv);

                        Variable v = (Variable)equiv;
                        lp2 = d.getVariablePath(v);
                        let2 = lp2.getLabel();

                        if (lp2 == null) {
                            throw new RuntimeException("GEFactory.newGE: unable to make a variable base");
                        }

                        if (let2 == equiv.getInverse()) {
                            swap = true;
                        }
                        forced = true;
                    }
                }
                else {
                    let2 = lp2.getLabel();

                    if (let2 == let.getInverse()) {
                        swap = true;
                    }
                    forced = false;
                }

                int beginIndex2=((Integer)lp2begin.get(lp2)).intValue();
                int endIndex2=((Integer)lp2end.get(lp2)).intValue();
                Boundary begin2 = ge.getNthBoundary(beginIndex2);
                Boundary end2 = ge.getNthBoundary(endIndex2);

                if (endIndex2-beginIndex2 <= 0) {
                    throw new RuntimeException("GEFactory.newGE: diagram contains variable base w/ dual of length <= 0");
                }

                if (swap) {
                    Boundary swapBdy = begin2;
                    begin2 = end2;
                    end2 = swapBdy;
                }

                if (forced || (lp.hashCode() < lp2.hashCode())) {
                    ge.addNewVariableBase(begin, end, (Variable)let, begin2, end2);
                }
            }
        }


        for (Iterator it = d.iteratorEdges(); it.hasNext();) {
            Edge e = (Edge)it.next();
            LabeledPath[] lpArray = d.getPaths(e);

            // System.out.println("Considering edge "+e);

            LabeledPath lp1 = lpArray[0];
            LabeledPath lp2 = lpArray[1];

            // System.out.println("p1: "+lp1);
            // System.out.println("p2 "+lp2);

            int offset1 = lp1.getEdgeIndex(e);
            int offset2 = lp2.getEdgeIndex(e);
            offset1 = lp1.length() - offset1 - 1;
            offset2 = lp2.length() - offset2 - 1;

            // System.out.println("offset1: "+offset1);
            // System.out.println("offset2 "+offset2);

            int beginIndex=offset1+((Integer)lp2begin.get(lp1)).intValue();
            int endIndex=beginIndex+1;

            // System.out.println("beginIndex: "+beginIndex);

            Boundary begin = ge.getNthBoundary(beginIndex);
            Boundary end = ge.getNthBoundary(endIndex);

            int beginIndex2=offset2+((Integer)lp2begin.get(lp2)).intValue();
            int endIndex2=beginIndex2+1;

            // System.out.println("beginIndex2: "+beginIndex2);

            Boundary begin2 = ge.getNthBoundary(beginIndex2);
            Boundary end2 = ge.getNthBoundary(endIndex2);

            Variable mu = LetterFactory.instance().newUnusedVariable(vars,
                    params.MKParams.TEMPVAR_BASE, Boolean.TRUE);
            
            ge.addNewVariableBase(begin, end, (Variable)mu, end2, begin2);
            vars.add(mu);
        }
        
        return ge;
    }
}
