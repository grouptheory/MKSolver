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
public interface IReport {
    void setName(String name);
    String getName();

    void writeEntry(String msg);
    String readEntry();

    void create();
    
    void attachObserver(IReportObserver obs);
    void detachObserver(IReportObserver obs);
    void notifyObservers(IReport rep);
}