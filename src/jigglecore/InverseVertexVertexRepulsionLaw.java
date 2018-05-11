package jigglecore;

/**
 *
 * @author Daniel Tunkelang, minor edits by Bilal Khan
 */
public class InverseVertexVertexRepulsionLaw extends VertexVertexRepulsionLaw {

	public InverseVertexVertexRepulsionLaw (Graph g, double k) {
		super (g, k);
	}

	double pairwiseRepulsion (Cell c1, Cell c2) {
		double k = preferredEdgeLength + Cell.sumOfRadii (c1, c2);
		return k * k / Cell.getDistanceSquared (c1, c2);
	}
}