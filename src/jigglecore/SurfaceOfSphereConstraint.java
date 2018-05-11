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
public class SurfaceOfSphereConstraint extends Constraint {

	private double radius;

	public SurfaceOfSphereConstraint (Graph g) {
		super (g); radius = 0;
	}

	public SurfaceOfSphereConstraint (Graph g, double r) {
		super (g); radius = r;
	}
	
	void apply (double [][] penalty) {
		int d = graph.getDimensions ();
		int n = graph.numberOfVertices;
		double center [] = new double [d], sum [] = new double [d];
		for (int i = 0; i < d; i++) center [i] = sum [i] = 0;
		for (int i = 0; i < n; i++) {
			double coords [] = graph.vertices [i].getCoords ();
			for (int j = 0; j < d; j++) center [j] += coords [j] / n;
		}
		double r = radius;
		if (r == 0) {
			for (int i = 0; i < n; i++) {
				double coords [] = graph.vertices [i].getCoords ();
				double distanceSquared = 0;
				for (int j = 0; j < d; j++) {
					distanceSquared += square (coords [j] - center [j]);
				}
				r += Math.sqrt (distanceSquared);
			}
			r = r / n;
		}

		for (int i = 0; i < n; i++) {
			double coords [] = graph.vertices [i].getCoords ();
			double distanceSquared = 0;
			for (int j = 0; j < d; j++) {
				distanceSquared += square (coords [j] - center [j]);
			}
			double p = r - Math.sqrt (distanceSquared);
			for (int j = 0; j < d; j++) {
				penalty [i] [j] += p * (coords [j] - center [j]);
				sum [j] += p * (coords [j] - center [j]);
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < d; j++) {
				penalty [i] [j] -= sum [j] / n;
			}
		}

	}
}

