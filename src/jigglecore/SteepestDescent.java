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

/* Class for method of steepest descent. */

/**
 *
 * @author Daniel Tunkelang, minor edits by Bilal Khan
 */
public class SteepestDescent extends FirstOrderOptimizationProcedure{

	public SteepestDescent (Graph g, ForceModel fm, double accuracy) {
		super (g, fm, accuracy);
	}

	protected void computeDescentDirection () {
		int n = graph.numberOfVertices, d = graph.getDimensions ();
		if ((descentDirection == null) || (descentDirection.length != n))
			descentDirection = new double [n] [d];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < d; j++) {
				descentDirection [i] [j] = negativeGradient [i] [j];
			}
		}
	}
}