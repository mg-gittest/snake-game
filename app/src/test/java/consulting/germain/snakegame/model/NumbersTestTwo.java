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
public class NumbersTestTwo extends TestCase {

    private static final Numbers.Node empty = null;
    private static final Numbers.Node zero  = new Numbers.Node();
    private static final Numbers.Node seven = makeSeven();
    private final int          expected;
    private final Numbers      target;
    private final String       msg;
    private final Numbers.Node node;

    /**
     * test a sample array to see if it locates the expected midPoint
     *
     * @param expected expected mid point
     * @param msg      context message
     */
    public NumbersTestTwo(int expected, Numbers.Node node, String msg) {
        this.expected = expected;
        this.target = new Numbers();
        this.msg = "Testing: " + msg;
        this.node = node;
    }

    private static Numbers.Node makeSeven() {
        Numbers.Node head = null;
        Numbers.Node prev = null;
        int idx = 7;
        while (idx > 0) {
            head = new Numbers.Node(prev, idx);
            prev = head;
            --idx;
        }

        return head;
    }

    @Parameterized.Parameters
    public static List data() {
        return Arrays.asList(new Object[][]{
                        {0, empty, "empty"},
                        {1, zero, "zero"},
                        {7, seven, "seven"}
                }
        );
    }

    @Test
    public void testGetListLength() throws Exception {
        int actual = target.getListLength(node);
        assertEquals(msg, expected, actual);
    }


}
