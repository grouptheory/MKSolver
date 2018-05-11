/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ge;

import cancellation.*;
import equation.GroupEquation;
import equation.QuadraticSystem;
import ge.GE;
import ge.GEFactory;

/**
 *
 * @author grouptheory
 */
public class CancellationDiagramAnalysis_GEDecorator
        implements ICancellationDiagramAnalysisDecorator {

    public CancellationDiagramAnalysis_GEDecorator() {
    }
    
    public String texify(ICancellationDiagramAnalysis analysis, DiagramTreeNode dtn) {

        QuadraticSystem qs = analysis.getQuadraticSystem();
        Diagram d = dtn.getDiagram();

        GEFactory gef = GEFactory.instance();
        GE geq = gef.newGE(d, qs);

        String s="";
        if (params.MKParams.FLAG_REPORT_GE_STRUCTURES) {
            s += ge.Latex.instance().renderGEasText(geq);
        }
        if (params.MKParams.FLAG_REPORT_GE_PICTURES) {
            s += ge.Latex.instance().renderGEasGraphics(geq);
        }

        return s;
    }
}
