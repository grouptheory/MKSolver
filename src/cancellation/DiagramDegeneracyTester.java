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

package cancellation;

/**
 * Tests a Diagram for degeneracy.
 * 
 * @author grouptheory
 */
public class DiagramDegeneracyTester {

    /**
     * Determines if a Diagram is degenerate.  At present
     * a Diagram is degenerate if it is not closed.
     *
     * @param d a Diagram.
     * @return true iff d is degenerate.
     */
    public static boolean isDegenerate(Diagram d) {

        if ( ! d.isClosed()) return true;
        else {
            return false;
        }
    }

}
