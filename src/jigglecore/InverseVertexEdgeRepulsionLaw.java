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
public class InverseVertexEdgeRepulsionLaw extends VertexEdgeRepulsionLaw {

	public InverseVertexEdgeRepulsionLaw (Graph g, double k) {
		super (g, k, 1);
	}

	public InverseVertexEdgeRepulsionLaw (Graph g, double k, double s) {
		super (g, k, s);
	}

	double pairwiseRepulsion (Cell c1, Cell c2) {
		double k = preferredEdgeLength + Cell.sumOfRadii (c1, c2);
		double dSquared = Cell.getDistanceSquared (c1, c2);
		if (dSquared >= square (k)) return 0; 
		else return k * k / dSquared - k / Math.sqrt (dSquared);
	}

	double pairwiseRepulsion (Cell cell, double [] coords) {
		double k = preferredEdgeLength + Cell.radius (cell, coords);
		double dSquared = Cell.getDistanceSquared (cell, coords);
		if (dSquared >= square (k)) return 0; 
		else return k * k / dSquared - k / Math.sqrt (dSquared);
	}
}