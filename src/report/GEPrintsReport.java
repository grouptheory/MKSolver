/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package report;


import ge.GE;
import ge.GEFactory;
import ge.Latex;
import ge.CancellationDiagramAnalysis_GEDecorator;
import equation.GroupEquation;
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

/**
 *
 * @author grouptheory
 */
public class GEPrintsReport extends AbstractReport {
    
    private static String SUFFIX = "-Prints.tex";

    String latexHeader() {
        String s = "";
        s+="\\documentclass[final]{article}\n";
        s+="\\usepackage{amssymb,amsmath,amsfonts}\n";
        s+="\\usepackage[dvips]{graphicx}\n";
        s+="\\usepackage{longtable}\n";
        s+="\\usepackage{verbatim}\n";
        s+="\\usepackage{pstricks-add,pst-slpe}\n";
        s+="\\begin{document}\n";
        if ( ! params.MKParams.FLAG_REPORT_DATE) {
            s+="\\date{}\n";
        }
        return s;
    }

    String latexTitle(GroupEquation eq) {
        String s="";
        s+="\\title{\n";
        s+="  {\\Large The Prints of the Generalized Equations of \\\\";
        s += "$";
        s += equation.Latex.instance().renderGroupEquation(eq);
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
        return s;
    }

    String latexFooter() {
        String s = "";
        s+="\\end{document}\n";
        return s;
    }

    private GroupEquation _eq;

    GEPrintsReport(String name, GroupEquation eq) {

        super(name+SUFFIX);
        _eq = eq.duplicate();
    }

    public void create() {
        GroupEquation prob = _eq.duplicate();

        ICancellationDiagramAnalysis analysis;
        /*
            analysis = CancellationDiagramFactory.instance().newDiagramTree(prob);
        */
            analysis = CancellationDiagramFactory.instance().newDiagramProbe(prob);

        analysis.addDecorator(new CancellationDiagramAnalysis_GEDecorator());

        writeEntry(latexHeader());
        writeEntry(latexTitle(prob));

        int i=1;
        for (Iterator it = analysis.iteratorDiagramTreeNodes(); it.hasNext();) {
            DiagramTreeNode dtn = (DiagramTreeNode)it.next();

            writeEntry("\\section{Generalized Equation \\#$"+i+"$}\n");

            QuadraticSystem qs = analysis.getQuadraticSystem();
            Diagram d = dtn.getDiagram();

            GEFactory gef = GEFactory.instance();
            GE geq = gef.newGE(d, qs);
            
            CarrierFactory.applyToGE(geq);
            CriticalBoundaryFactory.applyToGE(geq);
            BaseClassDecoratorFactory.applyToAllBases(geq);

            if (params.MKParams.FLAG_REPORT_GE_QUADRATICSYSTEM) {
                writeEntry(equation.Latex.instance().renderQSAsText(qs));
            }
            if (params.MKParams.FLAG_REPORT_GE_PICTURES) {
                writeEntry(makanin.Latex.instance().renderGEasGraphics(geq));
            }
            if (params.MKParams.FLAG_REPORT_MAKANIN_CARRIER) {
                writeEntry(makanin.Latex.instance().renderGEasText(geq));
            }
            if (params.MKParams.FLAG_REPORT_MAKANIN_PRINTS) {
                writeEntry(makanin.Latex.instance().renderPrintsasText(geq));
            }
            i++;
        }

        writeEntry(latexFooter());
    }
}
