/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package letter;

/**
 * Specifies runtime safety settings for this package.
 *
 * @author grouptheory
 */
public class Safety {
    
    private static final boolean PACKAGE_VALUE = false;

    static final boolean CHECK = ((PACKAGE_VALUE && params.Safety.GLOBAL!=params.Safety.OFF) ||
                                         params.Safety.GLOBAL==params.Safety.ON);
}
