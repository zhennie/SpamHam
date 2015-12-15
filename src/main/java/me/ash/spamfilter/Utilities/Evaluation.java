package me.ash.spamfilter.Utilities;

import me.ash.spamfilter.perceptron.Perceptron;
import me.ash.spamfilter.vector.SparseVector;

import java.util.List;

/**
 * Created by ash on 11/12/15.
 */
public class Evaluation {
    private double hamCount;
    private double spamCount;
    private double trueHamCount;
    private double trueSpamCount;
    private double falseHamCount;
    private double falseSpamCount;

    public Evaluation() {
        this.hamCount = 0;
        this.spamCount = 0;
        this.trueHamCount = 0;
        this.trueSpamCount = 0;
        this.falseHamCount = 0;
        this.falseSpamCount = 0;
    }

    public void testOnDataset(List<SparseVector> testX, List<Integer> testY, Perceptron perceptron) {
        if (testX.size() != testY.size()) {
            System.out.println("Wrong size of test data and outcomes");
            return;
        }


        for (int i = 0; i < testY.size(); i++) {

            int yi = testY.get(i);
            double yEstimate = perceptron.getScore(testX.get(i));

            if (yi == 1) {
                this.hamCount += 1;
                if (yi * yEstimate < 0) {
                    this.falseSpamCount++;
                } else {
                    this.trueHamCount++;
                }

            } else {

                this.spamCount += 1;
                if (yi * yEstimate < 0) {
                    this.falseHamCount++;
                } else {
                    this.trueSpamCount++;
                }
            }


        }
    }

    public double getTrueSpamRate() {
        if (this.trueSpamCount == 0 && this.falseHamCount == 0) {
            System.out.println("zero TN and FP");
            return 0;
        }
        return trueSpamCount / (trueSpamCount + falseHamCount);
    }

    public double getTrueHamRate() {
        if (this.trueHamCount == 0 && this.falseSpamCount == 0) {
            System.out.println("zero TP and FN");
            return 0;
        }
        return trueHamCount / (trueHamCount + falseSpamCount);
    }

    public double getSpamPrecision() {
        if (this.trueSpamCount == 0 && this.falseSpamCount == 0) {
            System.out.println("zero TrueSpam and FalseSpam");
            return 0;
        }
        return trueSpamCount / (trueSpamCount + falseSpamCount);
    }

    public double getSpamRecall() {
        if (this.trueSpamCount == 0 && this.falseHamCount == 0) {
            System.out.println("zero TrueSpam and FalseSpam");
            return 0;
        }
        return trueSpamCount / (trueSpamCount + falseHamCount);
    }

    public double getSpamAccuracy() {
        if (this.trueSpamCount == 0 && this.falseSpamCount == 0 &&
                this.trueHamCount == 0 && this.falseHamCount == 0) {
            System.out.println("zero TrueSpam and FalseSpam and TrueHam and FalseHam");
            return 0;
        }
        return (trueSpamCount + trueHamCount) / (trueSpamCount + trueHamCount + falseSpamCount + falseHamCount);
    }

    public double getFMeasure() {
        if (this.getSpamPrecision() == 0 && this.getSpamRecall() == 0) {
            System.out.println("zero Precision and Recall");
            return 0;
        }
        return 2 * this.getSpamPrecision() * this.getSpamRecall() / (this.getSpamPrecision() + this.getSpamRecall());
    }

}
