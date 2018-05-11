/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package makanin;

import ge.Base;
import ge.GE;
import ge.GEDecorator;

/**
 *
 * @author grouptheory
 */
public class Carrier extends GEDecorator {

    static final String NAME = "Carrier";

    private Base _carrier;
    
    Carrier(Base carrier) {
        super();
        _carrier = carrier;
    }

    Base getBase() {
        return _carrier;
    }
}
