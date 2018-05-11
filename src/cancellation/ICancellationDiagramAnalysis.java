/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cancellation;

import java.util.TreeSet;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Iterator;
import letter.Letter;
import equation.GroupEquation;
import equation.QuadraticSystem;
/**
 *
 * @author grouptheory
 */
public interface ICancellationDiagramAnalysis {

    public void addDecorator(ICancellationDiagramAnalysisDecorator dec);
    public Iterator iteratorDecorators();
    
    public Iterator iteratorDiagramTreeNodes();
    
    public GroupEquation getProblem();
    public QuadraticSystem getQuadraticSystem();
    public GroupEquation getQuadraticEquation() ;
    public HashMap getEquivalences();
    public String toString();
}
