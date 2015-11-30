/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.model;

import java.math.BigInteger;

/**
 * Created by mark_local on 28/11/2015.
 */
public class Numbers {
    private BigInteger[] tenBig = new BigInteger[10];

    public Numbers() {
        for (int idx = 0; idx < 10; ++idx) {
            tenBig[idx] = BigInteger.valueOf((idx - 5) * 1000000);
        }
    }

    public BigInteger[] getTenBig() {
        return tenBig;
    }

    public int solution(int[] A) {
        // write your code in Java SE 8
        final int length = A.length;

        if (length < 1) {
            return -1;
        }
        if (length == 1) {
            return 0;
        }

        long sumToRight = 0;
        long sumToLeft = 0;

        // establish full sum
        for (int val : A) {
            sumToRight += val;
        }

        // walk array, subtract current val from sum to right compare to sum to left,
        // then add val to sum to left to prepare for next loop
        for (int idx = 0; idx < length; idx++) {
            final int val = A[idx];
            sumToRight -= val;
            if (sumToLeft == sumToRight) {
                return idx;
            }
            sumToLeft += val;
        }


        // no match so return appropriately
        return -1;
    }

    public int getListLength(final Node node) {
        int len = 0;
        Node curr = node;
        while (curr != null) {
            ++len;
            curr = curr.next;
        }
        return len;
    }

    public static class Node {
        Node next;
        int  val;

        public Node(Node next, int val) {
            this.next = next;
            this.val = val;
        }

        public Node(int val) {
            this(null, val);
        }

        public Node() {
            this(0);
        }
    }
}
