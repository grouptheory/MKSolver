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
public class ExtensionToVertexSpur extends Extension {
    private static String NAME = "ExtensionToVertexSpur";
    private Node _srcNode;

    ExtensionToVertexSpur(Letter label, Node src) {
        super(label);
        _srcNode = src;
    }

    Diagram apply(Diagram d) {
        Diagram d2 = new Diagram(d);
        Node end = d2.getEnd();
        Node spurNode = d2.addNode();
        Node src = d2.lookupNode(_srcNode.getID());
        d2.addEdge(src, spurNode);

        // System.out.println("DEBUG ExtensionToVertexSpur "+d2);
        
        BFS bfs = new BFS(d2, end);
        Path p = bfs.getPathFrom(spurNode);
        LabeledPath lp = new LabeledPath(_label, p);
        d2.addLabeledPath(lp);
        return d2;
    }

    public String toString() {
         return ""+NAME+"->"+_srcNode.toStringShort()+"";
    }
}
