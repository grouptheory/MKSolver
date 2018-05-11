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

import java.util.TreeSet;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Iterator;
import letter.Letter;
import equation.GroupWord;
import equation.QuadraticSystem;
import equation.Equivalences;
/**
 * An analysis of all the cancellation Diagrams of a given
 * (not necessarily quadratic) equation.
 *
 * @author grouptheory
 */
public interface ICancellationDiagramAnalysis {

    /**
     * Add a decorator to the Analysis
     *
     * @param dec an ICancellationDiagramAnalysisDecorator.
     */
    public void addDecorator(ICancellationDiagramAnalysisDecorator dec);
    /**
     * Get an Iterator over all ICancellationDiagramAnalysisDecorators
     * associated with this analysis.
     *
     * @return an Iterator.
     */
    public Iterator iteratorDecorators();
    
    /**
     * Get an Iterator over all TreeNodes
     * associated with this analysis.
     *
     * @return an Iterator.
     */
    public Iterator iteratorDiagramTreeNodes();
    
    /**
     * Get the underlying equation associated with this analysis.
     *
     * @return a GroupWord (which is assumed to =1 in F).
     */
    public GroupWord getProblem();

    /**
     * Get the QuadraticSystem associated with this analysis.
     * Every equation can be turned into a QuadraticSystem.
     * 
     * @return the QuadraticSystem.
     */
    public QuadraticSystem getQuadraticSystem();

    /**
     * Get the main quadratic equation of the QuadraticSystem
     * associated with this analysis.
     *
     * @return a quadratic GroupWord.
     */
    public GroupWord getQuadraticEquation() ;

    /**
     *
     * Get the variable equivalences of the QuadraticSystem
     * associated with this analysis.
     *
     * @return an Equivalences object.
     */
    public Equivalences getEquivalences();

    /**
     * Get the String representation of this ICancellationDiagramAnalysis.
     *
     * @return a String.
     */
    public String toString();
}
