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
public class ProjectionConstraint extends Constraint {

	private int dimensions = 0;

	public ProjectionConstraint (Graph g, int d) {
		super (g); dimensions = d;
	}
	
	void apply (double [][] penalty) {
		int d = graph.getDimensions ();
		int n = graph.numberOfVertices;
		for (int i = 0; i < n; i++) {
			double coords [] = graph.vertices [i].getCoords ();
			for (int j = dimensions; j < d; j++) {
				penalty [i] [j] += (- coords [j]);
			}
		}
	}
}

