/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cancellation;

import letter.Letter;

/**
 *
 * @author grouptheory
 */
public abstract class Extension {
    protected Letter _label;

    protected Extension(Letter label) {
        _label = label;
    }
    
    abstract Diagram apply(Diagram d);
}
