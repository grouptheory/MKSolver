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

import equation.GroupWord;

/**
 *
 * @author grouptheory
 */
public class Main2 {
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

            System.out.println("GE#"+i+":\n");
            System.out.println(""+geq+"\n");
            i++;
        }

        System.out.println("Total number of GEs generated: "+i+"\n");
    }
}
