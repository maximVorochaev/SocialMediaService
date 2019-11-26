package com.example.socialmediaservice.service;

import com.example.socialmediaservice.model.Pair;
import com.example.socialmediaservice.model.Person;
import com.example.socialmediaservice.task.CombinationFinder;
import com.example.socialmediaservice.util.PairUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Service that contains the logic of finding the best combination of pairs
 */
@Service
public class SocialMediaService {

    //the number of threads in the pool
    @Value("${thread.number}")
    private int threadsNumber;

    private static Logger logger = LogManager.getLogger();

    /**
     * Get the best combination of pairs
     *
     * @param persons - list of all persons
     * @return list of pairs
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public List<Pair> getPairs(List<Person> persons) throws ExecutionException, InterruptedException {
        //list to store all possible pairs
        List<Pair> allPairs = getAllPairs(persons);


//        logger.info("All possible pairs");
//        allPairs.forEach(x -> logger.info(x.getP1().getName() + " - " + x.getP2().getName() + " : " + x.getSumPoints()));

        //resulting pair combination
        List<Pair> resultPairs = new ArrayList<>();

        //Runtime.getRuntime().availableProcessors();

        System.out.println("КОЛИЧЕСТВО ЯДЕР: " + Runtime.getRuntime().availableProcessors());

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadsNumber);
        //list to store all tasks
        List<CombinationFinder> combinationFinders = new ArrayList<>();

        for (Pair pair : allPairs) {
            combinationFinders.add(new CombinationFinder(pair, allPairs));
        }

        List<Future<List<Pair>>> tmp = executor.invokeAll(combinationFinders);

        tmp.forEach(future -> {
            try {
                List<Pair> tempPairs = future.get();
                if (isCombinationBetter(resultPairs, tempPairs)) {
                    resultPairs.clear();
                    resultPairs.addAll(tempPairs);
                }
            } catch (InterruptedException e) {
                logger.warn("InterruptedException", e);
            } catch (ExecutionException e) {
                logger.warn("ExecutionException", e);
            }
        });

        executor.shutdown();

//        logger.info("Result pairs");
//        resultPairs.forEach(x -> logger.info(x.getP1().getName() + " - " + x.getP2().getName() + " : " + x.getSumPoints()));

        return resultPairs;
    }


    /**
     * Get all possible pairs from all persons
     *
     * @param persons - list of all persons
     * @return all possible pairs
     */
    private List<Pair> getAllPairs(List<Person> persons) {
        List<Pair> allPairs = new ArrayList<>();

        for (int i = 0; i < persons.size(); i++) {
            for (int j = i + 1; j < persons.size(); j++) {
                int sumPoints = PairUtils.countPairsPoints(persons.get(i), persons.get(j));

                //get all possible pairs
                if (sumPoints != 0) {
                    allPairs.add(Pair.builder()
                            .p1(persons.get(i))
                            .p2(persons.get(j))
                            .sumPoints(sumPoints)
                            .build());
                }
            }
        }

        return allPairs;
    }

    /**
     * Check size and the sum of the points of the intermediate list and the resultant
     *
     * @param resultPairs - result list of pairs
     * @param tempPairs   - temporary list of pairs
     * @return true if combination of pairs from tempPairs is better than combination from resultPairs
     * otherwise - false
     */
    private boolean isCombinationBetter(List<Pair> resultPairs, List<Pair> tempPairs) {
        return CollectionUtils.isEmpty(resultPairs)
                || tempPairs.size() > resultPairs.size()
                || resultPairs.stream()
                .mapToInt(x -> x.getSumPoints()).sum() < tempPairs.stream().mapToInt(x -> x.getSumPoints()).sum();
    }
}
