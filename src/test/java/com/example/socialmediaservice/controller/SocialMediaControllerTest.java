package com.example.socialmediaservice.controller;

import com.example.socialmediaservice.model.Pair;
import com.example.socialmediaservice.model.Person;
import com.example.socialmediaservice.service.SocialMediaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class SocialMediaControllerTest {

    private MockMvc mvc;

    @Mock
    private SocialMediaService socialMediaService;

    @InjectMocks
    private SocialMediaController socialMediaController;

    private List<Person> persons;

    @Before
    public void setup() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(socialMediaController).build();

        persons = Arrays.asList(
                Person.builder().name("Jane").interests(Arrays.asList("movies", "music")).build(),
                Person.builder().name("Peter").interests(Arrays.asList("music", "movies")).build());
    }

    @Test
    public void should_Returns200_whenValidInput() throws Exception {
        mvc.perform( MockMvcRequestBuilders
                .post("/pairs")
                .content(asJsonString(persons))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void should_ReturnListOfPairs_When_PassJsonWithPersons() throws Exception {
        List<Pair> pairs = Arrays.asList(Pair.builder()
                .p1(persons.get(0))
                .p2(persons.get(1))
                .sumPoints(2)
                .build());

        given(socialMediaService.getPairs(anyList())).willReturn(pairs);

        MockHttpServletResponse response = mvc.perform( MockMvcRequestBuilders
                .post("/pairs")
                .content(asJsonString(persons))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getContentAsString()).isEqualTo(asJsonString(pairs));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
