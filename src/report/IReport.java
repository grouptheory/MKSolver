/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
