package com.programandoenjava;


interface Mensaje {
    void mostrarMensaje();
}

abstract  class Animal {
    abstract void sonido();
}

public class Main {

    public static void main(String[] args) {
        Mensaje obj = new Mensaje() {
            public void mostrarMensaje(){
                System.out.println("Hola desde una clases anonima");
            }
        };
        obj.mostrarMensaje();

        Animal animal = new Animal() {
            void sonido(){
                System.out.println("El animal hace un sonido");
            }
        };
        animal.sonido();
    }
}
