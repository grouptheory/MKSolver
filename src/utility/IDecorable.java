/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utility;

/**
 *
 * @author grouptheory
 */
public interface IDecorable {
    void attachDecorator(String name, IDecorator dec);
    void detachDecorator(IDecorator dec);

    String lookupDecoratorName(IDecorator dec);
}
