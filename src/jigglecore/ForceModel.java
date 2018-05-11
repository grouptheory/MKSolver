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

import java.util.Vector;
import java.util.Enumeration;

/**
 *
 * @author Daniel Tunkelang, minor edits by Bilal Khan
 */
public class ForceModel {

	protected Graph graph = null;
	protected double preferredEdgeLength;

	private Vector forceLaws = new Vector ();
	private Vector constraints = new Vector ();

	public ForceModel (Graph g) {graph = g;}

	double getPreferredEdgeLength () {return preferredEdgeLength;}
	void setPreferredEdgeLength (double k) {preferredEdgeLength = k;}

	public void addForceLaw (ForceLaw fl) {forceLaws.addElement (fl);}
	public void removeForceLaw (ForceLaw fl) {forceLaws.removeElement (fl);}

	public void addConstraint (Constraint c) {constraints.addElement (c);}
	public void removeConstraint (Constraint c) {constraints.removeElement (c);}
	
	void getNegativeGradient (double [] [] negativeGradient) {
		int n = graph.numberOfVertices, d = graph.getDimensions ();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < d; j++) {
				negativeGradient [i] [j] = 0;
			}
			graph.vertices [i].intField = i;
		}
		for (Enumeration en = forceLaws.elements (); en.hasMoreElements ();)
			((ForceLaw) (en.nextElement ())).apply (negativeGradient);
	}

	void getPenaltyVector (double [] [] penaltyVector) {
		int n = graph.numberOfVertices, d = graph.getDimensions ();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < d; j++) {
				penaltyVector [i] [j] = 0;
			}
			graph.vertices [i].intField = i;
		}
		for (Enumeration en = constraints.elements (); en.hasMoreElements ();)
			((Constraint) (en.nextElement ())).apply (penaltyVector);
	}
}