package com.programandoenjava;


/**
 *Llamar a un constructor de la superclase

  Cuando una subclase tiene un constructor, 
  puede usar super() para 
  llamar al constructor de su clase padre. 
 */
public class Animal {

    String tipo;

    public Animal(String tipo) {
      this.tipo = tipo;
    } 

    void hacerSonido(){
        System.out.println("El animal hace un sonido");
    }
    
}

class Perro extends Animal {
    String raza;

    Perro(String tipo, String raza){
        super(tipo); //Llama al constructor de Animal 
        this.raza = raza;
    }

    void mostrarInfo(){
        System.out.println("Tipo: " + tipo + ", Raza: " + raza);
    }

    @Override
    void hacerSonido(){
        super.hacerSonido();
        System.out.println("El perro ladra");
    }
}
