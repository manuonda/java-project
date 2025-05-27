package com.programandoenjava;

public class Main {

    public static void main(String[] args) {
        Externa.Interna obj = new Externa.Interna();
        obj.mostrar();

        ExternaNoEstatica externaNOEstatica = new ExternaNoEstatica();
        ExternaNoEstatica.Interna interna = externaNOEstatica.new Interna();
        interna.mostrar();
    }
}
