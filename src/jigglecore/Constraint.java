package jigglecore;

/**
 *
 * @author Daniel Tunkelang, minor edits by Bilal Khan
 */
public abstract class Constraint extends JiggleObject {

	protected Graph graph;

	protected Constraint (Graph g) {graph = g;}

	abstract void apply (double [][] penalty);
}

