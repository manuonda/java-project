package com.programandoenjava;

public interface A {
  default void mostrar(){
    System.out.println("Hola desde A");
  }
}
