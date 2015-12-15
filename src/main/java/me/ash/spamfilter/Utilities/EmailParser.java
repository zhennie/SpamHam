package me.ash.spamfilter.Utilities;

import me.ash.spamfilter.vector.SparseVector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ash on 11/11/15.
 */
public class EmailParser {

    public static SparseVector readDataToSparseVector(String path, Lexicon lexicon){
        BufferedReader br = null;
        SparseVector vector = new SparseVector();

        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader(path));

            while ((sCurrentLine = br.readLine()) != null) {
                sCurrentLine.toLowerCase();
                String[] wordsInLine = sCurrentLine.split("\\s+");

                for (int i=0; i<wordsInLine.length; i++){
                    vector.setValue(lexicon.getIndex(wordsInLine[i]), 1);
                }

            }

            return vector;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return vector;
    }

    public static SparseVector readDataWithWordCount(String path, Lexicon lexicon){
        BufferedReader br = null;
        SparseVector vector = new SparseVector();


        double counter = 0.0;

        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader(path));

            while ((sCurrentLine = br.readLine()) != null) {
                sCurrentLine.toLowerCase();
                String[] wordsInLine = sCurrentLine.split("\\s+");

                for (int i=0; i<wordsInLine.length; i++){
                    Integer idx = lexicon.getIndex(wordsInLine[i]);
                    vector.setValue(idx, vector.getValue(idx)+1);
                    counter ++;
                }
            }

            for(Integer idx : vector.getIndices()){
                vector.setValue(idx, vector.getValue(idx) / counter);
            }

            return vector;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return vector;
    }


}
