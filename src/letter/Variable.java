/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package letter;

/**
 *
 * @author grouptheory
 */

public class Variable extends Letter {

    public Boolean isConstant() {
        return false;
    }

    private Boolean _pos;

    public Boolean isPositive() {
        return _pos;
    }

    private Variable _inverse;

    public Letter getInverse() {
        if (_inverse == null) {
            throw new RuntimeException("Variable.getInverse: _inverse == null");
        }
        return _inverse;
    }

    Variable(int id, Boolean positive) {
        super(id);
        _pos = positive;
    }

    void setInverse(Variable vinv) {
        _inverse = vinv;
    }

    public String toString() {
        String s = "";
        s += VARIABLE_SYMBOL;
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

