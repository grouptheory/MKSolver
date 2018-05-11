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
public class GETask_DeleteBoundary implements IGETask {

    private Boundary _bd;
    private GE _geq;
    private String _msg;

    public GETask_DeleteBoundary(Boundary bd, GE geq) {
        if (bd==null) {
            throw new RuntimeException("GETask_DeleteBoundary.ctor: bd == null");
        }
        _bd = bd;
        _geq = geq;
        _msg = log();
    }

    public void execute() {
        // System.out.println("executing: "+_msg);
        _geq.removeBoundary(_bd);
    }

    public String toString() {
        return _msg;
    }

    private String log() {
        String s="";
        s += "Deleted (new) boundary "+_bd+" because it is not used inside any base.  This will cause renumbering of higher numbered boundaries.\n";
        return s;
    }
}

