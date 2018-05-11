package jigglecore;

import java.util.Vector;
import java.util.Enumeration;

/**
 *
 * @author Daniel Tunkelang, minor edits by Bilal Khan
 */
public class VertexSet extends JiggleObject {

	private Vector vertices;

	VertexSet () {vertices = new Vector ();}

	VertexSet (Vertex v) {vertices = new Vector (); vertices.addElement (v);}

	void add (Vertex v) {vertices.addElement (v);}

	Enumeration elements () {return vertices.elements ();}
}