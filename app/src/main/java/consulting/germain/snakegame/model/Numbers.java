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

    public int findMid(int[] ara) {

        final int length = ara.length;
        BigInteger[] sumLeft = new BigInteger[length];
        BigInteger[] sumRight = new BigInteger[length];

        BigInteger sum = BigInteger.ZERO;
        for (int idx = 0; idx < length; ++idx) {
            sum = sum.add(BigInteger.valueOf(ara[idx]));
            sumLeft[idx] = sum;
        }

        sum = BigInteger.ZERO;
        for (int idx = length - 1; idx >= 0; --idx) {
            sum = sum.add(BigInteger.valueOf(ara[idx]));
            sumRight[idx] = sum;
        }

        if (length < 1) {
            return -1;
        }

        for (int idx = 0; idx < length - 1; ++idx) {
            BigInteger left = sumLeft[idx];
            BigInteger right = sumRight[idx + 1];
            if (left.equals(right)) {
                return idx + 1;
            }
        }

        if (sumLeft[length - 1].equals(BigInteger.ZERO)) {
            return length;
        }
        if (sumRight[0].equals(BigInteger.ZERO)) {
            return 0;
        }

        return -1;
    }
}
