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

package ge;

import letter.Letter;
import letter.Constant;
import letter.Variable;
import letter.LetterFactory;
import equation.GroupWord;
import equation.QuadraticSystem;
import cancellation.ICancellationDiagramAnalysis;
import cancellation.CancellationDiagramFactory;
import cancellation.Diagram;
import cancellation.DiagramTreeNode;
import java.util.Iterator;

/**
 *
 * @author grouptheory
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Constant a = LetterFactory.instance().getConstant(1, true);
        Constant b = LetterFactory.instance().getConstant(2, true);
        Constant A = (Constant)a.getInverse();
        Constant B = (Constant)b.getInverse();
        
        Variable z1 = LetterFactory.instance().getVariable(1, true);
        Variable z2 = LetterFactory.instance().getVariable(2, true);

        GE ge1 = new GE();
        Boundary b0 = ge1.getFirstBoundary();
        Boundary b1 = ge1.appendNewBoundary();
        Boundary b2 = ge1.appendNewBoundary();
        Boundary b3 = ge1.appendNewBoundary();
        Base c1 = ge1.addNewConstantBase(b0, a);
        Base c2 = ge1.addNewConstantBase(b1, b);
        Base c3 = ge1.addNewConstantBase(b2, A);
        Base c4 = ge1.addNewConstantBase(b3, B);
        Base v1 = ge1.addNewVariableBase(b0, b2, z1, b1, b3);
        Base v2 = ge1.addNewVariableBase(b0, b1, z2, b3, b2);

        ge1.addNewConstraint(v1, b1, b2);
        
        System.out.println("GE#1:\n");
        System.out.println(""+ge1+"\n");

        GE ge2 = ge1.duplicate();

        System.out.println("GE#1:\n");
        System.out.println(""+ge1+"\n");

        System.out.println("GE#2:\n");
        System.out.println(""+ge2+"\n");



        
        System.out.println("\nTEST Real-world EQUATION TEST:\n");

        GroupWord prob = new GroupWord("z1+.c1+.z1+.c2+.z1-.");
        System.out.println("Original Equation: "+prob+" = 1\n");

        ICancellationDiagramAnalysis analysis =
                CancellationDiagramFactory.instance().newDiagramTree(prob);

        System.out.println("Analysis: \n\n");
        QuadraticSystem qs = analysis.getQuadraticSystem();
        System.out.println(qs);

        Iterator it = analysis.iteratorDiagramTreeNodes();
        DiagramTreeNode dtn = (DiagramTreeNode)it.next();
        Diagram d = dtn.getDiagram();

        System.out.println("Diagram:\n");
        System.out.println(""+d+"\n\n");
        
        GEFactory gef = GEFactory.instance();
        GE ge3 = gef.newGE(d, qs);

        System.out.println("GE#3:\n");
        System.out.println(""+ge3+"\n");
        
        GE ge4 = ge3.duplicate();

        System.out.println("GE#4:\n");
        System.out.println(""+ge4+"\n");
    }
}
