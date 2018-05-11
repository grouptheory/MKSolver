/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ge;

import utility.AbstractDecorator;

/**
 *
 * @author grouptheory
 */
public class GEDecorator extends AbstractDecorator {

    public GEDecorator() {
    }

    public GE getGE() {
        return (GE)super.getOwner();
    }
    
    public String getName() {
        return getOwner().lookupDecoratorName(this);
    }

    public void attach(String name, GE owner) {
        setOwner(owner);
        getOwner().attachDecorator(name, this);
    }

    public void detach() {
        getOwner().detachDecorator(this);
        setOwner(null);
    }
}
