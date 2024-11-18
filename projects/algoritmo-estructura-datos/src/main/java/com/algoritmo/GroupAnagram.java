package com.algoritmo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array of strings strs, group the
 * anagrams
 * together. You can return the answer in any order.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * 
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 
 * Explanation:
 * 
 * There is no string in strs that can be rearranged to form "bat".
 * The strings "nat" and "tan" are anagrams as they can be rearranged to form
 * each other.
 * The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to
 * form each other.
 * 
 * Example 2:
 * 
 * Input: strs = [""]
 * 
 * Output: [[""]]
 * 
 * Example 3:
 * 
 * Input: strs = ["a"]
 * 
 * Output: [["a"]]
 * 
 */
public class GroupAnagram {

    /**
     * O (N log N)
     * 
     * @param strs
     * @return
     */

    public List<List<String>> groupAnagramsNotOptimized(String[] strs) {
        if (strs.length == 0)
            return new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] array = s.toCharArray();
            Arrays.sort(array);
            String key = String.valueOf(array);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);

        }
        return new ArrayList<>(map.values());
    }

    public List<List<String>> groupAnagrams(String[] args){
       if(args.length == 0 || args == null) return Collections.emptyList();

       Map<String, List<String>> maps = this.buildAnagramMap(args);
       return new ArrayList<>(maps.values());
    }

    public Map<String, List<String>> buildAnagramMap(String[] words) {
        Map<String, List<String>> map = new HashMap<>();
        for (String word : words) {
            String hash = getAnagramHash(word);
            if(!map.containsKey(hash)){
                map.put(hash, new ArrayList<>());
            }

            map.get(hash).add(word);
        }
        return map;
    }

    /**
     * Genera un hash único basado en la frecuencia de caracteres de una cadena.
     * Este hash es una representación en forma de arreglo que indica cuántas veces
     * aparece cada letra del alfabeto en la cadena de entrada.
     *
     * Pasos que realiza:
     * 1. Inicializa un arreglo de tamaño 26 (`letterCount`) para representar las
     * frecuencias de las letras de 'a' a 'z'.
     * 2. Recorre cada carácter de la cadena `s` y actualiza el conteo en el índice
     * correspondiente (calculado como `c - 'a'`).
     * 3. Convierte el arreglo de frecuencias (`letterCount`) a una representación
     * de cadena mediante `Arrays.toString()`.
     * 
     * Ejemplo:
     * - Entrada: "saco"
     * - Paso 1: Inicializa `letterCount` como [0, 0, 0, ..., 0].
     * - Paso 2: Para cada carácter:
     * - 's' incrementa `letterCount[18]` → [0, 0, ..., 1, ..., 0].
     * - 'a' incrementa `letterCount[0]` → [1, 0, ..., 1, ..., 0].
     * - 'c' incrementa `letterCount[2]` → [1, 0, 1, ..., 1, ..., 0].
     * - 'o' incrementa `letterCount[14]` → [1, 0, 1, ..., 1, ..., 1].
     * - Paso 3: Devuelve la cadena "[1, 0, 1, ..., 1, ..., 1]".
     * 
     * Propósito:
     * - Las palabras que son anagramas tendrán la misma salida, ya que comparten la
     * misma frecuencia de caracteres.
     * 
     * @param s La cadena de entrada.
     * @return Una representación en forma de cadena del arreglo de frecuencias de
     *         caracteres.
     */
    private String getAnagramHash(String s) {
        // Inicializa un arreglo de tamaño 26 para las letras del alfabeto
        int[] letterCount = new int[26];

        // Recorre la cadena y cuenta las ocurrencias de cada letra
        for (int c : s.toCharArray()) {
            letterCount[c - 'a']++; // Incrementa el índice correspondiente
        }

        // Devuelve el arreglo como una cadena
        return Arrays.toString(letterCount);
    }

}
