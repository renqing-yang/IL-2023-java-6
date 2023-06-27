package com.example.il2023java6;

import static org.junit.jupiter.api.Assertions.*;

import com.example.il2023java6.week6.TwoSumUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TwoSumTestNoRepo {

    //private TwoSumUtil util = new TwoSumUtil();
    private static TwoSumUtil util;
    @BeforeAll
    public static void init() {
        util = new TwoSumUtil();
    }
    @Test
    public void positiveValueInput() {
        assertTrue(util.twoSum(new int[]{1, 5, 5}, 6));
        assertTrue(util.twoSum(new int[]{2, 5}, 7));
        assertTrue(util.twoSum(new int[]{3, 3, 3}, 6));
    }

    @Test
    public void negativeValueInput() {
        assertTrue(util.twoSum(new int[]{1, -5, 5}, 0));
        assertTrue(util.twoSum(new int[]{2, -5}, -3));
        assertTrue(util.twoSum(new int[]{-3, 3, 3}, 6));
    }

    @Test
    public void testExceptionInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            util.twoSum(null, 0);
        });
    }

    @Test
    public void negativeCasesInput() {
        assertFalse(util.twoSum(new int[0], 6));
        assertFalse(util.twoSum(new int[]{1}, 6));
    }

}
