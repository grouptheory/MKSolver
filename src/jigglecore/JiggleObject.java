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

/* Abstract base class for all JIGGLE objects. */

/**
 *
 * @author Daniel Tunkelang, minor edits by Bilal Khan
 */
public abstract class JiggleObject {

	private JiggleObject context = null;
	public JiggleObject getContext () {return context;}
	protected void setContext (JiggleObject c) {context = c;}

	/* The context of a JiggleObject identifies the parent JiggleObject
	(if any) that contains it.  The context of a Vertex or Cell is either
	a Graph or a Cell; the context of an Edge is a Graph; the context of
	an EdgeLabel is an Edge.  For now, we assume that the	context of a
	Graph is null; if, however, we extend the present implementation to
	include composite graphs, then the context of a Graph	could be a
	JiggleObject (e.g. a Vertex) that contains the graph inside	it. */

	boolean booleanField = false;
	int intField = 0;
	Object objectField = null;

	static double square (double d) {return d * d;}
	static double cube (double d) {return d * d * d;}
	static int intSquare (int n) {return n * n;}

	static int power (int base, int d) {
		if (d == 0) return 1;
		else if (d == 1) return base;
		else if (d % 2 == 0) return intSquare (power (base, d / 2));
		else return base * intSquare (power (base, d / 2));
	}
}