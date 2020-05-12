#ifndef NoContato_hpp
#define NoContato_hpp

#include <iostream>
#include <string>
#include "Contato.hpp"

using namespace std;

class NoContato : public Contato {
protected:
    Contato elemento;
    NoContato *esq, *dir;

public:
    //Criacao de um No
    NoContato() {
        this->dir = this->esq = NULL;
    }

    //Criacao de um no com um valor
    NoContato(string nome, string email, int telefone, int cpf) {
        this->elemento.inserir(nome, email, telefone, cpf);
        this->dir = this->esq = NULL;
    }
};
#endif