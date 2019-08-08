package com.example.socialmediaservice.task;

import com.example.socialmediaservice.model.Pair;
import com.example.socialmediaservice.util.PairUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Task performed in a thread to find combinations of pairs
 */
public class CombinationFinder implements Callable<List<Pair>> {
    private Pair headPair;
    private List<Pair> allPairs;

    public CombinationFinder(Pair headPair, List<Pair> allPairs) {
        this.headPair = headPair;
        this.allPairs = allPairs;
    }

    /**
     * Get a possible combination of pairs
     *
     * @return list of pairs
     * @throws Exception
     */
    @Override
    public List<Pair> call() throws Exception {
        List<Pair> pairCombinationList = new ArrayList<>();
        //add the first pair, as the top of the graph, from which we will build the subsequent search
        pairCombinationList.add(headPair);
        //go through all the remaining pairs to find a unique combination of pairs
        allPairs.forEach(x -> {
            if (!pairCombinationList.stream().anyMatch(x2 -> (PairUtils.getPersonsFromPair(x2).contains(x.getP1())
                            || PairUtils.getPersonsFromPair(x2).contains(x.getP2())))) {
                pairCombinationList.add(x);
            }
        });
        return pairCombinationList;
    }
}
