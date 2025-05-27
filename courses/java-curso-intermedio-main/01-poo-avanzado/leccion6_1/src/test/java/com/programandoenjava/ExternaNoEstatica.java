package com.programandoenjava;

public class ExternaNoEstatica {
   private String mensaje = "Hola desde la clase externa no estatica";

   class Interna {
     void mostrar(){
        System.out.println(mensaje);
     }
   }
}


