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

class ExtensionToEdgeExact extends Extension {
    private static String NAME = "ExtensionToEdgeExact";
    private Edge _srcEdge;

    ExtensionToEdgeExact(Letter label, Edge src) {
        super(label);
        _srcEdge = src;
    }

    Diagram apply(Diagram d) {
        Diagram d2 = new Diagram(d);
        Node end = d2.getEnd();
        Edge src = d2.lookupEdge(_srcEdge.getA().getID(), _srcEdge.getB().getID());
        Node cutNode = d2.cutEdge(src);

        BFS bfs = new BFS(d2, end);
        Path p = bfs.getPathFrom(cutNode);
        LabeledPath lp = new LabeledPath(_label, p);
        d2.addLabeledPath(lp);
        return d2;
    }

    public String toString() {
         return ""+NAME+"->"+_srcEdge+"";
    }
}
