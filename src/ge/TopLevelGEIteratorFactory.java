/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ge;

import equation.GroupEquation;

/**
 *
 * @author grouptheory
 */
public class TopLevelGEIteratorFactory {

    private static TopLevelGEIteratorFactory _instance;

    private TopLevelGEIteratorFactory() {
    }

    public static TopLevelGEIteratorFactory instance() {
        if (_instance == null) {
            _instance = new TopLevelGEIteratorFactory();
        }
        return _instance;
    }

    public TopLevelGEIterator newTopLevelGEIterator(GroupEquation eq) {
        return new TopLevelGEIterator(eq);
    }
}
