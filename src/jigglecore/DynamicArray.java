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

/* Methods for manipulating dynamic arrays of JiggleObjects. */

/**
 *
 * @author Daniel Tunkelang, minor edits by Bilal Khan
 */
public abstract class DynamicArray {

	public static Vertex [] add (Vertex [] arr, int size, Vertex elem) {
		if (size == arr.length) {
			Vertex newArr [] = new Vertex [2 * size];
			for (int i = 0; i < size; i++) newArr [i] = arr [i];
			arr = newArr;
		}
		arr [size] = elem; return arr;
	}

	public static Edge [] add (Edge [] arr, int size, Edge elem) {
		if (size == arr.length) {
			Edge newArr [] = new Edge [2 * size];
			for (int i = 0; i < size; i++) newArr [i] = arr [i];
			arr = newArr;
		}
		arr [size] = elem; return arr;
	}

	public static void remove (JiggleObject [] arr, int size, JiggleObject elem)
			throws NotFoundException {
		for (int i = 0; i < size; i++)
			if (arr [i] == elem) {arr [i] = arr [size - 1]; return;}
		throw new NotFoundException ();
	}
}
