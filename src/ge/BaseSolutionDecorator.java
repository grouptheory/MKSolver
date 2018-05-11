/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ge;

import equation.GroupEquation;

/**
 *
 * @author grouptheory
 */
public class BaseSolutionDecorator extends BaseDecorator {

    static final String NAME = "Solution";

    private GroupEquation _eq;

    BaseSolutionDecorator(GroupEquation eq) {
        _eq = eq;
    }

    private BaseSolutionDecorator(BaseSolutionDecorator orig) {
        _eq = orig._eq.duplicate();
    }

    public BaseSolutionDecorator duplicate() {
        return new BaseSolutionDecorator(this);
    }
    
    GroupEquation getSolution() {
        return _eq;
    }

    public static void attachSolution(Base bs, GroupEquation sol) {
        bs.attachDecorator(BaseSolutionDecorator.NAME,
                new BaseSolutionDecorator(sol.duplicate()));
    }
}

