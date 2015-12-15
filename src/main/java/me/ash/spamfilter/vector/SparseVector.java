package me.ash.spamfilter.vector;


import java.util.*;

/**
 * Created by ash on 11/10/15.
 */
public class SparseVector{

    private Map<Integer, Double> map;

    public SparseVector() {
        this.map = new HashMap<Integer, Double>();
    }

    public SparseVector(SparseVector v) {
        this.map = new HashMap<Integer, Double>();
        Set<Integer> indicesToCopy = v.getIndices();
        for (Integer idx: indicesToCopy){
            this.map.put(idx, v.getValue(idx));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SparseVector)) return false;

        SparseVector that = (SparseVector) o;

        if (!map.equals(that.map)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return map.hashCode();
    }

    public double getValue(int idx){
        if (!this.map.containsKey(idx)) return 0.0;
        else {
            return this.map.get(idx);
        }
    }

    public void setValue(int idx, double v){
        if (v == 0.0 && this.map.containsKey(idx)) this.map.remove(idx);
        else if (v == 0.0) return;
        else this.map.put(idx, v);
    }

    public Set<Integer> getIndices(){
        HashSet<Integer> indices = new HashSet<Integer>(this.map.keySet());
        return indices;
    }

    public SparseVector add(SparseVector y) {
        SparseVector res = new SparseVector(this);
        Set<Integer> yIndices = y.getIndices();
        for (Integer idx: yIndices){
            res.setValue(idx, y.getValue(idx) + this.getValue(idx));
        }

        return res;
    }

    public SparseVector minus(SparseVector y){
        SparseVector res = new SparseVector(this);
        Set<Integer> yIndices = y.getIndices();
        for (Integer idx: yIndices){
            res.setValue(idx, this.getValue(idx) - y.getValue(idx));
        }

        return res;
    }

    public double dotProduct(SparseVector y){
        double res = 0.0;
        Set<Integer> overlappingIndices = this.getIndices();
        overlappingIndices.retainAll(y.getIndices());

        for (Integer idx: overlappingIndices){
            res += this.getValue(idx) * y.getValue(idx);
        }

        return res;
    }

    public SparseVector times(double n){
        SparseVector res = new SparseVector(this);
        Set<Integer> indices = this.getIndices();
        for (Integer idx: indices){
            res.setValue(idx, this.getValue(idx) * n);
        }

        return res;
    }

   public SparseVector divide(double n){
       if (n == 0.0) throw new IllegalArgumentException("zero divider");

       SparseVector res = new SparseVector(this);
       Set<Integer> indices = this.getIndices();
       for (Integer idx: indices){
           res.setValue(idx, this.getValue(idx) / n);
       }

       return res;
   }



}
