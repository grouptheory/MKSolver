/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cancellation;

/**
 *
 * @author grouptheory
 */
public class DiagramDegeneracyTester {

    public static boolean isDegenerate(Diagram d) {

        if ( ! d.isClosed()) return true;
        else
            return false;
    }

}
