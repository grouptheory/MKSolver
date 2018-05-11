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

/**
 *
 * @author Daniel Tunkelang, minor edits by Bilal Khan
 */
public class QuadTree extends Cell {

	QuadTree subtrees [];

	double force [];

	QuadTree (Graph g) {
		setContext (g); objectField = null;
		int d = g.getDimensions (); setDimensions (d);
		subtrees = new QuadTree [power (2, d)];
		int n = g.numberOfVertices;
		setMin (g.getMin ()); setMax (g.getMax ());
		for (int i = 0; i < n; i++)
			g.vertices [i].objectField = null;
		for (int i = 0; i < n; i++)
			insert (g.vertices [i]);
		force = new double [d]; for (int i = 0; i < d; i++) force [i] = 0;
	}

	private QuadTree (double [] min, double [] max, QuadTree p) {
		setContext (p); objectField = null;
		int d = p.getDimensions (); setDimensions (d);
		subtrees = new QuadTree [power (2, d)];
		setMin (min); setMax (max);
		force = new double [d]; for (int i = 0; i < d; i++) force [i] = 0;
	}

	QuadTree lookUp (Vertex v) {
		if (objectField == v) return this;
		else if (objectField != null) return null;
		else return subtrees [getIndex (v)].lookUp (v);
	}

	private int getIndex (Vertex v) {
		double c [] = v.getCoords (), center [] = getCenter ();
		int d = getDimensions (), index = 0, column = 1;
		for (int i = 0; i < d; i++) {
			if (c [i] > center [i]) index += column;
			column *= 2;
		}
		return index;
	}

	private double [] getCenter () {
		int d = getDimensions ();
		double mp [] = new double [d];
		double lo [] = getMin (), hi [] = getMax ();
		for (int i = 0; i < d; i++) mp [i] = (lo [i] + hi [i]) / 2;
		return mp;
	}

	protected void recomputeSize () {}
	void recomputeBoundaries () {}
	/* NOTE: Size for quadtrees has nothing to do with min and max! It stores
	the average size of the vertices that have been inserted into the tree. */ 

	void insert (Vertex v) {
		double w = getWeight (), vw = v.getWeight ();
		int d = getDimensions ();
		double vCoords [] = v.getCoords (), vSize [] = v.getSize ();
		if (w == 0) {
			v.setContext (this); setWeight (v.getWeight ());
			setCoords (vCoords); setSize (v.getSize ());
			objectField = v; return;
		}
		if (objectField != null) splitCell ();
		double c [] = getCoords (), s [] = getSize ();
		for (int i = 0; i < d; i++) {
			c [i] = (c [i] * w + vCoords [i] * vw) / (w + vw);
			s [i] = (s [i] * w + vSize [i] * vw) / (w + vw);
		}
		setWeight (w + vw);
		subtrees [getIndex (v)].insert (v);
	}

	private void splitCell () {
		Vertex v = (Vertex) objectField;
		objectField = null;
		double cellMin [] = getMin (), cellMax [] = getMax ();
		double center [] = getCenter ();
		int d = getDimensions (), n = power (2, d);
		double lo [] = new double [d], hi [] = new double [d];
		for (int index = 0; index < n; index++) {
			int column = 1;
			for (int i = 0; i < d; i++) {
				if ((index & column) > 0) {
					lo [i] = center [i]; hi [i] = cellMax [i];
				}
				else {lo [i] = cellMin [i]; hi [i] = center [i];}
				column *= 2;
			}
			subtrees [index] = new QuadTree (lo, hi, this);
		}
		subtrees [getIndex (v)].insert (v);
	}
}

