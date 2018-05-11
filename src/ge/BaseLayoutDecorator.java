/*
    Copyright 2008 Bilal Khan
    grouptheory@gmail.com

    This file is part of MKSolver.

    MKSolver is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    MKSolver is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
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
