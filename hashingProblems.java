/*
 * *** Abdul Shad / 272 ***
 *
 * This HashingProblems object contains three methods / problems that you must
 * complete utilizing the HashMap object within Java's Collection Framework Library.
 *
 * The three methods / problems you need to solve are:
 *  - getAverage
 *  - odd
 *  - twoSums
 */

import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class HashingProblems {

    /*
     * Method getAverage()
     *
     * This method accepts two parameters. The first is a HashMap object, while the second
     * is an array of integers. This method must compute the average of the values for each
     * 'key' that is found in BOTH the HashMap and the array.
     */

    public double getAverage(HashMap<Integer, Integer> map, int[] array) {
        int sum = 0;
        int count = 0;

        // Iterate over the array and check for matching keys in the HashMap
        for (int num : array) {
            if (map.containsKey(num)) {
                sum += map.get(num);
                count++;
            }
        }

        // If no common keys found, return NaN
        return count == 0 ? Double.NaN : (double) sum / count;
    }

    /*
     * Method odd()
     *
     * This method accepts a HashMap object and returns an ArrayList object with the
     * values of the corresponding keys that are odd.
     */

    public ArrayList<String> odd(HashMap<Integer, String> map) {
        ArrayList<String> result = new ArrayList<>();

        // Iterate through keys and add values of odd keys to the result list
        for (Integer key : map.keySet()) {
            if (key % 2 != 0) { // Check if the key is odd
                result.add(map.get(key));
            }
        }

        return result;
    }

    /*
     * Method twoSums()
     *
     * Solves the problem of finding the number of pairs in an array where the difference
     * between elements equals k in O(n) complexity.
     */

    public int twoSums(int[] numbers, int k) {
        HashSet<Integer> numSet = new HashSet<>();
        int count = 0;

        // Populate HashSet
        for (int num : numbers) {
            numSet.add(num);
        }

        // Check if the complement (num ± k) exists in the set
        for (int num : numbers) {
            if (numSet.contains(num + k)) {
                count++;
            }
        }

        return count;
    }
}
