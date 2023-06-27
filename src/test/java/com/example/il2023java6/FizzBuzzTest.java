package com.example.il2023java6;

import com.example.il2023java6.week6.FizzBuzz;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class FizzBuzzTest {
    private static FizzBuzz fizzBuzz;

    @BeforeAll
    public static void init() {
        fizzBuzz = Mockito.spy(FizzBuzz.class);
    }
    @Test
    public void testFizzBuzzPrint() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        when(fizzBuzz.fizzBuzzHelper(3)).thenReturn("3");
        fizzBuzz.fizzBuzzPrint(list);
        for(int v: list) {
            verify(fizzBuzz, times(1)).fizzBuzzHelper(v);
        }
    }

    @Test
    public void fizzBuzzValue() {
        assertTrue(fizzBuzz.fizzBuzzHelper(3).equals("fizz"));
        assertTrue(fizzBuzz.fizzBuzzHelper(5).equals("buzz"));
        assertTrue(fizzBuzz.fizzBuzzHelper(15).equals("fizzbuzz"));
        assertTrue(fizzBuzz.fizzBuzzHelper(-1).equals("-1"));
    }

    @Test
    public void testException() {
        assertThrows(IllegalArgumentException.class, () -> {
            fizzBuzz.fizzBuzzPrint(Arrays.asList(null, 5));
        });
        assertThrows(IllegalArgumentException.class, () -> {
            fizzBuzz.fizzBuzzPrint(null);
        });
    }
}
