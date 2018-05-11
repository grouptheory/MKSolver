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