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
import java.util.Iterator;

/**
 * A path in the cancellation Diagram, together with its label.
 * 
 * @author grouptheory
 */
public class LabeledPath {
    private Path _path;
    private Letter _label;

    LabeledPath(Letter label, Path path) {
        _label = label;
        _path = path;
    }

    static LabeledPath project(LabeledPath lp, Diagram d2) {
        LabeledPath lp2 = new LabeledPath(lp._label, lp._path, d2);
        return lp2;
    }

    private LabeledPath(Letter label, Path path, Diagram d2) {
        _label = label;
        _path = Path.project(path, d2);
    }

    /**
     * Get the length of this LabeledPath in Edges.
     *
     * @return the integer length.
     */
    public int length() {
        return _path.length();
    }

    void cutEdge(Edge e, Node insert) {
        if (this.hasEdge(e)) {
            _path.cutEdge(e, insert);
        }
    }

    boolean hasEdge(Edge e) {
        return _path.hasEdge(e);
    }

    /**
     * Get the index of a given Edge within this LabeledPath.
     * 
     * @param e an Edge.
     * @return its integer index within this LabeledPath.
     */
    public int getEdgeIndex(Edge e) {
        return _path.getEdgeIndex(e);
    }

    Node getNewEnd() {
        return _path.getSrcNode();
    }

    /**
     * Get the Path associated with this LabeledPath.
     * 
     * @return the Path.
     */
    public Path getPath() {
        return _path;
    }

    /**
     * Get the Label associated with this LabeledPath.
     * 
     * @return a Letter.
     */
    public Letter getLabel() {
        return _label;
    }
    
    Iterator iteratorEdges() {
        return _path.iteratorEdges();
    }

    /**
     * Get the String representation of this LabeledPath.
     *
     * @return a String.
     */
    public String toString() {
        String s = "{ "+_label+" :: "+_path+" }";
        return s;
    }

    void validate(Diagram d) {
        _path.validate(d);
    }
}
