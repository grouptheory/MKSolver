/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utility;

import utility.IDecorator;
import utility.IDecorable;

/**
 *
 * @author grouptheory
 */
public abstract class AbstractDecorator implements IDecorator {

    private IDecorable _owner;

    protected AbstractDecorator() {
    }
    
    public void setOwner(IDecorable owner) {
        if ((_owner != null) && (_owner != owner)) {
            throw new RuntimeException("BaseDecorator.attach: already attached");
        }

        if (_owner != owner) {
            _owner = owner;
        }
    }
    
    public IDecorable getOwner() {
        return _owner;
    }
}
