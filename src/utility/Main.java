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

package utility;

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

        IntegerComposableIterator ici = new IntegerComposableIterator(2, 6);
        CompositeIterator compiter = new CompositeIterator(ici, false);
        // System.out.println("INITIAL compiter="+compiter.toString());

        int i=1;
        while (compiter.hasNext()) {
            // System.out.println("LOOP-preNEXT "+i+" compiter="+compiter.toString());
            Object o = compiter.next();
            // System.out.println("LOOP-postNEXT "+i+" compiter="+compiter.toString());
            System.out.println("STATE: "+o.toString());
            i++;
        }

        // System.out.println("FINAL compiter="+compiter.toString());


        IntegerComposableIterator ici2 = new IntegerComposableIterator(6,2);
        CompositeIterator compiter2 = new CompositeIterator(ici2, false);
        // System.out.println("INITIAL compiter="+compiter.toString());

        int i2=1;
        while (compiter2.hasNext()) {
            // System.out.println("LOOP-preNEXT "+i+" compiter="+compiter.toString());
            Object o2 = compiter2.next();
            // System.out.println("LOOP-postNEXT "+i+" compiter="+compiter.toString());
            System.out.println("STATE: "+o2.toString());
            i2++;
        }

        // System.out.println("FINAL compiter="+compiter.toString());
    }
}
