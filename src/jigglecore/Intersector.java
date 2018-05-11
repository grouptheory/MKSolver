/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jigglecore;

/**
 *
 * @author Bilal Khan
 */
public class Intersector {

public static int crossingNumber(Graph g) {
    int crossings = 0;

    // System.out.println("DEBUG crossingNumber over numberOfEdges="+g.numberOfEdges);

    for (int i=0; i<g.numberOfEdges; i++) {
        for (int j=i+1; j<g.numberOfEdges; j++) {
            if (intersect(g.edges[i], g.edges[j])) {
                crossings++;
            }
        }
    }
    return crossings;
}


static boolean intersect(Edge this_line, Edge other_line) {

    double Ax=this_line.getFrom().getCoords()[0];
    double Ay=this_line.getFrom().getCoords()[1];
    double Bx=this_line.getTo().getCoords()[0];
    double By=this_line.getTo().getCoords()[1];

    double Cx=other_line.getFrom().getCoords()[0];
    double Cy=other_line.getFrom().getCoords()[1];
    double Dx=other_line.getTo().getCoords()[0];
    double Dy=other_line.getTo().getCoords()[1];

    boolean xxx = intersect(Ax, Ay, Bx, By, Cx, Cy, Dx, Dy);

    /*
    System.out.print("("+Ax+","+Ay+")-");
    System.out.print("("+Bx+","+By+") and ");
    System.out.print("("+Cx+","+Cy+")-");
    System.out.print("("+Dx+","+Dy+")-");
    if (xxx) System.out.print(" INTERSECT");
    else System.out.println(" dont intersect");
    */

    return xxx;
    
}
static boolean intersect(double Ax, double Ay,
        double Bx, double By,
        double Cx, double Cy,
        double Dx, double Dy) {
    
    double  distAB, theCos, theSin, newX, ABpos ;

  //  Fail if either line segment is zero-length.
  if (Ax==Bx && Ay==By || Cx==Dx && Cy==Dy) return false;

  //  Fail if the segments share an end-point.
  if (Ax==Cx && Ay==Cy || Bx==Cx && By==Cy
  ||  Ax==Dx && Ay==Dy || Bx==Dx && By==Dy) {
    return false; }

  //  (1) Translate the system so that point A is on the origin.
  Bx-=Ax; By-=Ay;
  Cx-=Ax; Cy-=Ay;
  Dx-=Ax; Dy-=Ay;

  //  Discover the length of segment A-B.
  distAB=Math.sqrt(Bx*Bx+By*By);

  //  (2) Rotate the system so that point B is on the positive X axis.
  theCos=Bx/distAB;
  theSin=By/distAB;
  newX=Cx*theCos+Cy*theSin;
  Cy  =Cy*theCos-Cx*theSin; Cx=newX;
  newX=Dx*theCos+Dy*theSin;
  Dy  =Dy*theCos-Dx*theSin; Dx=newX;

  //  Fail if segment C-D doesn't cross line A-B.
  if (Cy<0. && Dy<0. || Cy>=0. && Dy>=0.) return false;

  //  (3) Discover the position of the intersection point along line A-B.
  ABpos=Dx+(Cx-Dx)*Dy/(Dy-Cy);

  //  Fail if segment C-D crosses line A-B outside of segment A-B.
  if (ABpos<0. || ABpos>distAB) return false;

  //  Success.
  return true; }
}
