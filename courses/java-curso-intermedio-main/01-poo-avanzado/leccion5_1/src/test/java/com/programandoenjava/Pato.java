package com.programandoenjava;

interface  Volador {
    void volar();
}

interface Nadador {
    void nadar();
}

public class Pato implements Volador, Nadador{

    @Override
    public void nadar() {
       System.out.println("El pato esta nadando"); 
    }

    @Override
    public void volar() {
       System.out.println("El pato esta volando");
    }

}


