/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utility;

import utility.IDecorator;
import utility.IDecorable;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author grouptheory
 */
public abstract class AbstractDecorable implements IDecorable {
    private HashMap _name2dec;
    private HashMap _dec2name;

    protected AbstractDecorable() {
        _name2dec = new HashMap();
        _dec2name = new HashMap();
    }

    public void attachDecorator(String name, IDecorator dec) {
        if (_name2dec.containsKey(name)) {
            throw new RuntimeException("AbstractDecorable.attachDecorator: _name2dec duplicate key: "+name);
        }
        if (_name2dec.containsValue(dec)) {
            throw new RuntimeException("AbstractDecorable.attachDecorator: _name2dec duplicate val");
        }
        if (_dec2name.containsKey(dec)) {
            throw new RuntimeException("AbstractDecorable.attachDecorator: _dec2name duplicate key");
        }
        if (_dec2name.containsValue(name)) {
            throw new RuntimeException("AbstractDecorable.attachDecorator: _dec2name duplicate val: "+name);
        }

        // Thread.dumpStack();
        
        _name2dec.put(name, dec);
        _dec2name.put(dec, name);
    }

    public void detachDecorator(IDecorator dec) {
        String name = lookupDecoratorName(dec);

        if (!_name2dec.containsKey(name)) {
            throw new RuntimeException("AbstractDecorable.detachDecorator: _name2dec unknown key");
        }
        if (!_dec2name.containsKey(dec)) {
            throw new RuntimeException("AbstractDecorable.detachDecorator: _dec2name unknown key");
        }

        _dec2name.remove(dec);
        _name2dec.remove(name);
    }

    public String lookupDecoratorName(IDecorator dec) {
        if (!_dec2name.containsKey(dec)) {
            throw new RuntimeException("AbstractDecorable.lookupDecoratorName: _dec2name unknown key");
        }
        return (String)_dec2name.get(dec);
    }

    public IDecorator lookupDecorator(String name) {
        /*
        if (!_name2dec.containsKey(name)) {
            throw new RuntimeException("AbstractDecorable.lookupDecoratorName: _name2dec unknown key");
        }
         */
        return (IDecorator)_name2dec.get(name);
    }

    public Iterator iteratorDecorators() {
        return _name2dec.values().iterator();
    }

}
