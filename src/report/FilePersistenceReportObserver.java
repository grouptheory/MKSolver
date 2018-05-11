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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

/**
 *
 * @author grouptheory
 */
public class FilePersistenceReportObserver implements IReportObserver {

    public FilePersistenceReportObserver() {
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
            if (_outfile != null) {
                _outfile.close();
                _outfile = null;
                _initialized = false;
            }
        } catch (IOException e) {
            _outfile = null;
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
            _outfile = null;
            throw new RuntimeException("File could not be opened.");
        }
    }
}
