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

import java.util.TreeMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import letter.Letter;
import letter.Constant;
import letter.Variable;

/**
 *
 * @author grouptheory
 */
public class Latex {

    private static Latex _instance;

    private Latex() {
    }

    public static Latex instance() {
        if (_instance == null) {
            _instance = new Latex();
        }
        return _instance;
    }

    public String renderGEasText(GE ge) {
        String s = "";
        s+="\\begin{verbatim}\n";
        s+=ge.toString()+"\n";
        s+="\\end{verbatim}\n";
        return s;
    }

    public String renderGEasGraphics(GE ge) {
        int numBD = ge.getNumberOfBoundaries();
        int numBS = ge.getNumberOfBases();

        String s = "";
        s+="\\begin{center}\n";
        s+="\\begin{pspicture}(-0.5,-0.5)(7.5,6.5)\n";

        if (numBD==1) {

        }
        else {
            double boundaryspace = BaseLayoutDecoratorFactory.applyToAllBases(ge, 
                    params.MKParams.REPORT_GE_WIDTH, params.MKParams.REPORT_GE_HEIGHT);

            double x = 0.0;
            for (int i=0; i<numBD; i++) {
                s+="\\psline[linecolor=black]{-}("+x+",0.0)("+x+",6.0)";
                s+="\\rput{0}("+x+",0.0){$"+i+"$}\n";
                s+="\\rput{0}("+x+","+params.MKParams.REPORT_GE_HEIGHT+"){$"+i+"$}\n";
                x += boundaryspace;
            }

            BaseComparator bcomp = new BaseComparator();
            
            for (Iterator it=ge.iteratorBases(); it.hasNext();) {
                Base bs=(Base)it.next();
                BaseLayoutDecorator bld = (BaseLayoutDecorator)bs.lookupDecorator(BaseLayoutDecorator.NAME);
                double left = bld.getX1();
                double right = bld.getX2();
                double mid = (left+right)/2.0;
                String arrowhead = "{[->}";
                Letter let = bs.getLabel();
                if (!let.isPositive()) {
                    arrowhead = "{<-]}";
                }

                double y = bld.getY();
                //System.out.println("assigned "+bs+" y="+y);
                
                if (let.isConstant()) {
                    String colorStr="";
                    if (let.isConstant()) {
                        int id = let.getID();
                        if (id %3==0) {
                            colorStr="yellow";
                        }
                        else if (id %3==1) {
                            colorStr="blue";
                        }
                        else {
                            colorStr="green";
                        }
                    }
                    else {
                        colorStr="red";
                    }
                    s+="\\psline[linecolor="+colorStr+"]"+arrowhead+"("+left+","+y+")("+right+","+y+")";

                    if (!let.isPositive()) {
                        let = let.getInverse();
                    }
                    y -= 0.2;
                    s+="\\rput{0}("+mid+","+y+"){$"+letter.LatexAdapter.instance().render(let)+"$}\n";
                }
                else {
                    String colorStr="";
                    colorStr="red";
                    s+="\\psline[linecolor="+colorStr+"]"+arrowhead+"("+left+","+y+")("+right+","+y+")";

                    if (!let.isPositive()) {
                        let = let.getInverse();
                    }
                    y -= 0.2;
                    s+="\\rput{0}("+mid+","+y+"){$"+letter.LatexAdapter.instance().render(let)+"$}\n";

                    Base bs2 = bs.getDual();
                    if (bcomp.compare(bs, bs2) < 0) {

                        BaseLayoutDecorator bld2 = (BaseLayoutDecorator)bs2.lookupDecorator(BaseLayoutDecorator.NAME);
                        double left2 = bld2.getX1();
                        double right2 = bld2.getX2();
                        double y2 = bld2.getY();

                        int conidx=0;
                        Constraint con = bs.getConstraint();

                        for (Iterator it2=con.iteratorBoundary(); it2.hasNext();) {
                            Boundary bd1 = (Boundary)it2.next();
                            Boundary bd2 = con.getDual(bd1);
                            double x1;
                            if (bs2.getEnd().getID() != bs2.getBegin().getID()) {
                                x1 = left+(right-left)*(double)(bd1.getID()-bs.getBegin().getID())/
                                                          (double)(bs.getEnd().getID()-bs.getBegin().getID());
                            }
                            else {
                                x1 = right;
                            }
                            double x2;
                            if (bs2.getEnd().getID() != bs2.getBegin().getID()) {
                                x2 = left2+(right2-left2)*(double)(bd2.getID()-bs2.getBegin().getID())/
                                                             (double)(bs2.getEnd().getID()-bs2.getBegin().getID());
                            }
                            else {
                                x2 = right;
                            }
                            
                            String fillcolor = "";
                            if (conidx%2 == 0) {
                                fillcolor = "black";
                            }
                            else {
                                fillcolor = "white";
                            }

                            s+="\\pscircle[linecolor=red,fillcolor="+fillcolor+",fillstyle=solid]("+x1+","+(y+0.2)+"){0.075}\n";
                            s+="\\pscircle[linecolor=red,fillcolor="+fillcolor+",fillstyle=solid]("+x2+","+y2+"){0.075}\n";
                            conidx++;
                        }
                    }
                }
            }
        }
        s+="\\end{pspicture}\n";
        s+="\\end{center}\n";

        return s;
    }
}
