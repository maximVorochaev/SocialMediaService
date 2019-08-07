package com.example.socialmediaservice.service;

import com.example.socialmediaservice.model.Pair;
import com.example.socialmediaservice.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SocialMediaServiceTest {

    @InjectMocks
    private SocialMediaService socialMediaService;

    @Test
    public void should_GetTwoPairs_When_PassFourPersonsWithCommonInterests() throws ExecutionException, InterruptedException {
        List<Person> persons = Arrays.asList(
                Person.builder().name("Jane").interests(Arrays.asList("movies", "music")).build(),
                Person.builder().name("Peter").interests(Arrays.asList("cars", "movies")).build(),
                Person.builder().name("Dustin").interests(Arrays.asList("movies", "cars", "hiking", "music")).build(),
                Person.builder().name("Lana").interests(Arrays.asList("cars", "dancing")).build());

        List<Pair> pairs = socialMediaService.getPairs(persons);

        assertThat(pairs.size()).isEqualTo(2);
    }

    @Test
    public void should_GetEmptyList_When_PassPersonsWithoutCommonInterests() throws ExecutionException, InterruptedException {
        List<Person> persons = Arrays.asList(
                Person.builder().name("Jane").interests(Arrays.asList("qwe", "ert")).build(),
                Person.builder().name("Peter").interests(Arrays.asList("asd", "sdf")).build(),
                Person.builder().name("Dustin").interests(Arrays.asList("tyuyt", "cars", "hiking", "music")).build(),
                Person.builder().name("Lana").interests(Arrays.asList("sdfsdf", "tyuuyyt")).build());

        List<Pair> pairs = socialMediaService.getPairs(persons);

        assertThat(pairs.size()).isEqualTo(0);
    }
}
