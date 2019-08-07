package com.example.socialmediaservice.util;

import com.example.socialmediaservice.model.Pair;
import com.example.socialmediaservice.model.Person;

import java.util.Arrays;
import java.util.List;

/**
 * Utility class
 */
public final class PairUtils {
    /**
     * Calculates pair compatibility points
     *
     * @param p1 - first member of the pair
     * @param p2 - second member of the pair
     * @return count of points of the pair
     */
    public static int countPairsPoints(Person p1, Person p2) {
        return (int) p1.getInterests().stream().filter(p2.getInterests()::contains).count();
    }

    /**
     * Get list of persons from pair
     *
     * @param pair - a couple from which to get a list of persons
     * @return list of persons
     */
    public static List<Person> getPersonsFromPair(Pair pair) {
        return Arrays.asList(pair.getP1(), pair.getP2());
    }
}
