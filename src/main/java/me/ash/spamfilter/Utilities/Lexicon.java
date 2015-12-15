package me.ash.spamfilter.Utilities;

import me.ash.spamfilter.vector.SparseVector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ash on 11/12/15.
 */
public class Lexicon {

    private Map<String, Integer> lexicon;
    private boolean allowUpdates;

    public Lexicon() {
        this.lexicon = new HashMap<String, Integer>();
        this.lexicon.put("unknownFeature", 0);
        this.allowUpdates = true;
    }

    public void disableUpdates() {
        this.allowUpdates = false;
    }

    public void enableUpdates() {
        this.allowUpdates = true;
    }

    public Integer getIndex(String featureName) {

        if (this.allowUpdates) {
            featureName = featureName.toLowerCase();

            if (!this.lexicon.containsKey(featureName)) {
                int n = this.lexicon.size();
                this.lexicon.put(featureName, n);
                return n;
            } else return this.lexicon.get(featureName);
        } else {
            return this.getIndexNoUpdate(featureName);
        }
    }

    private Integer getIndexNoUpdate(String featureName) {
        featureName = featureName.toLowerCase();

        if (!this.lexicon.containsKey(featureName)) {
            return 0;
        } else return this.lexicon.get(featureName);
    }

}
