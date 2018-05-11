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

package cancellation;

import java.util.Formatter;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.HashMap;
import letter.Letter;
import report.IReport;

/**
 * A singleton Factory for converting cancellation
 * Diagrams into Latex formatted Strings.
 *
 * @author grouptheory
 */
public class LatexAdapter {

    private static LatexAdapter _instance;

    private LatexAdapter() {
    }

    /**
     * Singleton getter.
     *
     * @return the singleton LatexAdapter.
     */
    public static LatexAdapter instance() {
        if (_instance == null) {
            _instance = new LatexAdapter();
        }
        return _instance;
    }

    private static final double OFFSET_PATHS = 0.25;

    static final int SYMBOL_LEN = 4;
    static final String FORMAT_STR = "%.2f";
    static Formatter _formatter = new Formatter();

    /**
     * Render a Diagram as a Latex format String.
     *
     * @param d a Diagram.
     * @return a Latex-formatted String representaiton of d.
     */
    public String renderDiagram(Diagram d) {

        DiagramDecoratorLayout layout;
        layout = (DiagramDecoratorLayout)d.getDecorator();
        if (layout == null) {
            layout = new DiagramDecoratorLayout(d);
            d.setDecorator(layout);
        }
        
        String str = "";
        str+="\\begin{center}\n";
        str+="\\begin{pspicture}(-0.5,-0.5)(6.5,6.5)\n";
        str+="{\\psset{fillstyle=ccslope,slopebegin=yellow!40,slopeend=gray}\n";

        for (Iterator it = d.iteratorNodes(); it.hasNext();) {
            Node nd = (Node)it.next();
            double x = layout.getX(nd);
            double y = layout.getY(nd);
            _formatter = new Formatter();
            String xstr = ""+_formatter.format(FORMAT_STR, x);
            _formatter = new Formatter();
            String ystr = ""+_formatter.format(FORMAT_STR, y);

            int id = nd.getID();
            str+="\\cnodeput("+xstr+","+ystr+"){"+id+"}{\\strut\\boldmath$"+id+"$}\n";
        }

        str+="}\n";
        str+="\\newcommand\\arc[3]{%\n";
        str+="  \\ncline{#1}{#2}{#3}\n";
        str+="}\n";

        for (Iterator it=d.iteratorEdges(); it.hasNext();) {
            Edge e = (Edge)it.next();
            str += "\\arc{-}{"+e.getA().getID()+"}{"+e.getB().getID()+"}{}\n";
        }

        HashMap canonical2covered = new HashMap();

        for (Iterator it=d.iteratorLabeledPaths();it.hasNext();) {

            LabeledPath lp = (LabeledPath)it.next();
            
            Letter let = lp.getLabel();
            Path p = lp.getPath();

            boolean line = (p.length()==1);
            String arrowhead = "{|->>}";
            if (!let.isPositive()) {
                arrowhead = "{<<-|}";
            }

            String colorStr="";
            if (let.isConstant()) {
                int id = let.getID();
                if (id%3==0) {
                    colorStr="yellow";
                }
                else if (id%3==1) {
                    colorStr="blue";
                }
                else if (id%3==2) {
                    colorStr="green";
                }
            }
            else {
                colorStr="red";
            }

            if (!line) str+="\\pscurve";
            else str+="\\psline";

            str+="[linecolor="+colorStr+"]"+arrowhead;

            LinkedList curves = new LinkedList();

            int midpoint = (p.length()+1)/2;
            int segnum = 1;
            boolean virgin=true;
            String labelStr = "";
            boolean prevcovered=false;

            Node s = p.getSrcNode();
            
            for (Iterator it2=p.iteratorEdges(); it2.hasNext();){
                Edge e=(Edge)it2.next();

                Node t = e.getOpposite(s);
                
                double xs = layout.getX(s);
                double ys = layout.getY(s);
                double xt = layout.getX(t);
                double yt = layout.getY(t);
                double angle = Math.atan2(yt-ys, xt-xs);

                boolean covered = (canonical2covered.get(e)!=null);
                angle+=(Math.PI/2.0);
                if ((covered!=prevcovered) && (!virgin)) {
                    curves.removeLast();
                }
                prevcovered=covered;

                double dy = OFFSET_PATHS*Math.sin(angle);
                double dx = OFFSET_PATHS*Math.cos(angle);
                xs += dx;
                ys += dy;
                xt += dx;
                yt += dy;

                _formatter = new Formatter();
                String xsstr = ""+_formatter.format(FORMAT_STR, xs);
                _formatter = new Formatter();
                String ysstr = ""+_formatter.format(FORMAT_STR, ys);
                _formatter = new Formatter();
                String xtstr = ""+_formatter.format(FORMAT_STR, xt);
                _formatter = new Formatter();
                String ytstr = ""+_formatter.format(FORMAT_STR, yt);
                if (virgin) {
                    curves.addLast("("+xsstr+","+ysstr+")");
                    virgin = false;
                }
                double xm = (xs+xt)/2.0;
                double ym = (ys+yt)/2.0;
                _formatter = new Formatter();
                String xmstr = ""+_formatter.format(FORMAT_STR, xm);
                _formatter = new Formatter();
                String ymstr = ""+_formatter.format(FORMAT_STR, ym);
                if (segnum==midpoint) {
                    String letStr = letter.LatexAdapter.instance().render(let);
                    if (!let.isPositive()) {
                        letStr = letter.LatexAdapter.instance().render(let.getInverse());
                    }

                    angle-=(Math.PI/2.0);
                    if (angle>Math.PI/2.0) angle-=Math.PI;
                    if (angle<-Math.PI/2.0) angle+=Math.PI;
                    double deg = (angle*180.0/Math.PI);
                    while (deg < 0) deg+=360.0;
                    _formatter = new Formatter();
                    String degstr = ""+_formatter.format("%d", (int)deg);
                    labelStr="\\rput{"+
                            degstr
                            +"}("+xmstr+","+ymstr+"){$"+letStr+"$}";
                }
                curves.addLast("("+xmstr+","+ymstr+")");
                curves.addLast("("+xtstr+","+ytstr+")");
                canonical2covered.put(e, Boolean.TRUE);
                segnum++;

                s = t;
            }

            for(Iterator it3=curves.iterator();it3.hasNext();) {
                String str2 = (String)it3.next();
                str+=str2;
            }
            str += labelStr+"\n";
        }

        str+="\\end{pspicture}\n";
        str+="\\end{center}\n";


        str+="\\begin{center}\n";
        str+="\\begin{tabular}{|ll|}\n";
        str+="\\hline\n";
        for (Iterator it=d.iteratorLabeledPaths();it.hasNext();) {
            LabeledPath lp = (LabeledPath)it.next();
            Letter let = lp.getLabel();
            Path p = lp.getPath();

            str+="$"+letter.LatexAdapter.instance().render(let)+"$";
            str+=" & ";


            Node s = p.getSrcNode();
            Node t = null;
            str += "$";
            for (Iterator it2=p.iteratorEdges(); it2.hasNext();){
                Edge e=(Edge)it2.next();

                t = e.getOpposite(s);
                
                str += ""+s.getID()+"\\leftarrow ";

                s = t;
            }
            str += ""+t.getID()+"";
            str += "$";

            str+="\\\\\n";
        }
        str+="\\hline\n";
        str+="\\end{tabular}\n";
        str+="\\end{center}\n";

        return str;
    }


