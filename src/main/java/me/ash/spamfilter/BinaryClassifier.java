package me.ash.spamfilter;

import me.ash.spamfilter.vector.SparseVector;

/**
 * Created by ash on 11/12/15.
 */
public interface BinaryClassifier {
    public void train(SparseVector x, int y);
    public double getScore(SparseVector x);
}
