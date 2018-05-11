/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package makanin;

import ge.GE;

/**
 *
 * @author grouptheory
 */
public class PrintIteratorFactory {

    private static PrintIteratorFactory _instance;

    private PrintIteratorFactory() {
    }

    public static PrintIteratorFactory instance() {
        if (_instance == null) {
            _instance = new PrintIteratorFactory();
        }
        return _instance;
    }

    public PrintIterator newPrintIterator(GE geq) {
        return new PrintIterator(geq);
    }
}
