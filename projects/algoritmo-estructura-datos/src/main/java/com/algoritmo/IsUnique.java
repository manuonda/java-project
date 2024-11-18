package com.algoritmo;

import java.util.HashSet;
import java.util.Set;


/**
 * Dado un metodo que recibe String, comprobar si todos los caracteres son unicos 
 * o no.
 * isUnique("abcde") => true
 * isUnique("abcdea") => false
 * 
 * Complejidad con for:
 * a: b c d e
 * b: c d e 
 * 
 * 0(n al 2)
 * 
 * Tabla Hash: solution
 * 
 * for: logaritmo  O(N)
 * Consulta el table 0(1)
 * 
 * ----
 * 0(1)
 */
public class IsUnique {

    private static int NUMBER_OF_CHARS =  128;
    public static boolean isUnique(String s ){
       
        if( s.length() > NUMBER_OF_CHARS) return false;
        Set<Character> characters = new HashSet<>();

        for(Character c : s.toCharArray()){
           if( characters.contains(c)) return false;
           characters.add(c);
        }
        return true;
    }
}
