/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package equation;

import java.util.Iterator;
import java.util.HashMap;
import letter.Letter;

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

    public String renderGroupEquation(GroupEquation eq) {
        String s = "";
        for (GroupEquation.LetterIterator it = eq.getLetterIterator(); it.hasNext();) {
            Letter let = it.next();
            s += letter.Latex.instance().render(let);
        }
        s += "=_F 1";
        return s;
    }

    public String renderQuadraticSystem(QuadraticSystem qs) {
        String s = "";
        s += equation.Latex.instance().renderGroupEquation(qs.getEquation());
        HashMap equiv = qs.getEquivalences();
        if (equiv.size()>0) {
            s += " $, where $";
            for (Iterator it=equiv.keySet().iterator(); it.hasNext();) {
                Letter let = (Letter)it.next();
                Letter leteq = (Letter)equiv.get(let);

                s += letter.Latex.instance().render(let);
                s += "=";
                s += letter.Latex.instance().render(leteq);
                if (it.hasNext()) s+= ", ";
            }
        }
        s += ".";
        return s;
    }


    public String renderQSAsText(QuadraticSystem qs) {
        String s = "{\\bf Quadratic System:}\n";
        s += "$";
        s += renderQuadraticSystem(qs);
        s += "$";
        return s;
    }
}
