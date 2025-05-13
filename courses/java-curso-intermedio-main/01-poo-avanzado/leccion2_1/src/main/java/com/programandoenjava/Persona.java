package com.programandoenjava;


/**
 * La palabra clave this hace referencia a la instancia actual de la clase.
 *  Se usa para diferenciar atributos y métodos de la instancia 
 * cuando hay ambigüedad,  y para invocar otros constructores dentro de la misma clase.
 */
public class Persona {
   String nombre;

   Persona(String nombre){
      this.nombre = nombre;
   }

   void mostrarNombre(){
    System.out.println("Nombre: "+ this.nombre);
   }
}

class Estudiante extends Persona {
    String nombre = "Estudiante";

    Estudiante(){
        super("Juan");
    }

    @Override
    void mostrarNombre(){
        System.out.println("Nombre en Estudainte " + this.nombre);
        System.out.println("Nombre en Persona " + super.nombre);

    }
}


