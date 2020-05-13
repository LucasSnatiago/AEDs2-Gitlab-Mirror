#include <iostream>
#include <string>
#include "Erros.hpp"
#include "NoContato.hpp"

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

            } else aviso("Objeto nao inserido! Já existente na arvore");

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

    agenda.inserir("Teste1", "Teste1@example.com", 1342, 32423);
    agenda.inserir("teste2", "teste2@example.com", 4758, 52452);
    agenda.inserir("teste3", "teste3@example.com", 456987, 52452);
    agenda.inserir("teste1", "teste1@example.com", 13211485, 2525424);

    agenda.mostrar();  
}