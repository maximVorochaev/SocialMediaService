package com.example.socialmediaservice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents pair of person
 */
@Getter
@Setter
@Builder
public class Pair {
    private Person p1;
    private Person p2;
    private int sumPoints;
}
