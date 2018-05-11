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

package equation;

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

        GroupWord eqn = new GroupWord("z1+.z2+.z1-.z2-.c1+.c2+.c1-.c2-.z1+.z2+.z1-.z2-.c1+.c2+.c1-.c2-.");

        QuadraticSystem qs;
        qs = QuadraticSystemFactory.instance().newQuadraticSystem(eqn);

        System.out.println("eqn: "+eqn);
        System.out.println("qs: "+qs);
    }
}
