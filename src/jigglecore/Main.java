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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jigglecore;

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

/*
Node 0 @ 5.999999999999999 , 0.0
Node 1 @ 2.8890725407747664 , 0.3191581928043395
Node 2 @ 4.1978494452756925 , 5.999999999999999
Node 3 @ 1.5365544482374358 , 2.793475452785374
Node 4 @ 0.0 , 0.8532927763978876
*/
        
        double Ax=2.8890725407747664;
        double Ay=0.3191581928043395;
        double Bx=4.1978494452756925;
        double By=5.999999999999999;

        double Cx=1.5365544482374358;
        double Cy=2.793475452785374;
        double Dx=5.999999999999999;
        double Dy=0.0;

        boolean inter = Intersector.intersect(Ax, Ay, Bx, By, Cx, Cy, Dx, Dy);

        System.out.println("The line intersect test: "+inter);
    }
}
