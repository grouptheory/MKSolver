/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package params;

/**
 *
 * @author grouptheory
 */
public class Safety {

    static class Settings {
        private Settings() {}
    }

    public static final Settings OFF = new Settings();
    public static final Settings ON = new Settings();
    public static final Settings PER_PACKAGE = new Settings();

    public static final Settings GLOBAL = ON;
}
