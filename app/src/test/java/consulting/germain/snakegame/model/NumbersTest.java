/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.model;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mark_local on 28/11/2015.
 * Testing finding middle of array
 */
@RunWith(Parameterized.class)
public class NumbersTest extends TestCase {

    private static final Integer[] empty     = {};
    private static final Integer[] zero      = {0};
    private static final Integer[] one       = {1};
    private static final Integer[] pyramid  = {1, 2, 3, 4, 3, 2, 1, 0};
    private static final Integer[] ramp     = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 45};
    private static final Integer[] right    = {1, 2, 3, 4, 5, 6, 7, 8, 9, -45, 10};
    private static final Integer[] left     = {100, 1, 2, 3, 4, 5, 6, 7, 8, 9, -45};
    private static final Integer[] bigPos   =
            {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
    private static final Integer[] bigRange = {Integer.MIN_VALUE, Integer.MAX_VALUE, 26, -1};
    private static final Integer[] bigArray  = buildBigArray();

    private final int[]   ara;
    private final int     expected;
    private final Numbers target;
    private final String  msg;

    /**
     * test a sample array to see if it locates the expected midPoint
     *
     * @param in       array to test
     * @param expected expected mid point
     * @param msg      context message
     */
    public NumbersTest(int expected, Integer[] in, String msg) {
        this.ara = new int[in.length];
        for (int idx = 0; idx < in.length; ++idx) {
            ara[idx] = in[idx];
        }
        this.expected = expected;
        this.target = new Numbers();
        this.msg = "Testing: " + msg;
    }

    private static Integer[] buildBigArray() {

        Integer[] ret = {-1, 3, -4, 5, 1, -6, 2, 1};

        return ret;
    }

    @Parameterized.Parameters
    public static List data() {
        return Arrays.asList(new Object[][]{
                        {-1, empty, "empty"},
                        {0, zero, "zero"},
                        {0, one, "one"},
                        {3, pyramid, "pyramid"},
                        {9, ramp, "ramp"},
                        {1, bigPos, "bigPos"},
                        {2, bigRange, "bigRange"},
                        {right.length - 1, right, "right"},
                        {0, left, "left"},
                        {1, bigArray, "bigArray"}
                }
        );
    }

    @Test
    public void testSolution() throws Exception {
        int actual = target.solution(ara);
        assertEquals(msg, expected, actual);
    }


}
