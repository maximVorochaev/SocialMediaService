package com.example.socialmediaservice.model;

import lombok.*;

import java.util.List;

/**
 * Represents person
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {

    private String name;
    private List<String> interests;
}
