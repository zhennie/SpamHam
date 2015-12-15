package me.ash.spamfilter.Utilities;

import org.junit.Test;

import static org.junit.Assert.*;

public class LexiconTest {
    private Lexicon testLexicon = new Lexicon();

    @Test
    public void testGetIndex() throws Exception {
        assertEquals(1, (int) testLexicon.getIndex("1a"));
        assertEquals(2, (int) testLexicon.getIndex("2"));
        assertEquals(3, (int) testLexicon.getIndex("3"));
        assertEquals(1, (int) testLexicon.getIndex("1A"));
        assertEquals(2, (int) testLexicon.getIndex("2"));
        assertEquals(3, (int) testLexicon.getIndex("3"));
        assertEquals(4, (int) testLexicon.getIndex("11"));

        testLexicon.disableUpdates();

        assertEquals(0, (int) testLexicon.getIndex("2a"));
        assertEquals(2, (int) testLexicon.getIndex("2"));
        assertEquals(3, (int) testLexicon.getIndex("3"));
        assertEquals(1, (int) testLexicon.getIndex("1A"));
        assertEquals(0, (int) testLexicon.getIndex("2B"));
        assertEquals(0, (int) testLexicon.getIndex("3C"));
        assertEquals(4, (int) testLexicon.getIndex("11"));
    }

}