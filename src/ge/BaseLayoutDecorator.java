/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ge;

/**
 *
 * @author grouptheory
 */
public class BaseLayoutDecorator extends BaseDecorator {

    static final String NAME = "Layout";

    private double _y;
    private double _x1;
    private double _x2;

    BaseLayoutDecorator(double x1, double x2, double y) {
        _x1 = x1;
        _x2 = x2;
        _y = y;
    }

    double getX1() {
        return _x1;
    }

    double getX2() {
        return _x2;
    }

    double getY() {
        return _y;
    }
}
