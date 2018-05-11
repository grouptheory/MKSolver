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

package letter;

import java.util.LinkedList;
import java.util.Iterator;

/**
 * A sample program based on the letter package.
 *
 * @author grouptheory
 */
public class Main {

    /**
     * A sample program based on the letter package.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Letter z1 = LetterFactory.instance().getVariable(1, true);
        Letter z2 = LetterFactory.instance().getVariable(2, true);
        Letter z1inv = LetterFactory.instance().getVariable(1, false);
        Letter z2inv = LetterFactory.instance().getVariable(2, false);

        Letter c1 = LetterFactory.instance().getConstant(1, true);
        Letter c2 = LetterFactory.instance().getConstant(2, true);
        Letter c1inv = LetterFactory.instance().getConstant(1, false);
        Letter c2inv = LetterFactory.instance().getConstant(2, false);

        String s = "";
        s+=z1.toString();
        s+=z2.toString();
        s+=z1inv.toString();
        s+=z2inv.toString();
        s+=c1.toString();
        s+=c2.toString();
        s+=c1inv.toString();
        s+=c2inv.toString();
        s+=z1.toString();
        s+=z2.toString();
        s+=z1inv.toString();
        s+=z2inv.toString();
        s+=c1.toString();
        s+=c2.toString();
        s+=c1inv.toString();
        s+=c2inv.toString();

        System.out.println(s);

        System.out.println("Parsing string");
        
        LinkedList list = LetterFactory.instance().parse(s);
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Letter let = (Letter)it.next();
            System.out.println(let.toString());
        }

        System.out.println("LetterFactory: ");
        System.out.println(LetterFactory.instance().toString());
    }
}
