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

package report;

import ge.GE;
import ge.GEFactory;
import makanin.Print;
import makanin.PrintIterator;
import makanin.PrintIteratorFactory;
import ge.Latex;
import ge.CancellationDiagramAnalysis_GEDecorator;
import equation.GroupWord;
import equation.QuadraticSystem;
import cancellation.Diagram;
import cancellation.DiagramTreeNode;
import cancellation.ICancellationDiagramAnalysis;
import cancellation.CancellationDiagramFactory;
import cancellation.DiagramDegeneracyTester;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import makanin.CarrierFactory;
import makanin.CriticalBoundaryFactory;
import makanin.BaseClassDecoratorFactory;
import makanin.GENode;
import makanin.GENodePriorityQueue;
import makanin.GEPriorityQueueLogger;

/**
 *
 * @author grouptheory
 */
public class SolutionReport extends AbstractReport {

    private static String SUFFIX = "-Solution.tex";

    String latexHeader(GroupWord eq) {
        String s = "";
        s+="\\documentclass[final]{article}\n";
        s+="\\usepackage{amssymb,amsmath,amsfonts}\n";
        s+="\\usepackage[dvips]{graphicx}\n";
        s+="\\usepackage{longtable}\n";
        s+="\\usepackage{verbatim}\n";
        s+="\\usepackage{fancyhdr}\n";
        s+="\\usepackage{pstricks-add,pst-slpe}\n";
        s+="\\pagestyle{fancy}\n";
        s+="\\fancyhead{}\n";
        s+="\\fancyfoot{}\n";
        s+="\\fancyhead[CO,CE]{$"+equation.LatexAdapter.instance().renderGroupEquation(eq)+"$}\n";
        s+="\\fancyfoot[RO, LE] {\\thepage}\n";
        s+="\\begin{document}\n";
        if ( ! params.MKParams.FLAG_REPORT_DATE) {
            s+="\\date{}\n";
        }
        return s;
    }

    String latexTitle(GroupWord eq) {
        String s="";
        s+="\\title{\n";
        s+="  {\\Large Solution of the equation \\\\";
        s += "$";
        s += equation.LatexAdapter.instance().renderGroupEquation(eq);
        s += "$";
        s += "\\\\ in a Free Group}\n";
        s+="  {\\normalsize\n";
        s+="   \\author{Bilal Khan\n";
        s+="        \\thanks{Department of Mathematics and Computer Science, John Jay College of Criminal Justice, City University of New York (CUNY).}\n";
        s+="   \\and M-K Solver\n";
        s+="        \\thanks{This report was generated automatically by software developed with support from the National Security Agency Grant H98230-06-1-0042.}\n";
        s+="           }\n";
        s+="  }\n";
        s+="}\n\n";
        s+="\\maketitle\n\n";
        s+="\\tableofcontents\n\n";
        return s;
    }

    String latexFooter() {
        String s = "";
        s+="\\section{Acknowledgements}\n";
        s+="The authors acknowledge that this report was generated by software developed as part of a funded project supported by a research grant (H98230-06-1-0042) from the National Security Agency.";
        s+="  We also give special thanks to Alexei Miasnikov and Olga Kharlampovich for many helpful discussions along the way.  ";
        s+="\\end{document}\n";
        return s;
    }

    private GroupWord _eq;
    private int MAX_SUBTREE;

    SolutionReport(String reportname, GroupWord eq, int maxSubtree) {
        super(reportname+SUFFIX);
        _eq = eq;
        MAX_SUBTREE = maxSubtree;
    }

    public void create() {
        GroupWord prob = _eq.duplicate();

        ICancellationDiagramAnalysis analysis;
        analysis = CancellationDiagramFactory.instance().newDiagramProbe(prob);

        analysis.addDecorator(new CancellationDiagramAnalysis_GEDecorator());

        writeEntry(latexHeader(prob));
        writeEntry(latexTitle(prob));

        int i=1;
        for (Iterator it = analysis.iteratorDiagramTreeNodes(); it.hasNext();) {
            DiagramTreeNode dtn = (DiagramTreeNode)it.next();

            writeEntry("\\newpage\n");
            writeEntry("\\section{Cancellation scheme \\#$"+i+"$}\n");

            QuadraticSystem qs = analysis.getQuadraticSystem();
            Diagram d = dtn.getDiagram();

            GEFactory gef = GEFactory.instance();
            GE geq = gef.newGE(d, qs);

            String nodename = "root-"+i;

            GENode gnode = new GENode(geq, nodename);
            GENodePriorityQueue pq = new GENodePriorityQueue();
            pq.attachObserver(new GEPriorityQueueLogger());

            pq.enqueue(gnode);

            writeEntry(cancellation.LatexAdapter.instance().renderDiagram(d));
            
            int gen=0;
            while (!pq.empty()) {
                GENode nextGE = pq.step();

                writeEntry("\\subsection*{Generalized Equation "+nextGE.getName()+"}\n");
                writeEntry("\\label{"+nextGE.getName()+"}");

                if (nextGE.getParent() != null) {
                    writeEntry("We begin from the GE "+nextGE.getParent().getName()+" (see pp. \\pageref{"+nextGE.getParent().getName()+"}).  ");

                    // _latex += makanin.Latex.instance().renderGEasGraphics(nextGE.getParent().getGE());
                }

                if (!nextGE.isLeaf()) {
                    int kcount=0;
                    for (PrintIterator pi = PrintIteratorFactory.instance().newPrintIterator(nextGE.getGE());
                         pi.hasNext();) {
                        Print p = (Print)pi.next();
                        kcount++;
                    }

                    String s = "";
                    s += ("   It has "+kcount+" legal carrier-to-dual prints, as follows:\n");
                    s+="\\begin{verbatim}\n";
                    int k=1;
                    for (PrintIterator pi = PrintIteratorFactory.instance().newPrintIterator(nextGE.getGE());
                         pi.hasNext();) {
                        Print p = (Print)pi.next();
                        s+=("     Print "+k+": "+p+"\n");
                        k++;
                    }
                    s+="\\end{verbatim}\n";
                    nextGE.appendToLog(s);
                }

                /*
                if (params.MKParams.FLAG_REPORT_GE_PICTURES) {
                    _latex += makanin.Latex.instance().renderGEasGraphics(nextGE.getGE());
                }
                if (params.MKParams.FLAG_REPORT_MAKANIN_CARRIER) {
                    _latex += makanin.Latex.instance().renderGEasText(nextGE.getGE());
                }
                */
                String closure = "";
                closure = ""+nextGE.getName()+
                        (nextGE.getParent()!=null?", as derived from the application of a print to "+nextGE.getParent().getName():"");

                nextGE.closeLog(closure);
                
                writeEntry(nextGE.getLog());
                gen++;

                if (gen>=MAX_SUBTREE) break;
            }
            
            i++;
        }

        writeEntry(latexFooter());
    }
}
