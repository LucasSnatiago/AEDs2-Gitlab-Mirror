#ifndef Arvore_hpp
#define Arvore_hpp

#include <iostream>
#include "NoArvore.hpp"
#include "NoContato.hpp"
using namespace std;

class Arvore {
public:
    NoArvore *raiz;

    //Criacao de uma Arvore 
    Arvore() {
        this->raiz = NULL;
    }

    //Criacao de uma Arvore usando seed
    Arvore(char seed[]) {
        this->raiz = NULL;
        for(char i : seed) _seed(i, this->raiz);
    }

    //Mostrar elementos da Arvore
    void mostrar() {
        this->_mostrar(this->raiz);
        cout << "----------------------------" << endl;
    }

    //Inserir contato na Arvore
    void inserir(string nome, string email, int telefone, int cpf) {
        NoContato *noContato = new NoContato(nome, email, telefone, cpf);
        
        if(this->raiz == NULL) {
            this->raiz = new NoArvore();
            this->raiz->inserir(noContato);

        } else _inserir(noContato, this->raiz);

    }

private:
    //Criacao de uma arvore a partir de uma seed
    void _seed(char semente, NoArvore *i) {
        if(this->raiz == NULL) {
            this->raiz = new NoArvore();
            this->raiz->primeiraLetraNome = semente; 

        } else {
            if(i->primeiraLetraNome < semente) {
                if(i->esq == NULL) i->esq = new NoArvore(semente);
                else _seed(semente, i->esq);

            } else if(i->primeiraLetraNome > semente) {
                if(i->dir == NULL) i->dir = new NoArvore(semente);
                else _seed(semente, i->dir);

            } else cerr << "Erro ao inserir semente repetida: " << semente << endl;

        }
    }

    //Percorrer a arvore para inserir um contato
    void _inserir(NoContato *inserir, NoArvore *i) {
        if(inserir->contato->nome[0] < i->primeiraLetraNome) {
            if(i->esq == NULL) i->esq = new NoArvore(inserir);
            else _inserir(inserir, i->esq);

        } else if(inserir->contato->nome[0] > i->primeiraLetraNome) {
            if(i->dir == NULL) i->dir = new NoArvore(inserir);
            else _inserir(inserir, i->dir);

        } else cerr << "Impossivel inserir " 
                    << inserir->contato->nome 
                    << " nome repetido!" << endl;
    }

    //Percorrer arvore escrevendo valor contido nela
    void _mostrar(NoArvore *i) {
        if(i != NULL) {
           _mostrar(i->esq);
           cout << "----------------------------" << endl;
           i->mostrar();
           _mostrar(i->dir); 
        }
    }
};

#endif