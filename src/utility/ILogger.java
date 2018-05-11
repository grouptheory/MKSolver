/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utility;

/**
 *
 * @author grouptheory
 */
public interface ILogger {

    public interface Level {}

    void setDefaultVerbosity(Level level);

    void setVerbosity(String module, Level level);
    Level getVerbosity(String module);
    
    public void debug(String module, String msg);
    public void info(String module, String msg);
    public void warn(String module, String msg);
    public void error(String module, String msg);
    public void fatal(String module, String msg);
}
