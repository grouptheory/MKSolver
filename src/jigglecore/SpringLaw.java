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
public abstract class SpringLaw extends ForceLaw {

	protected double preferredEdgeLength;

	protected SpringLaw (Graph g, double k) {
		super (g); preferredEdgeLength = k;
	}

	void apply (double [][] negativeGradient) {
		int m = graph.numberOfEdges, d = graph.getDimensions ();
		for (int i = 0; i < m; i++) {
			Edge e = graph.edges [i];
			Vertex from = e.getFrom (), to = e.getTo ();
			double fromWeight = from.getWeight (), toWeight = to.getWeight ();
			int f = from.intField, t = to.intField;
			double w = Math.min (springAttraction (e), cap / e.getLength ());
			double fromCoords [] = from.getCoords ();
			double toCoords [] = to.getCoords ();
			for (int j = 0; j < d; j++) {
				double force = (toCoords [j] - fromCoords [j]) * w;
				negativeGradient [f] [j] += force * toWeight;
				negativeGradient [t] [j] -= force * fromWeight;
			}
		}
	}

	abstract double springAttraction (Edge e);
}

