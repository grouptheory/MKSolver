/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package letter;

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

    public String render(Letter let) {
        String s = "";
        if (let.isConstant()) s+= this.renderConstant((Constant)let);
        else s+= this.renderVariable((Variable)let);
        return s;
    }

    String renderVariable(Variable v) {
        String s = "";
        s += "z_{";
        s += ""+v.getID()+"}";
        if ( ! v.isPositive()) {
            s += "^{-1}";
        }
        return s;
    }

    String renderConstant(Constant c) {
        String s = "";
        s += "c_";
        s += "{"+c.getID()+"}";
        if ( ! c.isPositive()) {
            s += "^{-1}";
        }
        return s;
    }
}
