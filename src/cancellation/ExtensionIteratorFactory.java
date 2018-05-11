/*
    Copyright 2008 Bilal Khan
    grouptheory@gmail.com

    This file is part of MKSolver.

    MKSolver is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    MKSolver is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
*/

package cancellation;

import letter.Letter;
import java.util.LinkedList;
import java.util.Iterator;

class ExtensionIteratorFactory {

    private static ExtensionIteratorFactory _instance;

    private ExtensionIteratorFactory() {
    }

    static ExtensionIteratorFactory instance() {
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
