#include <iostream>
#include <string>
#include "Arvore.hpp"
#include <string.h>

#define bool short
#define true 1
#define false 0

//Criacao de uma Agenda em C++
class Agenda {
protected:
    Arvore *arvore;

public:
    //Contruindo uma Agenda
    Agenda() {
        arvore = new Arvore();
    }

    //Contruindo a Arvore da agenda com uma semente de criacao
    Agenda(char seed[]) {
        arvore = new Arvore(seed);
    }

    //Inserir um novo contato na Agenda
    void inserir(string nome, string email, int telefone, int cpf) {
        this->arvore->inserir(nome, email, telefone, cpf);
    }

    //Remover elemento da Agenda
    void remover(string nome) {
        this->arvore->remover(nome);
    }

    //Pesquisar elemento na Agenda pelo nome
    bool pesquisar(string nome) {
        return this->arvore->pesquisar(nome);
    }

    //Pesquisar elemento na Agenda pelo CPF
    bool pesquisar(int cpf) {
        return this->arvore->pesquisar(cpf);
    }

    //Mostrar contatos da Agenda
    void mostrar() {
        this->arvore->mostrar();
    }
};