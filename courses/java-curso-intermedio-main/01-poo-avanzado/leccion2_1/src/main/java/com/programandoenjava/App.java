package com.programandoenjava;

/**
 * Llamar a un constructor de la superclase
 *  Cuando una subclase tiene un constructor, 
 *   puede usar super() para llamar al constructor de su clase padre.
 */
public class App {
    public static void main(String[] args) {
       Persona persona = new Persona("Juan");
       persona.mostrarNombre();

       Coche coche1 = new Coche("Toyota");
       coche1.mostrarCoche();

       Perro miPerro = new Perro("Mamifero","Labrador");
       miPerro.mostrarInfo();

       miPerro.hacerSonido();

       Estudiante estudiante = new Estudiante();
       estudiante.mostrarNombre();
    }
}
