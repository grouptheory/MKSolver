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

import java.util.LinkedList;
import java.util.Iterator;

/**
 *
 * @author grouptheory
 */
public class GETrivialityTester {

    class Evidence {
        private String _s;

        Evidence(String s) {
            _s = s;
        }

        public String toString() {
            return _s;
        }
    }

    private LinkedList _evidence;

    public void reportEvidence(String s) {
        _evidence.addLast(new Evidence(s));
    }
    
    private boolean _isTrivial;

    public GETrivialityTester(GE geq) {
        _evidence = new LinkedList();
        _isTrivial = compute(geq);
    }

    public boolean isTrivial() {
        return _isTrivial;
    }

    private boolean compute(GE geq) {
        boolean answer = true;
        for (Iterator it = geq.iteratorBases(); it.hasNext();) {
            Base bs = (Base)it.next();
            if (bs.getLabel().isConstant()) continue;
            if ( ! bs.isEmpty()) {
                reportEvidence("Variable base "+bs.toStringShort()+" is not empty.  ");
                answer = false;
            }
        }
        return answer;
    }

    public String toString() {
        String s="";
        for (Iterator it = _evidence.iterator(); it.hasNext();) {
            Evidence ev=(Evidence)it.next();
            s+=(""+ev);
        }
        return s;
    }
}
