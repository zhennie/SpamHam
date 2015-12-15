package me.ash.spamfilter;

import me.ash.spamfilter.vector.SparseVector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ash on 11/13/15.
 */
public class Dataset {
    private List<SparseVector> xs;
    private List<Integer> ys;

    public Dataset(List<SparseVector> xs, List<Integer> ys) {
        this.xs = xs;
        this.ys = ys;
    }

    public List<SparseVector> getXs() {
        return xs;
    }

    public List<Integer> getYs() {
        return ys;
    }

    public void merge(Dataset other){
        this.xs.addAll(other.getXs());
        this.ys.addAll(other.getYs());
        Collections.shuffle(this.xs);
        Collections.shuffle(this.ys);
    }

}
