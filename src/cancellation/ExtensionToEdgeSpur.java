/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cancellation;

import letter.Letter;

/**
 *
 * @author grouptheory
 */
public class ExtensionToEdgeSpur extends Extension {
    private static String NAME = "ExtensionToEdgeSpur";
    private Edge _srcEdge;

    ExtensionToEdgeSpur(Letter label, Edge src) {
        super(label);
        _srcEdge = src;
    }

    Diagram apply(Diagram d) {
        Diagram d2 = new Diagram(d);
        Node end = d2.getEnd();
        Edge src = d2.lookupEdge(_srcEdge.getA().getID(), _srcEdge.getB().getID());
        Node cutNode = d2.cutEdge(src);
        Node spurNode = d2.addNode();
        d2.addEdge(cutNode, spurNode);

        BFS bfs = new BFS(d2, end);
        Path p = bfs.getPathFrom(spurNode);
        LabeledPath lp = new LabeledPath(_label, p);
        d2.addLabeledPath(lp);
        return d2;
    }

    public String toString() {
         return ""+NAME+"->"+_srcEdge+"";
    }
}
