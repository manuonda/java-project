package com.algoritmo;

/**
 * Given an m x n integer matrix matrix,
 * if an element is 0, set its entire row and column to 0's.
 * 
 * Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: [[1,0,1],[0,0,0],[1,0,1]]
 * 
 * Solution :
 * Para resolver el problema de establecer ceros en la matriz de manera
 * eficiente,
 * utilizamos la primera fila y la primera columna como banderas.
 * Estas banderas nos permiten identificar las filas y columnas que deben
 * ser llenadas con ceros sin usar espacio adicional.
 * 
 * Estrategia General
 * 
 * 1 - Usamos la primera fila y columna como banderas:
 * Si un elemento en (i, j) es 0, marcamos:
 * matrix[i][0] = 0 (fila i debe ser cero).
 * matrix[0][j] = 0 (columna j debe ser cero).
 * 
 * 2 - Procesamos las celdas basándonos en las banderas:
 * Si matrix[i][0] == 0 o matrix[0][j] == 0, establecemos matrix[i][j] = 0.
 * 
 * 3- Procesamos la primera fila y columna por separado al final:
 * Para evitar conflictos, al final revisamos si la primera fila y
 * columna originalmente debían ser ceros.
 */

public class ZeroMatrix {
    public void setZeroes(int[][] matrix) {

        boolean firstRowHasZero = hasFirstRowZero(matrix);
        boolean firstColumnHasZero = hasFirstColumnZero(matrix);
    }

    private boolean hasFirstColumnZero(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0)
                return true;
        }
        return false;
    }

    private boolean hasFirstRowZero(int[][] matrix) {
        for (int j = 0; j < matrix.length; j++) {
            if (matrix[0][j] == 0)
                return true;
        }
        return false;
    }

}
