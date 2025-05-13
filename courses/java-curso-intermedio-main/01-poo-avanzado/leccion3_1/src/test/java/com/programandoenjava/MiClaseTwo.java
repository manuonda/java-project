package com.programandoenjava;

public class MiClaseTwo implements  A, B{
    @Override
    public void mostrar(){
        System.out.println("Metodo sobrescrito para evitar el conflicto");
    }

}
