package me.ash.spamfilter.vector;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class SparseVectorTest {

    private SparseVector testVector1 = new SparseVector();
    private SparseVector testVector2 = new SparseVector();
    private SparseVector testVector3 = new SparseVector();

    @Before
    public void setUp(){

        testVector1.setValue(1, 1.0);
        testVector1.setValue(2, 2.0);
        testVector1.setValue(3, 3.0);

        testVector2.setValue(3, 4.0);
        testVector2.setValue(5, 5.0);
        testVector2.setValue(8, 6.0);

        testVector3.setValue(1, 7.0);
        testVector3.setValue(4, 8.0);
    }

    @org.junit.Test
    public void testAdd() throws Exception {
        SparseVector vectorSum = new SparseVector(testVector1.add(testVector2));

        SparseVector tester = new SparseVector();

        tester.setValue(1, 1.0);
        tester.setValue(2, 2.0);
        tester.setValue(3, 7.0);
        tester.setValue(5, 5.0);
        tester.setValue(8, 6.0);

        assertEquals(tester, vectorSum);
    }

    @org.junit.Test
    public void testMinus() throws Exception {
        SparseVector vectorDiff = new SparseVector(testVector2.minus(testVector3));

        SparseVector tester = new SparseVector();

        tester.setValue(3, 4.0);
        tester.setValue(5, 5.0);
        tester.setValue(8, 6.0);
        tester.setValue(1, -7.0);
        tester.setValue(4, -8.0);

        assertEquals(tester, vectorDiff);
    }

    @org.junit.Test
    public void testDotProduct() throws Exception {
        double product1 = testVector1.dotProduct(testVector2);
        double product2 = testVector1.dotProduct(testVector3);

        assertEquals(12, (int)product1);
        assertEquals(7, (int)product2);
    }

    @org.junit.Test
    public void testTimes() throws Exception {
        SparseVector vectorProd = new SparseVector(testVector1.times(2));

        SparseVector tester = new SparseVector();

        tester.setValue(1, 2.0);
        tester.setValue(2, 4.0);
        tester.setValue(3, 6.0);

        assertEquals(tester, vectorProd);
    }

    @org.junit.Test
    public void testDivide() throws Exception {
        SparseVector vector = new SparseVector(testVector2.divide(2));

        SparseVector tester = new SparseVector();

        tester.setValue(3, 2.0);
        tester.setValue(5, 2.5);
        tester.setValue(8, 3.0);

        assertEquals(tester, vector);
    }


    @Test
    public void testGetValue() throws Exception {
        assertEquals(3, (int)testVector1.getValue(3));
    }

    @Test
    public void testSetValue() throws Exception {
        SparseVector vector = new SparseVector(testVector1);
        vector.setValue(1, 0);
        vector.setValue(2, 0);
        vector.setValue(3, 4);
        vector.setValue(5, 5);
        vector.setValue(8, 6);

        assertEquals(vector, testVector2);
    }

    @Test
    public void testGetIndices() throws Exception {
        Set<Integer> idx = new HashSet<Integer>();
        idx.add(1);
        idx.add(2);
        idx.add(3);

        assertEquals(idx, testVector1.getIndices());
    }
}