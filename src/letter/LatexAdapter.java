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

package letter;

/**
 * Converts instances of Letter to latex formatted text.
 *
 * @author grouptheory
 */
public class LatexAdapter {

    private static LatexAdapter _instance;

    private LatexAdapter() {
    }

    /**
     * Returns the singleton instance of the LatexAdapter class.
     * @return LatexAdapter instance.
     */
    public static LatexAdapter instance() {
        if (_instance == null) {
            _instance = new LatexAdapter();
        }
        return _instance;
    }

    /**
     * Convert a letter into Latex, placing the ID of the letter in the subscript,
     * and the exponent as a superscript.  Constants are rendered as the symbol c,
     * while Variables are rendered as the symbol z.
     * 
     * @param let a Letter
     * @return a String
     */
    public String render(Letter let) {
        String s = "";
        if (let.isConstant()) s+= this.renderConstant((Constant)let);
        else s+= this.renderVariable((Variable)let);
        return s;
    }

    private String renderVariable(Variable v) {
        String s = "";
        s += "z_{";
        s += ""+v.getID()+"}";
        if ( ! v.isPositive()) {
            s += "^{-1}";
        }
        return s;
    }

    private String renderConstant(Constant c) {
        String s = "";
        s += "c_";
        s += "{"+c.getID()+"}";
        if ( ! c.isPositive()) {
            s += "^{-1}";
        }
        return s;
    }
}
