package com.example.il2023java6.week6;

import java.util.ArrayList;
import java.util.List;

/**
 *  1. TDD
 *  2. impl fizz buzz
 *     %3 && %5 => fizzbuzz
 *     %3 => fizz
 *     %5 => buzz
 *     print number
 *
 *  input / output format
 *      1. input: List<Integer>
 *      2. output: System.out.println()
 *  edge cases
 *      1. [0]: print 0
 *      2. null: runtime exception
 *      3. [null, 5, null] : runtime exception
 *      4. []: print nothing
 *      5. [-3] : print fizz
 *      6. [-1] : print -1
 *      7. [15] : fizzbuzz
 *      8. [1, 15]: 1, fizzbuzz
 *      9. [5] : buzz
 */
public class FizzBuzz {
    public void fizzBuzzPrint(List<Integer> list) {
        if(list == null) {
            throw new IllegalArgumentException("..");
        }
        for(Integer val: list) {
            if(val == null) {
                throw new IllegalArgumentException("..");
            }
            System.out.println(fizzBuzzHelper(val));
        }
    }

    public String fizzBuzzHelper(int val) {
        if(val % 3 == 0 && val % 5 == 0) {
            return "fizzbuzz";
        } else if(val % 3 == 0) {
            return "fizz";
        } else if(val % 5 == 0) {
            return "buzz";
        }
        return String.valueOf(val);
    }
}