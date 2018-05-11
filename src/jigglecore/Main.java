/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jigglecore;

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

/*
Node 0 @ 5.999999999999999 , 0.0
Node 1 @ 2.8890725407747664 , 0.3191581928043395
Node 2 @ 4.1978494452756925 , 5.999999999999999
Node 3 @ 1.5365544482374358 , 2.793475452785374
Node 4 @ 0.0 , 0.8532927763978876
*/
        
        double Ax=2.8890725407747664;
        double Ay=0.3191581928043395;
        double Bx=4.1978494452756925;
        double By=5.999999999999999;

        double Cx=1.5365544482374358;
        double Cy=2.793475452785374;
        double Dx=5.999999999999999;
        double Dy=0.0;

        boolean inter = Intersector.intersect(Ax, Ay, Bx, By, Cx, Cy, Dx, Dy);

        System.out.println("The line intersect test: "+inter);
    }
}
