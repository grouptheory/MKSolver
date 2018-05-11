/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package letter;

import java.util.LinkedList;
import java.util.Iterator;

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
