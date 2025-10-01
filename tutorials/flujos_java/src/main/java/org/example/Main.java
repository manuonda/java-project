package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        List<Movie> movies = Arrays.asList(
            new Movie("Inception", 8.9, true),
            new Movie("The dark knigh",9.0, true),
            new Movie("Interestellar", 8.6, false)
        );
        Movie movie = movies.stream().filter( m -> m.rating() > 8.5).findAny().orElse(null);
        System.out.println(movie);
        boolean anyMovie = movies.stream().anyMatch(m -> m.rating() > 8.5);
        System.out.println(anyMovie);
        boolean allMovie = movies.stream().allMatch(m -> m.rating() > 8.5);
        System.out.println(allMovie);
        boolean noneMovie = movies.stream().noneMatch(m -> m.rating() > 8.5);
        System.out.println(noneMovie);
    }
}