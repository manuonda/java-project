package com.algoritmo;

import java.util.List;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class GroupAnagramaTest {


    @Test
    public void groupAnagramaTest_not_optimized(){
        
        GroupAnagram groupAnagram = new GroupAnagram();
        List<List<String>> resultEmptyList = groupAnagram.groupAnagramsNotOptimized(new String[]{});
        assertTrue(resultEmptyList.isEmpty());
        
        String[] array = new String[]{"saco","arresto","programa","rastreo","caso"};
        List<List<String>> anagrams = groupAnagram.groupAnagramsNotOptimized(array);
        assertTrue(anagrams.size() == 3);
        assertTrue(containsAll(anagrams, List.of("programa")));
        assertTrue(containsAll(anagrams, List.of("caso","saco")));     
   
    }

    private boolean containsAll(List<List<String>> anagrams, List<String> group){
        anagrams.forEach(anagram -> System.out.println(anagram));
        for(List<String> g: anagrams){
            if(g.size() == group.size() && g.containsAll(group)) return true;
        }
        return false;
    }
}
