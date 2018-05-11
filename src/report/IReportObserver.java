/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package report;

/**
 *
 * @author grouptheory
 */
public interface IReportObserver {
    void notify(IReport r);
    void close();
}
