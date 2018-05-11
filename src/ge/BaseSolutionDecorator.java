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

import equation.GroupWord;

/**
 *
 * @author grouptheory
 */
public class BaseSolutionDecorator extends BaseDecorator {

    static final String NAME = "Solution";

    private GroupWord _eq;

    BaseSolutionDecorator(GroupWord eq) {
        _eq = eq;
    }

    private BaseSolutionDecorator(BaseSolutionDecorator orig) {
        _eq = orig._eq.duplicate();
    }

    public BaseSolutionDecorator duplicate() {
        return new BaseSolutionDecorator(this);
    }
    
    GroupWord getSolution() {
        return _eq;
    }

    public static void attachSolution(Base bs, GroupWord sol) {
        bs.attachDecorator(BaseSolutionDecorator.NAME,
                new BaseSolutionDecorator(sol.duplicate()));
    }
}

