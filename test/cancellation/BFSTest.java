/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cancellation;

import java.util.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author grouptheory
 */
public class BFSTest {

    public BFSTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of grow method, of class BFS.
     */
    @Test
    public void testGrow() {
        Diagram d = new Diagram();
        Node n0 = d.addNode();
        Node n1 = d.addNode();
        Node n2 = d.addNode();
        Node n11 = d.addNode();
        Node n22 = d.addNode();
        Node x1 = d.addNode();
        Node x2 = d.addNode();
        d.addEdge(n0, n1);
        Edge e1 = d.addEdge(n0, n2);
        d.addEdge(n1, n11);
        Edge e2 = d.addEdge(n2, n22);
        Edge ex = d.addEdge(x1, x2);

        System.out.println("grow");
        BFS instance = new BFS(d, n0);
        int expResult = 5;
        int result = instance.countMarkedNodes();
        assertEquals(expResult, result);
    }

    /**
     * Test of reachable method, of class BFS.
     */
    @Test
    public void testReachable_Node() {
        Diagram d = new Diagram();
        Node n0 = d.addNode();
        Node n1 = d.addNode();
        Node n2 = d.addNode();
        Node n11 = d.addNode();
        Node n22 = d.addNode();
        Node x1 = d.addNode();
        Node x2 = d.addNode();
        d.addEdge(n0, n1);
        Edge e1 = d.addEdge(n0, n2);
        d.addEdge(n1, n11);
        Edge e2 = d.addEdge(n2, n22);
        Edge ex = d.addEdge(x1, x2);

        System.out.println("reachable");
        BFS instance = new BFS(d, n0);

        assertEquals(true, instance.reachable(n0));
        assertEquals(true, instance.reachable(n1));
        assertEquals(true, instance.reachable(n2));
        assertEquals(true, instance.reachable(n11));
        assertEquals(true, instance.reachable(n22));
        assertEquals(false, instance.reachable(x1));
        assertEquals(false, instance.reachable(x2));
    }

    /**
     * Test of reachable method, of class BFS.
     */
    @Test
    public void testReachable_Edge() {
        Diagram d = new Diagram();
        Node n0 = d.addNode();
        Node n1 = d.addNode();
        Node n2 = d.addNode();
        Node n11 = d.addNode();
        Node n22 = d.addNode();
        Node x1 = d.addNode();
        Node x2 = d.addNode();
        d.addEdge(n0, n1);
        Edge e1 = d.addEdge(n0, n2);
        d.addEdge(n1, n11);
        Edge e2 = d.addEdge(n2, n22);
        Edge ex = d.addEdge(x1, x2);

        System.out.println("reachable");
        BFS instance = new BFS(d, n0);

        assertEquals(true, instance.reachable(e1));
        assertEquals(true, instance.reachable(e2));
        assertEquals(false, instance.reachable(ex));
    }

    /**
     * Test of reachableNodesIterator method, of class BFS.
     */
    @Test
    public void testReachableNodesIterator() {
        System.out.println("reachableNodesIterator");
        Diagram d = new Diagram();
        Node n0 = d.addNode();
        Node n1 = d.addNode();
        Node n2 = d.addNode();
        Node n11 = d.addNode();
        Node n22 = d.addNode();
        Node x1 = d.addNode();
        Node x2 = d.addNode();
        d.addEdge(n0, n1);
        Edge e1 = d.addEdge(n0, n2);
        d.addEdge(n1, n11);
        Edge e2 = d.addEdge(n2, n22);
        Edge ex = d.addEdge(x1, x2);

        BFS instance = new BFS(d, n0);

        Iterator result = instance.reachableNodesIterator();
        int c=0;
        while (result.hasNext()) {
            c++;
            result.next();
        }
        assertEquals(5, c);
    }

    /**
     * Test of reachableEdgesIterator method, of class BFS.
     */
    @Test
    public void testReachableEdgesIterator() {
        System.out.println("reachableEdgesIterator");
        Diagram d = new Diagram();
        Node n0 = d.addNode();
        Node n1 = d.addNode();
        Node n2 = d.addNode();
        Node n11 = d.addNode();
        Node n22 = d.addNode();
        Node x1 = d.addNode();
        Node x2 = d.addNode();
        d.addEdge(n0, n1);
        Edge e1 = d.addEdge(n0, n2);
        d.addEdge(n1, n11);
        Edge e2 = d.addEdge(n2, n22);
        Edge ex = d.addEdge(x1, x2);

        BFS instance = new BFS(d, n0);

        Iterator result = instance.reachableEdgesIterator();
        int c=0;
        while (result.hasNext()) {
            c++;
            result.next();
        }
        assertEquals(4, c);
    }

    /**
     * Test of getPathFrom method, of class BFS.
     */
    @Test
    public void testGetPathFrom_Node() {
        System.out.println("getPathFrom");
        Diagram d = new Diagram();
        Node n0 = d.addNode();
        Node n1 = d.addNode();
        Node n2 = d.addNode();
        Node n11 = d.addNode();
        Node n22 = d.addNode();
        Node x1 = d.addNode();
        Node x2 = d.addNode();
        d.addEdge(n0, n1);
        Edge e1 = d.addEdge(n0, n2);
        d.addEdge(n1, n11);
        Edge e2 = d.addEdge(n2, n22);
        Edge ex = d.addEdge(x1, x2);

        BFS instance = new BFS(d, n0);

        Path result = instance.getPathFrom(n0);
        assertEquals(0, result.length());
        result = instance.getPathFrom(n1);
        assertEquals(1, result.length());
        result = instance.getPathFrom(n2);
        assertEquals(1, result.length());
        result = instance.getPathFrom(n11);
        assertEquals(2, result.length());
        result = instance.getPathFrom(n22);
        assertEquals(2, result.length());
    }

    /**
     * Test of getPathFrom method, of class BFS.
     */
    @Test
    public void testGetPathFrom_Edge() {
        System.out.println("getPathFrom");
        Diagram d = new Diagram();
        Node n0 = d.addNode();
        Node n1 = d.addNode();
        Node n2 = d.addNode();
        Node n11 = d.addNode();
        Node n22 = d.addNode();
        Node x1 = d.addNode();
        Node x2 = d.addNode();
        d.addEdge(n0, n1);
        Edge e1 = d.addEdge(n0, n2);
        d.addEdge(n1, n11);
        Edge e2 = d.addEdge(n2, n22);
        Edge ex = d.addEdge(x1, x2);

        BFS instance = new BFS(d, n0);

        Path result = instance.getPathFrom(e1);
        assertEquals(1, result.length());
        result = instance.getPathFrom(e2);
        assertEquals(2, result.length());
    }

    /**
     * Test of toString method, of class BFS.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Diagram d = new Diagram();
        Node n0 = d.addNode();
        Node n1 = d.addNode();
        Node n2 = d.addNode();
        Node n11 = d.addNode();
        Node n22 = d.addNode();
        Node x1 = d.addNode();
        Node x2 = d.addNode();
        d.addEdge(n0, n1);
        Edge e1 = d.addEdge(n0, n2);
        d.addEdge(n1, n11);
        Edge e2 = d.addEdge(n2, n22);
        Edge ex = d.addEdge(x1, x2);

        BFS instance = new BFS(d, n0);

        String result = instance.toString();
        assertEquals(true, result.length()>0);
    }
}