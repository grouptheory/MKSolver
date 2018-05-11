/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utility;

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

        IntegerComposableIterator ici = new IntegerComposableIterator(2, 6);
        CompositeIterator compiter = new CompositeIterator(ici, false);
        // System.out.println("INITIAL compiter="+compiter.toString());

        int i=1;
        while (compiter.hasNext()) {
            // System.out.println("LOOP-preNEXT "+i+" compiter="+compiter.toString());
            Object o = compiter.next();
            // System.out.println("LOOP-postNEXT "+i+" compiter="+compiter.toString());
            System.out.println("STATE: "+o.toString());
            i++;
        }

        // System.out.println("FINAL compiter="+compiter.toString());


        IntegerComposableIterator ici2 = new IntegerComposableIterator(6,2);
        CompositeIterator compiter2 = new CompositeIterator(ici2, false);
        // System.out.println("INITIAL compiter="+compiter.toString());

        int i2=1;
        while (compiter2.hasNext()) {
            // System.out.println("LOOP-preNEXT "+i+" compiter="+compiter.toString());
            Object o2 = compiter2.next();
            // System.out.println("LOOP-postNEXT "+i+" compiter="+compiter.toString());
            System.out.println("STATE: "+o2.toString());
            i2++;
        }

        // System.out.println("FINAL compiter="+compiter.toString());
    }
}
