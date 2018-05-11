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
public class GETask_CollapseBase implements IGETask {

    private Base _bs;
    private GE _geq;
    private String _msg;

    public GETask_CollapseBase(Base bs, GE geq) {
        if (bs==null) {
            throw new RuntimeException("GETask_CollapseBase.ctor: bs == null");
        }

        if (bs.getLabel().isConstant()) {
            throw new RuntimeException("GETask_CollapseBase.ctor: bs == constant");
        }

        Base dual = bs.getDual();

        if (!dual.isEmpty() &&
           ((bs.getBegin() != dual.getBegin()) ||
            (bs.getEnd() != dual.getEnd()) ||
            (bs.getLabel() != dual.getLabel()))) {
            throw new RuntimeException("GETask_CollapseBase.ctor: bs not collapsible");
        }

        _bs = bs;
        _geq = geq;
        _msg = log();
    }

    public void execute() {
        // System.out.println("executing: "+_msg);
        _geq.collapseBase(_bs);
    }

    public String toString() {
        return _msg;
    }

    private String log() {
        String s="";
        s += "Collapsed (new) base "+_bs.toStringShort()+" to the empty base ("+_bs.getEnd()+","+_bs.getEnd()+").\n";
        return s;
    }
}
