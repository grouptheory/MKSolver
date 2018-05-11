/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utility;

import java.util.Iterator;
import java.util.HashMap;
import java.util.LinkedList;


/**
 *
 * @author grouptheory
 */
public class CompositeIterator implements Iterator {

    private LinkedList _subIterators;
    private CompositeIterator.State _next;
    private boolean _leafOnly = false;
    
    public CompositeIterator(ComposableIterator root, boolean leafOnly) {
        _subIterators = new LinkedList();
        root.setParent(null);
        _subIterators.addLast(root);
        _next = computeNext();
        _leafOnly = leafOnly;
    }
    
    public boolean hasNext() {
        return (_next!=null);
    }
     
    public Object next() {
        CompositeIterator.State answerFull = _next;
        // System.out.println("DEBUG pre "+answer.getLeafIteratorState());
        _next = computeNext();
        // System.out.println("DEBUG post "+answer);
        if (_subIterators.size() == 0) {
            _next = null;
        }

        Object answer = answerFull;
        if (_leafOnly) {
            answer = answerFull.getLeafIteratorState();
        }
        return answer;
    }

    public void remove() {
        throw new RuntimeException("CompositeIterator.remove: not implemented");
    }

    private CompositeIterator.State computeNext() {
        ComposableIterator subitWithStuff = null;
        
        for (Iterator it=_subIterators.descendingIterator();it.hasNext();) {
            ComposableIterator subit = (ComposableIterator)it.next();
            if ( ! subit.hasNext()) {
                it.remove();
            }
            else {
                Object subobj = subit.next();
                if (subobj == null) {
                    it.remove();
                }
                else {
                    subit.setState(subobj);
                    subitWithStuff = subit;
                    break;
                }
            }
        }

        while (subitWithStuff != null) {
            ComposableIterator nextIter = subitWithStuff.newComposableIterator(subitWithStuff);
            if (nextIter != null) {
                if (nextIter.hasNext()) {
                    Object subobj = nextIter.next();
                    if (subobj!=null) {
                        nextIter.setState(subobj);
                        nextIter.setParent(subitWithStuff);
                        _subIterators.addLast(nextIter);
                    }
                    else nextIter=null;
                }
                else nextIter=null;
            }
            
            subitWithStuff = nextIter;
        }

        return new State(this);
    }

    public String toString() {
        String s="";
        s += "CompositeIterator BEGIN\n";
        s += "levels = "+_subIterators.size()+"\n";
        int ct=0;
        for (Iterator it=_subIterators.iterator();it.hasNext();) {
            ComposableIterator subit = (ComposableIterator)it.next();
            s += subit.toString();
            if (it.hasNext()) s += ",";
            ct++;
        }
        
        if (_next == null) {
            s += " CompositeIterator.State = null";
        }
        else {
            s += " State="+_next.toString();
        }
        s += "CompositeIterator END\n";
        return s;
    }

    public static class State {
        private LinkedList _subObjects;
        private CompositeIterator _owner;

        State(CompositeIterator owner) {
            _owner = owner;
            _subObjects = new LinkedList();
            for (Iterator it=_owner._subIterators.iterator();it.hasNext();) {
                ComposableIterator subit = (ComposableIterator)it.next();
                Object subobj = subit.getState();
                _subObjects.addLast(subobj);
            }
        }

        public Object getLeafIteratorState() {
            return _subObjects.getLast();
        }

        public Iterator iteratorComposableStates() {
            return _subObjects.iterator();
        }
        
        public String toString() {
            String s="";
            s+="CompositeIterator.State BEGIN\n";
            for (Iterator it=_subObjects.iterator();it.hasNext();) {
                Object subobj = it.next();
                s += subobj;
                if (it.hasNext()) s += ",";
            }
            s+="CompositeIterator.State END\n";
            return s;
        }
    }
}
