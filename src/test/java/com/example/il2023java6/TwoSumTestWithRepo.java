package com.example.il2023java6;

import com.example.il2023java6.week6.DataRepo;
import com.example.il2023java6.week6.TwoSumUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class TwoSumTestWithRepo {
    private static TwoSumUtil util;
    private static DataRepo dataRepo;

    @BeforeAll
    public static void init() {
        dataRepo = Mockito.mock(DataRepo.class);
        util = new TwoSumUtil(dataRepo);

    }
    @Test
    public void positiveValueInput() {
        when(dataRepo.getData()).thenReturn(new int[]{3, 3});
        assertTrue(util.twoSum(6));
        when(dataRepo.getData()).thenReturn(new int[]{4, 3});
        assertTrue(util.twoSum(7));
    }

    @Test
    public void negativeValueInput() {
        when(dataRepo.getData()).thenReturn(new int[]{3, -3});
        assertTrue(util.twoSum(0));
    }


    @Test
    public void negativeCasesInput() {
        when(dataRepo.getData()).thenReturn(new int[0]);
        assertFalse(util.twoSum(6));
        when(dataRepo.getData()).thenReturn(new int[]{1});
        assertFalse(util.twoSum(6));

        verify(dataRepo, times(2)).getData();
    }

}
