package com.example.socialmediaservice.controller;

import com.example.socialmediaservice.model.Pair;
import com.example.socialmediaservice.model.Person;
import com.example.socialmediaservice.service.SocialMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Controller containing persons request processing logic
 */
@RestController
public class SocialMediaController {

    @Autowired
    private SocialMediaService socialMediaService;

    /**
     * Process a request with persons from UI
     *
     * @param persons
     * @return list of pairs(the best combination of pairs)
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @RequestMapping(method = RequestMethod.POST, name = "pairs", produces = "application/json", consumes = "application/json")
    public ResponseEntity<List<Pair>> getPairs(@RequestBody List<Person> persons) throws ExecutionException, InterruptedException {
        return new ResponseEntity<>(socialMediaService.getPairs(persons), HttpStatus.OK);
    }
}
