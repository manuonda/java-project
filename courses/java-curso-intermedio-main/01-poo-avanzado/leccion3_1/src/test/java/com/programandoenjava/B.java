package com.programandoenjava;

public interface B {
  
    default  void mostrar(){
        System.out.println("Hola desde B");
    }
}
