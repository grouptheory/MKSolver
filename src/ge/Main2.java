/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ge;

import equation.GroupEquation;

/**
 *
 * @author grouptheory
 */
public class Main2 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        GroupEquation problem = new GroupEquation("z1+.c1+.z2+.c2+.z1-.c3+.z2-.");

        TopLevelGEIterator iter = TopLevelGEIteratorFactory.instance().newTopLevelGEIterator(problem);

        int i=0;
        for (;iter.hasNext();) {
            GE geq = (GE)iter.next();

            System.out.println("GE#"+i+":\n");
            System.out.println(""+geq+"\n");
            i++;
        }

        System.out.println("Total number of GEs generated: "+i+"\n");
    }
}
