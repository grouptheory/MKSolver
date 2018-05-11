/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
