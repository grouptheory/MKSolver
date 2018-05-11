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

/**
 *
 * @author grouptheory
 */
class ExtensionToVertexSpur extends Extension {
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
