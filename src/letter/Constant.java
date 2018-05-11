/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package letter;

/**
 *
 * @author grouptheory
 */

public class Constant extends Letter {
    
    public Boolean isConstant() {
        return true;
    }

    private Boolean _pos;

    public Boolean isPositive() {
        return _pos;
    }

    private Constant _inverse;

    public Letter getInverse() {
        if (_inverse == null) {
            throw new RuntimeException("Constant.getInverse: _inverse == null");
        }
        return _inverse;
    }

    Constant(int id, Boolean positive) {
        super(id);
        _pos = positive;
    }

    void setInverse(Constant cinv) {
        _inverse = cinv;
    }

    public String toString() {
        String s = "";
        s += CONSTANT_SYMBOL;
        s += _id;
        if (_pos) {
            s += POSITIVE_SYMBOL;
        }
        else {
            s += NEGATIVE_SYMBOL;
        }
        s += ENDOFLETTER_SYMBOL;
        return s;
    }
}
