#ifndef NoContato_hpp
#define NoContato_hpp

#include <iostream>
#include <string>
#include "Contato.hpp"

using namespace std;

class NoContato {
public:
    char semente;
    Contato elemento;
    NoContato *prox;

    //Criacao de um No
    NoContato() {
        this->prox = NULL;
    }

    //Criacao de um no com um valor
    NoContato(string nome, string email, int telefone, int cpf) {
        this->elemento.inserir(nome, email, telefone, cpf);
        this->prox = NULL;
    }
};
#endif