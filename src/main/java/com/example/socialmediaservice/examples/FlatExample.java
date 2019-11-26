package com.example.socialmediaservice.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlatExample {

    public static void main(String[] args) {
        List initList = Arrays.asList(1,2,3, Arrays.asList(4,5,6, Arrays.asList(7,8,9,10)));

        List<Integer> resultList = new ArrayList<>();

//        fn(initList, resultList);

//        resultList.forEach(System.out::println);
        fn1(initList).forEach(System.out::println);

        Arrays.asList("we", 1).stream().filter(x -> x instanceof Integer).collect(Collectors.toList()).forEach(System.out::println);
    }


    public static void fn(List<Object> objects, List<Integer> resultList) {
        objects.forEach(o -> {
            if(o instanceof Integer) {
                resultList.add((Integer) o);
            } else {
                fn((List<Object>) o, resultList);
            }
        });
    }

    public static List<Integer> fn1(List<Object> objects) {
        List<Integer> tmp = new ArrayList<>();
        objects.forEach(o -> {
            if(o instanceof Integer) {
                tmp.add((Integer) o);
            } else {
                tmp.addAll(fn1((List<Object>) o));
            }
        });
        return tmp;
    }
}
