package com.example.quizapplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solutions {
    public static List<String> question = Arrays.asList(
            "What is the fastest land animal?",
            "What was the most used programming language in 2023?",
            "What year did the dot com bubble begin to burst?",
            "Who was the first televised president?",
            "Which character is not from the simpsons"
    );

    public static List<List<String>> options = Arrays.asList(
            Arrays.asList("Cheetah", "Cougar", "Ostrich", "Horse"),
            Arrays.asList("Python", "JavaScript", "C++", "HTML"),
            Arrays.asList("1999", "2003", "2005", "2000"),
            Arrays.asList("Franklin Roosevelt", "Donald Trump", "Abraham Lincoln", "Joe Biden"),
            Arrays.asList("Homer", "Vince", "Lisa", "Kay")
    );

    public static List<String> correct = Arrays.asList(
            "Cheetah",
            "Python",
            "2000",
            "Franklin Roosevelt",
            "Kay"
    );
}
