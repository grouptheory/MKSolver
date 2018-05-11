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
public class MultidimensionalArray extends JiggleObject {

	private int dimensions = 0;
	private int size [] = null;
	private int numberOfCells = 0;
	private Object cells [] = null;

	MultidimensionalArray (int d, int [] s) {
		dimensions = d; size = new int [d]; numberOfCells = 1;
		for (int i = 0; i < d; i++) {
			numberOfCells *= (size [i] = s [i]);
		}
		cells = new Object [numberOfCells];
	}

	int getDimensions () {return dimensions;}

	Object get (int [] index) {return cells [rankOf (index)];}

	void set (int [] index, Object obj) {cells [rankOf (index)] = obj;}

	private int rankOf (int [] index) {
		int rank = 0, column = 1;
		for (int i = 0; i < dimensions; i++) {
			rank += index [i] * column;
			column *= size [i];
		}
		return rank;
	}
}
