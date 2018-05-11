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

import jigglecore.*;
import java.util.Iterator;
import java.util.HashMap;

/**
 *
 * @author grouptheory
 */
class DiagramDecoratorLayout extends Graph implements Diagram.Decorator {

    DiagramDecoratorLayout (Diagram cd) {super (); initialize (cd);}
    DiagramDecoratorLayout (Diagram cd, int d) {super (d); initialize (cd);}

    private void initialize (Diagram cd) {
        int n = cd.getNumberOfNodes();

        // System.out.println("DEBUG DiagramDecoratorLayout n="+n);

        Vertex V [] = new Vertex [n];
        for (int i=0;i<n;i++) {
            V[i] = this.insertVertex();
        }

        // System.out.println("DEBUG DiagramDecoratorLayout e="+cd.getNumberOfEdges());

        for (Iterator it = cd.iteratorEdges(); it.hasNext();) {
            cancellation.Edge e =
                    (cancellation.Edge)it.next();
            insertEdge (V [e.getA().getID()], V [e.getB().getID()]);
        }

        // System.out.println("BEFORE: "+this.toString());
        _vertices = layout(cd);
        // System.out.println("AFTER: "+this.toString());
        // System.out.println("AFTER: "+this.validate(cd));
    }

    private Vertex getVertex(Node nd) {
        Vertex v = (Vertex)_vertices.get(nd.getID());
        if (v==null) {
            throw new RuntimeException("DiagramLayout.getVertex: unknown Node "+nd);
        }
        return v;

    }
    
    double getX(Node nd) {
        Vertex v = getVertex(nd);
        return v.getCoords()[0];
    }

    double getY(Node nd) {
        Vertex v = getVertex(nd);
        return v.getCoords()[1];
    }
    
    private HashMap _vertices;
    
    private final static double LAYOUT_SIDE = 6.0;
    private final static double MAX_ERROR = 0.1;

    private HashMap layout(Diagram cd) {

      _vertices = new HashMap();

      int crossings = 0;

      do {
          _vertices.clear();

          int d = this.getDimensions ();
          int n = this.numberOfVertices;
          
          double kspring = 10.0;
          double kvv = 1.0;
          double kve = 1.0;

          QuadraticSpringLaw springLaw = 
                  new QuadraticSpringLaw (this, kspring);
          InverseSquareVertexVertexRepulsionLaw vvRepulsionLaw = 
                  new InverseSquareVertexVertexRepulsionLaw (this, kvv);
          InverseSquareVertexEdgeRepulsionLaw veRepulsionLaw =
                  new InverseSquareVertexEdgeRepulsionLaw (this, kve);

          ForceModel fm = new ForceModel(this);
          fm.addForceLaw (springLaw);
          fm.addForceLaw (vvRepulsionLaw);
          fm.addForceLaw (veRepulsionLaw);

          // make optimization procedure

          FirstOrderOptimizationProcedure opt = null;
          double acc = 0.5;
          double rt = 0.2;
          opt = new ConjugateGradients (this, fm, acc, rt);
          opt.setConstrained (true);

          // set vertex sizes

          for (int i = 0; i < n; i++) {
              double size [] = this.vertices [i].getSize ();
              size [0] = 10;
              size [1] = 10;
          }

          // scramble vertices

          double w = LAYOUT_SIDE,  h = LAYOUT_SIDE;
          for (int i = 0; i < n; i++) {
              double coords [] = this.vertices [i].getCoords ();
              for (int j = 0; j < d; j++) coords [j] = Math.random () * w;
          }
          double sumX = 0, sumY = 0;
          for (int i = 0; i < n; i++) {
              double coords [] = this.vertices [i].getCoords ();
              sumX += coords [0]; sumY += coords [1];
          }
          for (int i = 0; i < n; i++) {
              Vertex v = this.vertices [i];
              double coords [] = this.vertices [i].getCoords ();
              coords [0] += (w / 2) - (sumX / n);
              coords [1] += (h / 2) - (sumY / n);
          }

          // Optimization here

          int iter = 0;
          double val = 999.0;
          do {
              val = opt.improveGraph();
              // System.out.println("iter:"+iter+", err = "+val);
              iter++;
          }
          while(val > MAX_ERROR);

          // renormalize vertex coordinates

          double minx=999, miny=999, maxx=-999, maxy=-999;

          for (int i = 0; i < n; i++) {
              double coords [] = this.vertices [i].getCoords ();
              if (coords [0]>maxx) maxx=coords[0];
              if (coords [1]>maxy) maxy=coords[1];
              if (coords [0]<minx) minx=coords[0];
              if (coords [1]<miny) miny=coords[1];
          }

          for (int i = 0; i < n; i++) {
              double coords [] = this.vertices [i].getCoords ();
              coords[0] = (coords[0]-minx) * LAYOUT_SIDE/(maxx-minx);
              coords[1] = (coords[1]-miny) * LAYOUT_SIDE/(maxy-miny);
              _vertices.put(new Integer(i), this.vertices [i]);
          }

          crossings = Intersector.crossingNumber(this);

          /*
          if (crossings == 0) {
              System.out.println("Diagram "+cd.toString());
              System.out.println(this.validate(cd));
              System.out.println("Converged after "+iter+" iterations, err = "+val+", crossings = "+crossings);
          }
          */
      }
      while (crossings > 0);

      return _vertices;
    }

    public String toString() {
        String s = "";
        int n = this.numberOfVertices;
        for (int i = 0; i < n; i++) {
           double coords [] = this.vertices [i].getCoords ();
           s += "Vertex "+i+" @ "+coords[0]+" , "+coords[1]+"\n";
        }
        return s;
    }


    String validate(Diagram d) {
        String s = "";
        for (Iterator it = d.iteratorNodes(); it.hasNext();) {
            Node nd = (Node)it.next();
            s += "Node "+nd.getID()+" @ "+getX(nd)+" , "+this.getY(nd)+"\n";
        }
        return s;
    }
}
