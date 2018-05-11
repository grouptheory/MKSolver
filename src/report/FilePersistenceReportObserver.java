/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package report;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

/**
 *
 * @author grouptheory
 */
public class FilePersistenceReportObserver implements IReportObserver {

    FilePersistenceReportObserver() {
        _initialized = false;
    }
    
    public void notify(IReport r) {
        if (!_initialized) initialize(r);
        if (_initialized) {
            String msg = r.readEntry();
            try {
                _outfile.write(msg);
                _outfile.flush();
            } catch (IOException e) {
                throw new RuntimeException("File could not be written to.");
            }
        }
    }

    public void close() {
        try {
            _outfile.close();
            _initialized = false;
        } catch (IOException e) {
            throw new RuntimeException("File could not be closed.");
        }
    }
    
    private boolean _initialized;
    private BufferedWriter _outfile;
    
    private void initialize(IReport r) {
        try {
            _outfile = new BufferedWriter(new FileWriter(params.MKParams.OUTPUT_DIRECTORY+"/"+r.getName()));
            _initialized = true;
        } catch (IOException e) {
            throw new RuntimeException("File could not be opened.");
        }
    }
}
