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

import java.util.Enumeration;

/**
 *
 * @author Daniel Tunkelang, minor edits by Bilal Khan
 */
public abstract class VertexEdgeRepulsionLaw extends ForceLaw {

	protected double preferredEdgeLength;
	protected double strength = 1;

	protected VertexEdgeRepulsionLaw (Graph g, double k, double s) {
		super (g); preferredEdgeLength = k; strength = s;
	}

	private boolean gridding = false;
	public boolean getGridding () {return gridding;}
	public void setGridding (boolean b) {gridding = b;}

	abstract double pairwiseRepulsion (Cell c1, Cell c2);
	abstract double pairwiseRepulsion (Cell c, double [] coords);
	
	void apply (double [][] negativeGradient) {
		if (gridding) applyUsingGridding (negativeGradient);
		int n = graph.numberOfVertices, m = graph.numberOfEdges;
		int d = graph.getDimensions ();
		for (int i = 0; i < n; i++) {
			Vertex v = graph.vertices [i];
			for (int j = 0; j < m; j++) {
				Edge e = graph.edges [j];
				Vertex from = e.getFrom (), to = e.getTo ();
				computeRepulsion (v, e, negativeGradient);
			}
		}
	}

	private void applyUsingGridding (double [][] negativeGradient) {
		graph.recomputeBoundaries ();
		int n = graph.numberOfVertices, m = graph.numberOfEdges;
		int d = graph.getDimensions ();
		int gridSize [] = new int [d];
		double drawingArea [] = graph.getSize (), k = preferredEdgeLength;
		for (int i = 0; i < d; i++) {
			gridSize [i] = (int) (drawingArea [i] / k) + 1;
		}
		MultidimensionalArray grid = new MultidimensionalArray (d, gridSize);
		double gMin [] = graph.getMin ();
		int index [] = new int [d], sign [] = new int [d];
		for (int i = 0; i < n; i++) {
			Vertex v = graph.vertices [i]; double c [] = v.getCoords ();
			for (int j = 0; j < d; j++) {
				index [j] = (int) ((c [j] - gMin [j]) / k);
			}
			VertexSet gridCell = (VertexSet) grid.get (index);
			if (gridCell == null) grid.set (index, new VertexSet (v));
			else gridCell.add (v);
			v.objectField = index;
		}
		for (int i = 0; i < m; i++) {
			Edge e = graph.edges [i];
			Vertex from = e.getFrom (), to = e.getTo ();
			double fCoords [] = from.getCoords (), tCoords [] = to.getCoords ();
			for (int j = 0; j < d; j++) {
				if (fCoords [j] < tCoords [j]) sign [j] = 1;
				else if (fCoords [j] > tCoords [j]) sign [j] = -1;
				else sign [j] = 0;
			}
			int current [] = (int []) from.objectField;
			int numberOfAdjs = power (3, d);
			boolean flag = true;
			while (flag || (! equal (current, (int []) to.objectField))) {
				flag = false;
				FORLOOP: for (int adj = 0; adj < numberOfAdjs; adj++) {
					int temp = adj;
					for (int j = 0; j < d; j++) {
						index [j] = current [j] + (temp % 3) - 1;
						if ((index [j] < 0) || (index [j] >= gridSize [j]))
							continue FORLOOP;
						temp /= 3;
					}
					VertexSet gridCell = (VertexSet) grid.get (index);
					if ((gridCell != null) && (! gridCell.booleanField)) {
						for (Enumeration en = gridCell.elements ();
						     en.hasMoreElements ();) {
							Vertex v = (Vertex) en.nextElement ();
							computeRepulsion (v, e, negativeGradient);
						}
						gridCell.booleanField = true;
					}
				}
				double time, minTime = Double.MAX_VALUE; int nextAxis = 0;
				for (int axis = 0; axis < d; axis++) {
					if (sign [axis] == 0) continue;
					if (sign [axis] == 1) {
						time = (current [axis] + 1) * k /
						       (tCoords [axis] - fCoords [axis]);
					}
					else {
						time = current [axis] * k /
						       (fCoords [axis] - tCoords [axis]);
					}
					if (time < minTime) {minTime = time; nextAxis = axis;}
				}
				current [nextAxis] += sign [nextAxis];
			}
		}
	}

	private boolean equal (int [] u, int [] v) {
		int d = u.length;
		for (int i = 0; i < d; i++) if (u [i] != v [i]) return false;
		return true;
	}

	private void computeRepulsion (Vertex v, Edge e, double [][] negativeGradient) {
		Vertex from = e.getFrom (), to = e.getTo ();
		if ((from == v) || (to == v)) return;
		int d = v.getDimensions ();
		double vCoords [] = v.getCoords ();
		double fCoords [] = from.getCoords (), tCoords [] = to.getCoords ();
		double dp = 0, lenSquared;
		for (int i = 0; i < d; i++) {
			dp += (vCoords [i] - fCoords [i]) * (tCoords [i] - fCoords [i]);
		}
		if (dp <= 0) computeRepulsion (v, from, negativeGradient);
		else if (dp >= (lenSquared = e.getLengthSquared ()))
			computeRepulsion (v, to, negativeGradient);
		else {
			double len = Math.sqrt (lenSquared), alpha = dp / len;
			double pCoords [] = new double [d];
			for (int i = 0; i < d; i++) {
				pCoords [i] = (1 - alpha) * fCoords [i] + alpha * tCoords [i];
			}
			double w = Math.min (strength * pairwiseRepulsion (v, pCoords),
			                     cap / Vertex.getDistance (v, pCoords));
			if (w == 0) return;
			double vWeight = v.getWeight ();
			double fWeight = from.getWeight (), tWeight = to.getWeight ();
			for (int i = 0; i < d; i++) {
				double force1 = (vCoords [i] - fCoords [i]) * w * (1 - alpha);
				double force2 = (vCoords [i] - tCoords [i]) * w * alpha;
				negativeGradient [v.intField] [i] += force1 * fWeight;
				negativeGradient [from.intField] [i] -= force1 * vWeight;
				negativeGradient [v.intField] [i] += force2 * tWeight;
				negativeGradient [to.intField] [i] -= force2 * vWeight;
			}
		} 
	}

	private void computeRepulsion (Vertex v1, Vertex v2, double [][] negativeGradient) {
		int d = v1.getDimensions ();
		double w = Math.min (strength * pairwiseRepulsion (v1, v2),
		                     cap / Vertex.getDistance (v1, v2));
		if (w == 0) return;
		double v1Coords [] = v1.getCoords (), weight1 = v1.getWeight ();
		double v2Coords [] = v2.getCoords (), weight2 = v2.getWeight ();
		for (int i = 0; i < d; i++) {
			double force = (v1Coords [i] - v2Coords [i]) * w;
			negativeGradient [v1.intField] [i] += force * weight2;
			negativeGradient [v2.intField] [i] -= force * weight1;
		}
	}
}
