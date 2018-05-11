package jigglecore;

/* Class for standard force model of graph-drawing aesthetics. */

/**
 *
 * @author Daniel Tunkelang, minor edits by Bilal Khan
 */
public class StandardForceModel extends ForceModel {

	public StandardForceModel (Graph g, double k, double theta) {
		super (g);
		preferredEdgeLength = k;
		SpringLaw springLaw = new QuadraticSpringLaw (g, k);
		VertexVertexRepulsionLaw vvRepulsionLaw = new HybridVertexVertexRepulsionLaw (g, k);
		addForceLaw (springLaw);
		addForceLaw (vvRepulsionLaw);
		addConstraint (new ProjectionConstraint (g, 2));
	}
}