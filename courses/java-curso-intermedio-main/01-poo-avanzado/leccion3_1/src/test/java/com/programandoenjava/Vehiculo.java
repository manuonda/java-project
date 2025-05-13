package com.programandoenjava;

public interface Vehiculo {

    default void arrancar(){
        System.out.println("El vehiculo arrancando...");
    }
}
