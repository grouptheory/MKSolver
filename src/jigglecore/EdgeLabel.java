package jigglecore;

/* Class for edge labels. */

/**
 *
 * @author Daniel Tunkelang, minor edits by Bilal Khan
 */
public class EdgeLabel extends Cell {

	String name;

	EdgeLabel (Edge e, String str) {setContext (e); name = str;}

	String getName () {return name;}
	void setName (String str) {name = str;}

	public String toString () {return "(EdgeLabel: " + name + ")";}
}