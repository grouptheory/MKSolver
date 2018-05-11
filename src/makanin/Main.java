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

import equation.GroupWord;
import ge.TopLevelGEIterator;
import ge.TopLevelGEIteratorFactory;
import ge.GE;
import ge.Base;
import ge.Boundary;
import java.util.Iterator;
import utility.CompositeIterator;
import utility.CompositeIterator.State;

/**
 *
 * @author grouptheory
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        GroupWord problem = new GroupWord("z1+.c1+.z2+.c2+.z1-.c3+.z2-.");

        TopLevelGEIterator iter = TopLevelGEIteratorFactory.instance().newTopLevelGEIterator(problem);

        int i=0;
        for (;iter.hasNext();) {
            GE geq = (GE)iter.next();

            CarrierFactory.applyToGE(geq);
            CriticalBoundaryFactory.applyToGE(geq);
            BaseClassDecoratorFactory.applyToAllBases(geq);

            Carrier ca = (Carrier)geq.lookupDecorator(Carrier.NAME);
            if (ca == null) {
                throw new RuntimeException("gutierrez.Main: unknown carrier");
            }
            Base carrier_base = ca.getBase();
            if (carrier_base == null) {
                throw new RuntimeException("gutierrez.Main: carrier_base is null");
            }

            CriticalBoundary cr = (CriticalBoundary)geq.lookupDecorator(CriticalBoundary.NAME);
            if (cr == null) {
                throw new RuntimeException("gutierrez.Main: unknown critical boundary");
            }
            Boundary critical_boundary = cr.getBoundary();
            if (critical_boundary == null) {
                throw new RuntimeException("gutierrez.Main: critical_boundary is null");
            }

            Base carrier_dual = carrier_base.getDual();

            System.out.println("GE#"+i+":\n");

            /*
            System.out.println(""+geq+"\n");
            System.out.println("\n");
            System.out.println("Carrier: "+carrier_base+"\n");
            System.out.println("Carrier Dual: "+carrier_dual);
            System.out.println("Critical Boundary: "+critical_boundary+"\n");
            System.out.println("\n\n");
            */

            i++;

            int j=0;
            for (PrintIterator pi = PrintIteratorFactory.instance().newPrintIterator(geq);
                 pi.hasNext();) {
                Print p = (Print)pi.next();
                // System.out.println("PRINT "+p);
                j++;
            }
            System.out.println("Total number of prints generated: "+j+"\n");

            //break;
        }

        System.out.println("Total number of GEs generated: "+i+"\n");
    }
}