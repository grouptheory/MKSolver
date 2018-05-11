/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cancellation;

import letter.Letter;
import java.util.Iterator;

/**
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

    public int getEdgeIndex(Edge e) {
        return _path.getEdgeIndex(e);
    }

    Node getNewEnd() {
        return _path.getSrcNode();
    }

    public Path getPath() {
        return _path;
    }

    public Letter getLabel() {
        return _label;
    }
    
    Iterator iteratorEdges() {
        return _path.iteratorEdges();
    }

    public String toString() {
        String s = "{ "+_label+" :: "+_path+" }";
        return s;
    }

    void validate(Diagram d) {
        _path.validate(d);
    }
}