    /**
     *
     * Render a ICancellationDiagramAnalysis into an IReport.
     *
     * @param report an IReport.
     * @param analysis the ICancellationDiagramAnalysis to be rendered.
     */
    public void renderCancellationDiagramAnalysis(IReport report, ICancellationDiagramAnalysis analysis) {

        String s = "";
        s += "We report on the cancellation diagrams of ";
        s += "$";

        s += equation.LatexAdapter.instance().renderGroupEquation(analysis.getProblem());

        s += "$";
        s += ",\n";
        s += "which can be re-expressed as a quadratic system ";
        s += "$";

        s += equation.LatexAdapter.instance().renderQuadraticSystem(analysis.getQuadraticSystem());

        s += "$";
        s += ".\n";
        s += "Below, we list the possible cancellation diagrams and then represent each as a generalized equation (GE).\n\n";
        report.writeEntry(s);
        s = "";

        for (Iterator it = analysis.iteratorDiagramTreeNodes(); it.hasNext();) {
            DiagramTreeNode dtn = (DiagramTreeNode)it.next();

            s += "\\newpage ";
            s += "\n\n{\\bf Cancellation diagram ";
            s += dtn.getName();
            s += ":}\n";

            if (params.MKParams.FLAG_REPORT_CANCELLATION_PICTURES) {
                s += renderDiagram(dtn.getDiagram());
            }
            
            for (Iterator itdec=analysis.iteratorDecorators(); itdec.hasNext();) {
                ICancellationDiagramAnalysisDecorator dec =
                        (ICancellationDiagramAnalysisDecorator)itdec.next();
                s += dec.texify(analysis, dtn);
            }

            report.writeEntry(s);
            s = "";
        }
    }
}
