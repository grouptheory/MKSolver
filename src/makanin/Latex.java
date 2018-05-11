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
import ge.GEDegeneracyTester;

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

    public String renderGEasText(GE geq) {
        String s = "";

        Carrier ca = (Carrier)geq.lookupDecorator(Carrier.NAME);
        if (ca == null) {
            throw new RuntimeException("Latex.renderGEasText: unknown carrier");
        }
        Base carrier_base = ca.getBase();
        if (carrier_base == null) {
            throw new RuntimeException("Latex.renderGEasText: carrier_base is null");
        }

        CriticalBoundary cr = (CriticalBoundary)geq.lookupDecorator(CriticalBoundary.NAME);
        if (cr == null) {
            throw new RuntimeException("Latex.renderGEasText: unknown critical boundary");
        }
        Boundary critical_boundary = cr.getBoundary();
        if (critical_boundary == null) {
            throw new RuntimeException("Latex.renderGEasText: critical_boundary is null");
        }

        Base carrier_dual = carrier_base.getDual();

        s+=("{\\bf GE Information}:  \n");
        s+=("Carrier: "+carrier_base.toStringShort()+";  \n");
        s+=("Carrier Dual: "+carrier_dual.toStringShort()+";  \n");
        s+=("Critical Boundary: "+critical_boundary+";  \n");

        return s;
    }

    public String renderPrintsasText(GE geq) {
        String s = "";

        s+=("{\\bf Prints}\n");
        s+="\\begin{verbatim}\n";
        int j=0;
        for (PrintIterator pi = PrintIteratorFactory.instance().newPrintIterator(geq);
             pi.hasNext();) {
            Print p = (Print)pi.next();
            s+=("     Print "+j+": "+p+"\n");
            j++;
        }
        s+="\\end{verbatim}\n";
        s+=("Total number of prints: "+j+"\\\\\n");

        j=1;
        for (PrintIterator pi = PrintIteratorFactory.instance().newPrintIterator(geq);
             pi.hasNext();) {
            Print p = (Print)pi.next();

            if (j==0) {
                s+=("{\\em First, we consider}\n");
            }
            else {
                s+=("{\\em Next, we consider}\n");
            }
            s+="\\begin{verbatim}\n";
            s+=("     Print "+j+": "+p+"\n");
            s+="\\end{verbatim}\n";

            PrintApplicator pa = new PrintApplicator(geq, p);
            GE childeq = pa.getPrinted();

            s+=("{\\bf Sequence of Actions in performing the Print "+j+":}\\\\\n");
            s+=pa.toString();

            s+=("{\\em Summarizing, the GE we obtain after applying}\n");
            s+="\\begin{verbatim}\n";
            s+=("     Print "+j+": "+p+"\n");
            s+="\\end{verbatim}\n";
            s+=("{\\em is shown below:}\n");

            s+=ge.Latex.instance().renderGEasGraphics(childeq);

            GEDegeneracyTester testchild = new GEDegeneracyTester(childeq);
            if (testchild.isDegenerate()) {
                s+=("Observe the following facts about this GE:\n");
                s+=testchild.toString();
                s+=("These observations show that the GE above is degenerate.\\\\"+params.MKParams.REPORT_SPACING+"\n");
            }
            else {
                s+=("The GE above is non-degenerate.\\\\"+params.MKParams.REPORT_SPACING+"\n");
            }
            s+=("This completes the consideration of Print "+j+".\\\\"+params.MKParams.REPORT_SPACING+"\n");
            
            j++;
        }

        return s;
    }

    public String renderGEasGraphics(GE geq) {
        String s = "";
        s += ge.Latex.instance().renderGEasGraphics(geq);
        return s;
    }
}
