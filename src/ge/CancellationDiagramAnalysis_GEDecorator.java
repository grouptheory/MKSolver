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

import cancellation.*;
import equation.GroupWord;
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
