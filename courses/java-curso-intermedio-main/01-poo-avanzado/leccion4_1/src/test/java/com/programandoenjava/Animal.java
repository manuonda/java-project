package com.programandoenjava;

public abstract class Animal {

    String nombre;
    public Animal(String nombre){
        this.nombre = nombre;
    }

    abstract void hacerSonido();

    public void dormir(){
        System.out.println("El animal esta durmiendo");
    }
}

class Perro extends Animal {
    public Perro(String nombre){
        super(nombre);
    }

    @Override
    void hacerSonido(){
        System.out.println("Guau Guau");;
    }
}




