/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package equation;

/**
 *
 * @author grouptheory
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        GroupEquation eqn = new GroupEquation("z1+.z2+.z1-.z2-.c1+.c2+.c1-.c2-.z1+.z2+.z1-.z2-.c1+.c2+.c1-.c2-.");

        QuadraticSystem qs;
        qs = QuadraticSystemFactory.instance().newQuadraticSystem(eqn);

        System.out.println("eqn: "+eqn);
        System.out.println("qs: "+qs);
    }
}
