/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package report;

import equation.GroupEquation;

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


}
