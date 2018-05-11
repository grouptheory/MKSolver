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

/* Class for conjugate gradient method. */

/**
 *
 * @author Daniel Tunkelang, minor edits by Bilal Khan
 */
public class ConjugateGradients extends FirstOrderOptimizationProcedure {

	private double magnitudeOfPreviousGradientSquared;
	private double previousDescentDirection [] [] = null;
	private double restartThreshold = 0;

	public ConjugateGradients (Graph g, ForceModel fm, double acc) {
		super (g, fm, acc); restartThreshold = 0;
	}

	public ConjugateGradients (Graph g, ForceModel fm, double acc, double rt) {
		super (g, fm, acc); restartThreshold = rt;
	}

	public void reset () {negativeGradient = null; descentDirection = null;}

	protected void computeDescentDirection () {
		int n = graph.numberOfVertices, d = graph.getDimensions ();
		double magnitudeOfCurrentGradientSquared = 0;
		if ((descentDirection == null) || (descentDirection.length != n)) {
			descentDirection = new double [n] [d];
			previousDescentDirection = new double [n] [d];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < d; j++) {
					double temp = negativeGradient [i] [j];
					descentDirection [i] [j] = temp;
					magnitudeOfCurrentGradientSquared += square (temp);
				}
			}
		}
		else {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < d; j++) {
					double temp = negativeGradient [i] [j];
					magnitudeOfCurrentGradientSquared += square (temp);
				}
			}
			if (magnitudeOfCurrentGradientSquared < 0.000001) {
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < d; j++) {
						previousDescentDirection [i] [j] = 0;
						descentDirection [i] [j] = 0;
					}
				}
				return;
			}
			double w = magnitudeOfCurrentGradientSquared / magnitudeOfPreviousGradientSquared;
			double dotProduct = 0, magnitudeOfDescentDirectionSquared = 0, m;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < d; j++) {
					descentDirection [i] [j] = negativeGradient [i] [j] +
					                           w * previousDescentDirection [i] [j];
					dotProduct += descentDirection [i] [j] * negativeGradient [i] [j];
					magnitudeOfDescentDirectionSquared += square (descentDirection [i] [j]);
				}
			}
			m = magnitudeOfCurrentGradientSquared * magnitudeOfDescentDirectionSquared;
			if (dotProduct / Math.sqrt (m) < restartThreshold) {
				descentDirection = null; computeDescentDirection (); return;
			}
		}
		magnitudeOfPreviousGradientSquared = magnitudeOfCurrentGradientSquared;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < d; j++) {
				previousDescentDirection [i] [j] = descentDirection [i] [j];
			}
		}
	}
}