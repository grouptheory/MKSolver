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
public class GETask_ConstraintAddition implements IGETask {
    private Base _bs;
    private Boundary _bd;
    private Boundary _bd_dual;
    private GE _geq;
    private String _msg;

    public GETask_ConstraintAddition(Base bs, Boundary bd, Boundary bd_dual, GE geq) {
        if (bs==null) {
            throw new RuntimeException("GETask_ConstraintAddition.ctor: bs == null");
        }
        if (bd==null) {
            throw new RuntimeException("GETask_ConstraintAddition.ctor: bd == null");
        }
        if (bd_dual==null) {
            throw new RuntimeException("GETask_ConstraintAddition.ctor: bd_dual == null");
        }

        _bs = bs;
        _bd = bd;
        _bd_dual = bd_dual;
        _geq = geq;
        _msg = null;
    }

    public void execute() {
        // System.out.println("executing: "+log());
        _geq.addNewConstraint(_bs, _bd, _bd_dual);
        _msg = log();
    }

    public String toString() {
        return _msg;
    }

    private String log() {
        String s="";
        s += "Added constraint between boundary "+_bd+" in (new) base "+_bs.toStringShort()+" and boundary "+_bd_dual+" in its dual.";
        return s;
    }
}
