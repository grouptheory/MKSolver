/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package report;

/**
 *
 * @author grouptheory
 */
public class ConsoleReportObserver implements IReportObserver {

    ConsoleReportObserver() {
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
