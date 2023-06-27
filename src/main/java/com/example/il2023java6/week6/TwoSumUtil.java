package com.example.il2023java6.week6;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * TDD
 *      1. clarify requirements
 *      2. write test cases
 *      3. provide interface and abstract class + todo
 *      4. finish test cases, assert
 *      5. finish todo + provide impl
 *      6. run test cases
 *
 * Example: Two Sum
 *      1. clarify requirements
 *          a. []:  false
 *          b. [Integer.MAX_VALUE, Integer.MAX_VALUE] / overflow (no such cases)
 *          c. [single element]: false
 *          d. [5, -5], 0 : true
 *          e. [1, 5, 5], 6: true
 *          f. sorted array: not sorted
 *          g. null, 0: throw new IllegalArgumentException
 *      2.
 */
public class TwoSumUtil {
    private DataRepo dataRepo;

    public TwoSumUtil() {
    }

    public TwoSumUtil(DataRepo dataRepo) {
        this.dataRepo = dataRepo;
    }

    public boolean twoSum(int[] arr, int target) {
        if(arr == null) {
            throw new IllegalArgumentException("..");
        }
        if(arr.length <= 1) {
            return false;
        }
        Set<Integer> visited = new HashSet<>();
        for(int n: arr) {
            if(visited.contains(target - n)) {
                return true;
            }
            visited.add(n);
        }
        return false;
    }

    public boolean twoSum(int target) {
        int[] arr = dataRepo.getData();
        Map<Integer, Integer> cnt = new HashMap<>();
        for(int n: arr) {
            if(cnt.containsKey(target - n)) {
                return true;
            }
            cnt.put(n, 1);
        }
        return false;
    }
}
