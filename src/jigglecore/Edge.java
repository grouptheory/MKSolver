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

/* Class for edges of a graph.  NOTE: the only mutable characteristics
of an edge are its label, directedness, and preferred length. */

/**
 *
 * @author Daniel Tunkelang, minor edits by Bilal Khan
 */
public class Edge extends JiggleObject {

	private Vertex from, to; /* endpoints of the edge */
	private EdgeLabel label = null; /* label of edge */
	private boolean directed = false; /* is the edge directed? */
	private double preferredLength = 0; /* preferred length of edge */

	Edge (Graph g, Vertex f, Vertex t) {from = f; to = t; setContext (g);}

	Edge (Graph g, Vertex f, Vertex t, boolean dir) {
		from = f; to = t; setContext (g); directed = dir;
	}

	public Vertex getFrom () {return from;}
	public Vertex getTo () {return to;}

	EdgeLabel getLabel () {return label;}
	void setLabel (EdgeLabel lbl) {label = lbl;}

	boolean getDirected () {return directed;}
	void setDirected (boolean d) {directed = d;}

	double getPreferredLength () {return preferredLength;}
	public void setPreferredLength (double len) {preferredLength = len;}

	double getLengthSquared () {return Vertex.getDistanceSquared (from, to);}
	double getLength () {return Vertex.getDistance (from, to);}

	public String toString () {
		return "(Edge: " + from + ", " + to + ", " +
			 (directed ? "directed" : "undirected") + ")";
	}
}