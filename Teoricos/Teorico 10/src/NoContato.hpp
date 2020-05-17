#ifndef NoContato_hpp
#define NoContato_hpp

#include <iostream>
#include <string>
#include "Contato.hpp"

using namespace std;

class NoContato {
public:
    Contato *contato;
    NoContato *prox;

    //Criacao de um No
    NoContato() {
        this->prox = NULL;
    }

    //Criacao de um no com um valor
    NoContato(string nome, string email, int telefone, int cpf) {
        this->contato->inserir(nome, email, telefone, cpf);
        this->prox = NULL;
    }

    //Criar um novo contato
    void inserir(Contato *inserir) {
        contato = inserir;
    }
};
#endif