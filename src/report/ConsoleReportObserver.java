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

package report;

/**
 *
 * @author grouptheory
 */
public class ConsoleReportObserver implements IReportObserver {

    public ConsoleReportObserver() {
        _initialized = false;
    }

    public void notify(IReport r) {
        if (!_initialized) initialize(r);
        if (_initialized) {
            String msg = r.readEntry();
            System.out.println(""+msg);
        }
    }

    public void close() {
        System.out.println("========== END OF REPORT: "+_name);
        _initialized = false;
    }

    private boolean _initialized;
    private String _name;
    
    private void initialize(IReport r) {
        _name = r.getName();
        _initialized = true;
        System.out.println("========== BEGINNING OF REPORT: "+_name);
    }
}
