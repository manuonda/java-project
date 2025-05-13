package com.programandoenjava;


/**
 * Llamar a otro constructor de la misma clase

Se puede utilizar this() para invocar otro constructor 
de la misma clase y evitar repetir codigo 
 */
public class Coche {

    String marca;
    int year;
    
     Coche(String marca, int year){
        this.marca = marca;
        this.year = year;
    }

    public Coche(String marca) {
      this(marca, 2023);
    }

    void mostrarCoche(){
        System.out.println("Marca : "+ marca + " year : " + year);
    }
}
