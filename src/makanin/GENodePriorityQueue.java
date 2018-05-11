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

package makanin;

import java.util.LinkedList;
import java.util.Iterator;
import utility.ConsoleLogger;
import solutions.Solution;
import solutions.SolutionFactory;

/**
 *
 * @author grouptheory
 */
public class GENodePriorityQueue {

    private static class ProcessingPolicy {};
    public static final ProcessingPolicy BFS_POLICY = new ProcessingPolicy();
    public static final ProcessingPolicy DFS_POLICY = new ProcessingPolicy();

    private LinkedList _q;
    private ProcessingPolicy _policy;

    public GENodePriorityQueue() {
        this(BFS_POLICY);
    }

    public GENodePriorityQueue(ProcessingPolicy policy) {
        _policy = policy;
        _q = new LinkedList();
        _observers = new LinkedList();
    }

    public void enqueue(GENode node) {
        ConsoleLogger.instance().info(this.getClass().getSimpleName(), "enqueueing "+node.getName());

        _q.addLast(node);
        notifyObservers(node);
    }

    public boolean empty() {
        return _q.size()==0;
    }

    GENode dequeue() {
        if (empty()) {
            throw new RuntimeException("GENodePriorityQueue.dequeue: queue is empty");
        }
        
        GENode next = null;
        if (_policy == DFS_POLICY) {
            next = (GENode)_q.removeLast();
        }
        else if (_policy == BFS_POLICY) {
            next = (GENode)_q.removeFirst();
        }
        else {
            throw new RuntimeException("GENodePriorityQueue.dequeue: unknown policy");
        }
        ConsoleLogger.instance().info(this.getClass().getSimpleName(), "dequeueing..."+next.getName());

        return next;
    }

    public GENode step() {
        GENode next = null;
        if (!empty()) {
            ConsoleLogger.instance().info(this.getClass().getSimpleName(), "stepping...");

            next = dequeue();
            LinkedList children = next.expand();
            int count = children.size();
            for (Iterator it=children.iterator(); it.hasNext();) {
                GENode child = (GENode)it.next();
                enqueue(child);
            }
            appendToGENodeLog(next, count);
        }
        return next;
    }

    private void appendToGENodeLog(GENode next, int count) {
        if (next.isLeaf()) {
            next.appendToLog("This GE is a leaf in the GE tree.  ");
            if (next.isSolvable()) {
                next.appendToLog("We have effectively found a solution!\n");

                Solution ss = SolutionFactory.instance().newSolution(next);
                String solStr = solutions.Latex.instance().renderSolution(ss);
                next.appendToLog(solStr);
            }
            else {
                next.appendToLog("This branch of the tree has led us to a dead end.");
            }
        }
        else {
            next.appendToLog("This GE is {\\em not} a leaf in the GE tree.   ");
            if (count==0) {
                next.appendToLog("However, it has no valid prints (descendents).  ");
            }
        }

        if (count>0) {
            next.appendToLog("It has "+count+" valid prints (descendents).  ");
        }
        next.appendToLog("\\\\"+params.MKParams.REPORT_SPACING+"\n");

    }
    private LinkedList _observers;
    
    public void attachObserver(IGENodePriorityQueueObserver obs) {
        ConsoleLogger.instance().info(this.getClass().getSimpleName(), "attachObserver "+obs);
        _observers.addLast(obs);
    }

    void detachObserver(IGENodePriorityQueueObserver obs) {
        ConsoleLogger.instance().info(this.getClass().getSimpleName(), "detachObserver "+obs);
        _observers.remove(obs);
    }

    void notifyObservers(GENode node) {
        for (Iterator it=_observers.iterator(); it.hasNext();) {
            IGENodePriorityQueueObserver obs = (IGENodePriorityQueueObserver)it.next();
            ConsoleLogger.instance().debug(this.getClass().getSimpleName(), "notifyObservers "+obs);
            
            obs.notify(this, node);
        }
    }
}
