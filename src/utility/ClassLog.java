/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utility;

/**
 *
 * @author grouptheory
 */
public class ClassLog {
    String _data;

    public ClassLog() {
        _data = "";
    }

    public String toString() {
        return _data;
    }

    public void append(String msg) {
        _data += msg;
    }

    public void clear() {
        _data = "";
    }
}
