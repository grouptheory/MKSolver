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