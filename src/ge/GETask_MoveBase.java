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
public class GETask_MoveBase implements IGETask {
    private Base _bs;
    private Boundary _begin;
    private Boundary _end;
    private GE _geq;
    private String _msg;

    public GETask_MoveBase(Base bs, Boundary begin, Boundary end, GE geq) {
        if (begin==null) {
            throw new RuntimeException("GETask_MoveBase.ctor: begin == null");
        }
        if (end==null) {
            throw new RuntimeException("GETask_MoveBase.ctor: end == null");
        }
        if (bs==null) {
            throw new RuntimeException("GETask_MoveBase.ctor: bs == null");
        }

        _bs = bs;
        _begin = begin;
        _end = end;
        _geq = geq;
        _msg = log1();
    }

    public void execute() {
        // System.out.println("executing: "+_msg);
        _bs.move(_begin, _end);
        _msg += log2();
    }

    public String toString() {
        return _msg;
    }

    private String log1() {
        String s="";
        s += "Moved (old) base "+_bs.toStringShort()+"";
        return s;
    }

    private String log2() {
        String s="";
        s += " to (new) boundaries "+_begin+" - "+_end+".";
        return s;
    }
}
