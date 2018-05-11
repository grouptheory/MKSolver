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

package utility;

import java.util.HashMap;

/**
 *
 * @author grouptheory
 */
public class ConsoleLogger implements ILogger {

    public static class Level implements ILogger.Level, Comparable {
        private int _code;
        private Level(int code) {
            _code = code;
        }
        public int compareTo(Object o) {
            Level lo = (Level)o;
            if (this._code < lo._code) return -1;
            else if (this._code > lo._code) return +1;
            else return 0;
        }
        public String toString() {
            return "x"+_code+"x";
        }
    }

    class Chunk {
        ConsoleLogger.Level _level;
        String _msg;
        String _module;
        Chunk(ConsoleLogger.Level level, String module, String msg) {
            _level = level;
            _module = module;
            _msg = msg;
        }
        public String toString() {
            if (_level.compareTo(getLevel(_module)) >= 0) {
                return PROMPT+_level+"|"+_module+"||"+_msg;
            }
            else {
                return "";
            }
        }
    }
    private HashMap _currentLevel = new HashMap();
    public static final ConsoleLogger.Level DEBUG = new Level(0);
    public static final ConsoleLogger.Level INFO = new Level(1);
    public static final ConsoleLogger.Level WARN = new Level(2);
    public static final ConsoleLogger.Level ERROR = new Level(3);
    public static final ConsoleLogger.Level FATAL = new Level(4);
    private static ILogger.Level DEFAULT_LEVEL = FATAL;

    ConsoleLogger.Level getLevel(String module) {
        if (_currentLevel.containsKey(module)) {
            return (ConsoleLogger.Level)_currentLevel.get(module);
        }
        else {
            return (ConsoleLogger.Level)DEFAULT_LEVEL;
        }
    }
    
    private static ConsoleLogger _instance;
    private static String PROMPT = ": ";
    
    private ConsoleLogger() {
    }

    public static ILogger instance() {
        if (_instance == null) {
            _instance = new ConsoleLogger();
        }
        return _instance;
    }

    public void setDefaultVerbosity(ILogger.Level level) {
        DEFAULT_LEVEL = level;
    }

    public void setVerbosity(String module, ILogger.Level level) {
        _currentLevel.put(module, level);
    }

    public ILogger.Level getVerbosity(String module) {
        return getLevel(module);
    }

    public void debug(String module, String msg) {
        Chunk chunk = new Chunk(DEBUG, module, msg);
        output(chunk);
    }
    
    public void info(String module, String msg) {
        Chunk chunk = new Chunk(INFO, module, msg);
        output(chunk);
    }

    public void warn(String module, String msg) {
        Chunk chunk = new Chunk(WARN, module, msg);
        output(chunk);
    }

    public void error(String module, String msg) {
        Chunk chunk = new Chunk(ERROR, module, msg);
        output(chunk);
    }

    public void fatal(String module, String msg) {
        Chunk chunk = new Chunk(FATAL, module, msg);
        output(chunk);
    }

    private void output(Chunk chunk) {
        String s = chunk.toString();
        if (s.length() > 0) {
            System.out.println(s);
        }
    }
}
