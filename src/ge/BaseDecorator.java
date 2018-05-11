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
public class BaseDecorator extends AbstractDecorator {

    public BaseDecorator() {
    }

    public Base getBase() {
        return (Base)super.getOwner();
    }

    public String getName() {
        return getOwner().lookupDecoratorName(this);
    }

    public void attach(String name, Base owner) {
        setOwner(owner);
        getOwner().attachDecorator(name, this);
    }

    public void detach() {
        getOwner().detachDecorator(this);
        setOwner(null);
    }
}
