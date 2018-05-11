/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ge;

import java.util.Iterator;

/**
 *
 * @author grouptheory
 */
public class BaseLayoutDecoratorFactory {
    private int[] _occ;
    private boolean[][] _used;
    private int _slots;
    private int _bd;
    private int _bs;

    private double _width;
    private double _height;
    private double _boundaryspace;
    private double _basespace;

    private double WIDTH;
    private double HEIGHT;

    static double applyToAllBases(GE geq, double width, double height) {
        BaseLayoutDecoratorFactory bldf = new BaseLayoutDecoratorFactory(geq, width, height);
        for (Iterator it=geq.iteratorBases(); it.hasNext();) {
            Base bs=(Base)it.next();
            if (bs.lookupDecorator(BaseLayoutDecorator.NAME) != null) continue;

            BaseLayoutDecorator bld = bldf.newBaseHeightDecorator(bs);
            bld.attach(BaseLayoutDecorator.NAME, bs);
        }
        return bldf._boundaryspace;
    }

    private BaseLayoutDecoratorFactory(GE geq, double width, double height) {
        _bd = geq.getNumberOfBoundaries();
        _bs = geq.getNumberOfBases();
        
        _occ = new int[_bd];
        for (int i=0; i<_bd; i++) {
            _occ[i]=0;
        }

        for (Iterator it=geq.iteratorBases(); it.hasNext();) {
            Base bs=(Base)it.next();
            this.incorporate(bs);
        }

        initSlots();
        
        HEIGHT = height;
        WIDTH = width;
        _basespace = HEIGHT/(double)this.maxOcc();
        _boundaryspace = WIDTH/((double)_bd-1);
    }

    private void incorporate(Base bs) {
        int left = bs.getBegin().getID();
        int right = bs.getEnd().getID();
        for (int i=left; i<=right; i++) {
            _occ[i]++;
        }
    }

    private int maxOcc() {
        int max=0;
        for (int i=0; i<_bd; i++) {
            if (_occ[i]>max) max=_occ[i];
        }
        return max+2;
    }

    private void initSlots() {
        _slots = maxOcc();
        _used = new boolean[_bd][_slots+1];

        for (int i=0; i<_bd; i++) {
            for (int j=0; j<_slots; j++) {
                _used[i][j]=false;
            }
        }
    }

    private BaseLayoutDecorator newBaseHeightDecorator(Base bs) {

        boolean success = false;
        int height=0;
        int left = bs.getBegin().getID();
        int right = bs.getEnd().getID();
        for (int h=1; h<=_slots; h++) {
            if (isFree(left, right, h)) {
                height = h;
                success = true;
                break;
            }
        }
        if (success) {
            markUsed(left, right, height);
            return new BaseLayoutDecorator(left*_boundaryspace,
                                           right*_boundaryspace,
                                           height*_basespace);
        }
        else {
            throw new RuntimeException("DiagramAllocator.assignHeight: failed for bs="+bs);
        }
    }

    private boolean isFree(int left, int right, int h) {
        for (int i=left;i<=right;i++) {
            if (_used[i][h]) return false;
        }
        return true;
    }

    private void markUsed(int left, int right, int h) {
        for (int i=left;i<=right;i++) {
            _used[i][h] = true;
        }
    }
}
