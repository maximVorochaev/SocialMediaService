package com.example.socialmediaservice.util;


import com.example.socialmediaservice.model.Pair;
import com.example.socialmediaservice.model.Person;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class PairUtilsTest {

    @Test
    public void should_GetTwoPoints_When_PassPersonsWithTwoCommonInterests() {
        List<Person> persons = Arrays.asList(
                Person.builder().name("Jane").interests(Arrays.asList("movies", "music")).build(),
                Person.builder().name("Peter").interests(Arrays.asList("music", "movies")).build());

        Assert.assertEquals(2, PairUtils.countPairsPoints(persons.get(0), persons.get(1)));
    }

    @Test
    public void should_GetListWithTwoPersons_When_PassPair() {
        Pair pair = Pair.builder()
                .p1(Person.builder().build())
                .p2(Person.builder().build())
                .build();

        Assert.assertEquals(2, PairUtils.getPersonsFromPair(pair).size());
    }

}
