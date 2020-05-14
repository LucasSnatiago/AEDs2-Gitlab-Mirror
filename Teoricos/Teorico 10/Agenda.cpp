#include <iostream>
#include <string>
#include "Arvore.hpp"
#include <string.h>

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

    //Mostrar contatos da Agenda
    void mostrar() {
        this->arvore->mostrar();
    }
};


int main(int argc, char *argv[]) {
    Agenda agenda;

    char entrada[256];
    cin.getline(entrada, 256);

    cout << entrada;

    agenda.mostrar();  
}