package com.programandoenjava;



public class Pajaro extends Animal implements Volador{

    public Pajaro(String nombre){
        super(nombre);
    }
    @Override
    public void volar() {
        System.out.println("El pájaro " + nombre + " está volando");
    }

    @Override
    void hacerSonido() {
        System.out.println("El pájaro " + nombre + " hace chirridos");
    }
   

}

interface Volador {
    void volar();
}

abstract class Animal {
    String nombre;

    public Animal(String nombre){
        this.nombre = nombre;
    }

    public void mostrarNombre(){
        System.out.println("El nombre del animal es: " + nombre);
    }

    abstract  void hacerSonido();

    public void  comer(){
        System.out.println("El animal está comiendo");
    }

}


