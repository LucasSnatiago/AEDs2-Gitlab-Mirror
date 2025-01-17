#ifndef Contato_hpp
#define Contato_hpp

#include <iostream>
#include <string>

using namespace std; 

class Contato {
public:
    string nome;
    string email;
    int telefone;
    int cpf;

    //Criando um Contato
    Contato() {
        this->nome      = "";
        this->email     = "";
        this->telefone  = 0;
        this->cpf       = 0;
    }

    //Inserir um novo contato
    void inserir(string nome, string email, int telefone, int cpf) {
        this->nome       = nome;
        this->email      = email;
        this->telefone   = telefone;
        this->cpf        = cpf;
    }

    void mostrar() {
        cout << "Nome   - " << this->nome     << "\n"
                "Email  - " << this->email    << "\n"
                "Numero - " << this->telefone << "\n"
                "CPF    - " << this->cpf      << endl;
    }
};

#endif