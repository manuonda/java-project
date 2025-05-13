package com.programandoenjava;

public class App {

    public static void main(String[] args) {
        MiClase obj = new MiClase();
        obj.metodoPorDefecto();

        MiClasePersonalizada obj2 = new MiClasePersonalizada();
        obj2.metodoPorDefecto();

        Coche coche = new Coche();
        coche.arrancar();

        MiClaseTwo obj3= new MiClaseTwo();
        obj3.mostrar();
    }
}
