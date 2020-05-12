#ifndef Contato_hpp
#define Contato_hpp

#include <iostream>
#include <string>

using namespace std; 

class Contato {
protected:
    string nome;
    string email;
    int telefone;
    int cpf;

public:
    //Criando um Contato
    Contato() {
        this->nome = "";
        this->email = "";
        this->telefone = 0;
        this->cpf = 0;
    }

    //Inserir um novo contato
    void inserir(string nome, string email, int telefone, int cpf) {
        this->nome = nome;
        this->email = email;
        this->telefone = telefone;
        this->cpf = cpf;
    }

    void mostrar() {
        cout << "----------" << endl;
        cout << "Nome: " << this->nome << endl << "Email: " << this->email << endl << "Telefone: " << this->telefone << endl << "CPF: " << this->cpf << endl;
        cout << "----------" << endl;
    }
};

#endif