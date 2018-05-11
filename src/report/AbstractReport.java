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

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author grouptheory
 */
public abstract class AbstractReport implements IReport {

    private String _name;
    private LinkedList _q;

    protected AbstractReport(String name) {
        setName(name);
        _q = new LinkedList();
        _observers = new LinkedList();
    }

    public abstract void create();
    
    public final void writeEntry(String msg) {
        _q.addLast(msg);
        notifyObservers(this);
        _q.removeFirst();
    }
    
    public final String readEntry() {
        return (String)_q.getFirst();
    }

    public final void setName(String name) {
        _name = name;
    }

    public final String getName() {
        return _name;
    }

    private LinkedList _observers;

    public final void attachObserver(IReportObserver obs) {
        _observers.addLast(obs);
    }

    public final void detachObserver(IReportObserver obs) {
        _observers.remove(obs);
    }

    public final void notifyObservers(IReport rep) {
        for (Iterator it=_observers.iterator(); it.hasNext();) {
            IReportObserver obs = (IReportObserver)it.next();
            obs.notify(this);
        }
    }
}
