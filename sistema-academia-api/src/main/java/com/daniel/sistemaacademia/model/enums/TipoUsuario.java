package com.daniel.sistemaacademia.model.enums;

public enum TipoUsuario {

    ALUNO(1),
    INSTRUTOR(2);

    private int valor;

    TipoUsuario(int valor) {
        this.valor = valor;
    }

    public int getValor(){
        return valor;
    }

}
