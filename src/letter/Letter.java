/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package letter;

/**
 *
 * @author grouptheory
 */

public abstract class Letter {

    static String VARIABLE_SYMBOL = "z";
    static String CONSTANT_SYMBOL = "c";
    static String POSITIVE_SYMBOL = "+";
    static String NEGATIVE_SYMBOL = "-";
    static String ENDOFLETTER_SYMBOL = ".";
            
    public abstract Boolean isConstant();
    public abstract Boolean isPositive();
    public abstract Letter getInverse();

    protected int _id;

    protected Letter(int id) {
        _id = id;
    }
    
    public int getID() {
        return _id;
    }

    public int modulus(int mod) {
        return _id % mod;
    }

    public static Boolean testEquals(Letter a1, Letter a2) {
        return (a1==a2);
    }

    public static Boolean testInverse(Letter a1, Letter a2) {
        return (a1.getInverse()==a2);
    }

    public static Boolean testEqualOrInverse(Letter a1, Letter a2) {
        return (testEquals(a1,a2) || testInverse(a1,a2));
    }
}



