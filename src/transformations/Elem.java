/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package transformations;

import ge.GE;
import hom.Hom;

/**
 *
 * @author grouptheory
 */
public interface Elem {
    GE apply(GE actedOn);
    Hom getHom();
}
