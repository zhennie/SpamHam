package me.ash.spamfilter;

import me.ash.spamfilter.Utilities.EmailParser;
import me.ash.spamfilter.Utilities.Lexicon;
import me.ash.spamfilter.Utilities.Evaluation;
import me.ash.spamfilter.perceptron.Perceptron;
import me.ash.spamfilter.vector.SparseVector;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ash on 11/10/15.
 */
public class Main {
    public static void main(String[] args) {

        String hamTrainingFolder = "/Users/ash/Downloads/lbjava-tutorial/data/spam/train/ham";
        String spamTrainingFolder = "/Users/ash/Downloads/lbjava-tutorial/data/spam/train/spam";

        String hamTestingFolder = "/Users/ash/Downloads/lbjava-tutorial/data/spam/test/ham";
        String spamTestingFolder = "/Users/ash/Downloads/lbjava-tutorial/data/spam/test/spam";

        Perceptron myPerceptron = new Perceptron();
        Lexicon myLexicon = new Lexicon();


        Dataset trainingData = readData(hamTrainingFolder, myLexicon, 1);
        trainingData.merge(readData(spamTrainingFolder, myLexicon, -1));

        myLexicon.disableUpdates();

        Dataset testingData = readData(hamTestingFolder, myLexicon, 1);
        testingData.merge(readData(spamTestingFolder, myLexicon, -1));



        for (int j = 0; j<50; j++){
            myPerceptron.trainWholeSet(trainingData.getXs(), trainingData.getYs());
            System.out.println(j);

            Evaluation testingEvaluation = new Evaluation();
            testingEvaluation.testOnDataset(testingData.getXs(), testingData.getYs(), myPerceptron);
            System.out.println("Testing F-Measure = " + testingEvaluation.getFMeasure());

            Evaluation trainingEvaluation = new Evaluation();
            trainingEvaluation.testOnDataset(trainingData.getXs(), trainingData.getYs(), myPerceptron);
            System.out.println("Training F-Measure = " + trainingEvaluation.getFMeasure());

        }

    }

    private static Dataset readData(String folderPath, Lexicon lexicon,int label) {
        List<SparseVector> xs = new ArrayList<SparseVector>();
        List<Integer> ys = new ArrayList<Integer>();
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            File file = listOfFiles[i];
            String filePath = file.getAbsolutePath();

            xs.add(EmailParser.readDataToSparseVector(filePath, lexicon));
            ys.add(label);
        }
        return new Dataset(xs,ys);
    }

}
