#include <iostream>
#include <string>
#include "NoContato.hpp"
#include <string.h>

//Criacao de uma Agenda em C++
class Agenda {
protected:
    NoContato *raiz;

public:
    //Contruindo uma Agenda
    Agenda() {
        raiz = NULL;
    }

    //Funcao para mostrar todos os contatos da Agenda
    void mostrar() {
        this->_mostrar(this->raiz);
        cout << "----------------------------" << endl;
    }

    void inserir(string nome, string email, int telefone, int cpf) {
        NoContato *inserir = new NoContato(nome, email, telefone, cpf);

        if(this->raiz == NULL) this->raiz = inserir;
        else _inserir(this->raiz, inserir);

    }

private:

    //Metodo de percorrer a arvore inserindo elementos
    void _inserir(NoContato *i, NoContato *inserir) {
        if(i != NULL){
            if(i->elemento.nome.compare(inserir->elemento.nome) > 0) {
                if(i->esq == NULL) i->esq = inserir;
                else _inserir(i->esq, inserir);

            } else if (i->elemento.nome.compare(inserir->elemento.nome) < 0) {
                if(i->dir == NULL) i->dir = inserir;
                else _inserir(i->dir, inserir);

            } else cerr << "Elemento: " << i->elemento.nome.c_str() << "! Já existente na arvore" << endl;

        }
    }

    //Mostrar elementos da Agenda
    void _mostrar(NoContato *i) {
        if(i != NULL) {
           _mostrar(i->esq);
           cout << "----------------------------" << endl;
           i->elemento.mostrar();
           _mostrar(i->dir); 
        }
    }
};


int main(int argc, char *argv[]) {
    Agenda agenda;

    char entrada[256];
    cin.getline(entrada, 256);

    cout << entrada;

    agenda.mostrar();  
}