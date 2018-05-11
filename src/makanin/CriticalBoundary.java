/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package makanin;

import ge.Boundary;
import ge.GE;
import ge.GEDecorator;

/**
 *
 * @author grouptheory
 */
public class CriticalBoundary extends GEDecorator {

    static final String NAME = "CriticalBoundary";

    private Boundary _cr;

    CriticalBoundary(Boundary cr) {
        super();
        _cr = cr;
    }

    Boundary getBoundary() {
        return _cr;
    }
}
