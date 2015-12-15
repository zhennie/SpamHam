package me.ash.spamfilter.perceptron;

import me.ash.spamfilter.BinaryClassifier;
import me.ash.spamfilter.vector.SparseVector;

import java.util.List;

/**
 * Created by ash on 11/10/15.
 */
public class Perceptron implements BinaryClassifier {
    private SparseVector w;
    private double b;
    private double learningRate;

    public Perceptron() {
        this(new SparseVector(),0.0,0.1);
    }

    public Perceptron(double learningRate) {
        this(new SparseVector(),0.0,learningRate);
    }

    public Perceptron(SparseVector w, double b, double learningRate) {
        this.w = w;
        this.b = b;
        this.learningRate = learningRate;
    }

    public void trainOneStep(SparseVector xi, int yi){
        if (((w.dotProduct(xi)+b) * yi) <= 0){


            w = w.add(xi.times(learningRate*yi));
            b = b + learningRate * yi;
        }
        else return;
    }

    public void trainWholeSet(List<SparseVector> x, List<Integer> y){
        if (x.size() != y.size()) {
            System.out.println("wrong size of x and y");
            return;
        }

        for (int i=0; i<y.size(); i++){
            trainOneStep(x.get(i), y.get(i));
        }
    }


    @Override
    public void train(SparseVector x, int y) {
        this.trainOneStep(x,y);
    }

    public double getScore(SparseVector x){
        return w.dotProduct(x) + b;
    }

}
