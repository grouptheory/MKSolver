/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cancellation;

import letter.Letter;
import java.util.LinkedList;
import java.util.Iterator;

/**
 *
 * @author grouptheory
 */
public class ExtensionIteratorFactory {

    private static ExtensionIteratorFactory _instance;

    private ExtensionIteratorFactory() {
    }

    public static ExtensionIteratorFactory instance() {
        if (_instance == null) {
            _instance = new ExtensionIteratorFactory();
        }
        return _instance;
    }

    ExtensionIterator newExtensionIteratorLast(Diagram d, Letter let) {
        LinkedList exts = new LinkedList();

        Node dst = d.getEnd();
        BFS bfs = new BFS(d,dst);
        Node src = d.getBegin();

        Path p1 = bfs.getPathFrom(src);

        if (let.isConstant()) {
            // constants
            if (p1.length()==1) {
                Extension ex1=new ExtensionToVertexExact(let, src);
                exts.add(ex1);
            }
        }
        else {
            // variables
            if (p1.length() > 0) {
                Extension ex1=new ExtensionToVertexExact(let, src);
                exts.add(ex1);
            }
        }

        return new ExtensionIterator(exts);
    }

    ExtensionIterator newExtensionIterator(Diagram d, Letter let) {
        LinkedList exts = new LinkedList();

        Node dst = d.getEnd();
        BFS bfs = new BFS(d,dst);

        Iterator srcNodeIt = bfs.reachableNodesIterator();
        for ( ;srcNodeIt.hasNext(); ) {
            Node src = (Node)srcNodeIt.next();
            Path p1 = bfs.getPathFrom(src);

            if (let.isConstant()) {
                // constants
                if (p1.length()==1) {
                    Extension ex1=new ExtensionToVertexExact(let, src);
                    exts.add(ex1);
                }

                if (p1.length()==0) {
                    Extension ex1=new ExtensionToVertexSpur(let, src);
                    exts.add(ex1);
                }
            }
            else {
                // variables
                if (p1.length() > 0) {
                    Extension ex1=new ExtensionToVertexExact(let, src);
                    exts.add(ex1);
                }

                Extension ex2=new ExtensionToVertexSpur(let, src);
                exts.add(ex2);
            }
        }


        Iterator srcEdgeIt = bfs.reachableEdgesIterator();
        for ( ;srcEdgeIt.hasNext(); ) {
            Edge src = (Edge)srcEdgeIt.next();

            if ( ! d.isCuttableEdge(src)) {
                continue;
            }

            Path p1 = bfs.getPathFrom(src);

            if (let.isConstant()) {
                // constants
                if (p1.length()==1) {
                    Extension ex1=new ExtensionToEdgeExact(let, src);
                    exts.add(ex1);
                }
            }
            else {
                // variables
                Extension ex1=new ExtensionToEdgeExact(let, src);
                exts.add(ex1);

                Extension ex2=new ExtensionToEdgeSpur(let, src);
                exts.add(ex2);
            }
        }

        return new ExtensionIterator(exts);
    }
}
