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

package jigglecore;

/* Class for vertices of a graph. */

/**
 *
 * @author Daniel Tunkelang, minor edits by Bilal Khan
 */
public class Vertex extends Cell {

	int undirectedDegree = 0, inDegree = 0, outDegree = 0;
	Edge undirectedEdges [] = new Edge [1];
	Edge         inEdges [] = new Edge [1];
	Edge        outEdges [] = new Edge [1];
	Vertex undirectedNeighbors [] = new Vertex [1];
	Vertex         inNeighbors [] = new Vertex [1];
	Vertex        outNeighbors [] = new Vertex [1];

	/* NOTE: the above are made package-accessible for reasons of
	efficiency.  They should NOT, however, be modified except by
	insertNeighbor and deleteNeighbor methods below. */

	private String name = ""; /* name of vertex */
	private boolean fixed = false; /* is the vertex anchored? */

	Vertex (Graph g) {
		super ();
		setContext (g); setWeight (1); setDimensions (g.getDimensions ());
	}

	String getName () {return name;}
	void setName (String str) {name = str;}

	boolean getFixed () {return fixed;}
	void setFixed (boolean f) {fixed = f;}

	void insertNeighbor (Edge e) {
		Vertex from = e.getFrom (), to = e.getTo ();
		Vertex v = null;
		if (this == from) v = to; else if (this == to) v = from;
		else throw new Error (e + " not incident to " + this);
		if (! e.getDirected ()) {
			undirectedEdges = DynamicArray.add
				(undirectedEdges, undirectedDegree, e);
			undirectedNeighbors = DynamicArray.add
				(undirectedNeighbors, undirectedDegree++, v);
		}
		else if (this == from) {
			outEdges = DynamicArray.add (outEdges, outDegree, e);
			outNeighbors = DynamicArray.add
				(outNeighbors, outDegree++, to);
		}
		else {
			inEdges = DynamicArray.add (inEdges, inDegree, e);
			inNeighbors = DynamicArray.add
				(inNeighbors, inDegree++, from);
		}
	}

	void deleteNeighbor (Edge e) {
		Vertex from = e.getFrom (), to = e.getTo ();
		Vertex v = null;
		if (this == from) v = to; else if (this == to) v = from;
		else throw new Error (e + " not incident to " + this);
		try {
			if (! e.getDirected ()) {
				DynamicArray.remove
					(undirectedEdges, undirectedDegree, e);
				DynamicArray.remove
					(undirectedNeighbors, undirectedDegree--, v);
			}
			else if (this == from) {
				DynamicArray.remove (outEdges, outDegree, e);
				DynamicArray.remove (outNeighbors, outDegree--, to);
			}
			else {
				DynamicArray.remove (inEdges, inDegree, e);
				DynamicArray.remove (inNeighbors, inDegree--, from);
			}
		} catch (NotFoundException exc) {
			throw new Error (e + " not incident to " + this);
		}
	}

	public String toString () {return "(Vertex: " + name + ")";}
}