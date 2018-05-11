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
public class GETask_BoundaryInsertion implements IGETask {
    private Boundary _bd;
    private boolean _after;
    private Boundary _newbd;
    private GE _geq;
    private String _msg;

    public GETask_BoundaryInsertion(Boundary bd, boolean after, GE geq) {
        if (bd==null) {
            throw new RuntimeException("GETask_BoundaryInsertion.ctor: bd == null");
        }
        
        _bd = bd;
        _after = after;
        _geq = geq;
        _msg = null;
    }

    public void execute() {
        if (_after) {
            _newbd = _geq.insertNewBoundaryAfter(_bd);
        }
        else {
            _newbd = _geq.insertNewBoundaryBefore(_bd);
        }
        _msg = log();
        // System.out.println("executing: "+_msg);
    }

    public Boundary getNewBoundary() {
        return _newbd;
    }

    public String toString() {
        return _msg;
    }
    
    private String log() {
        String s="";
        s += "Added (new) boundary "+_newbd+".";
        return s;
    }
}
