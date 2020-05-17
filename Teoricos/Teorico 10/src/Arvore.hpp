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

    //Remover um elemento da Agenda a partir do nome
    void remover(string nome) {
        _remover(this->raiz, nome);

    }

    //Pesquisar um elemento na Arvore usando o nome
    bool pesquisar(string nome) {
        return _pesquisar(this->raiz, nome);
    }

    //Pesquisar um elemento na Arvore usando o CPF
    bool pesquisar(int cpf) {
        return _pesquisar(this->raiz, cpf);
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

            }
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

    //Percorrer a Arvore e remover um elemento pelo nome
    void _remover(NoArvore* i, string nome) {
        if(i == NULL) cerr << "Elemento nao encontrado!" << endl;
        else if(nome[0] < i->primeiraLetraNome) _remover(i->esq, nome);
        else if(nome[0] > i->primeiraLetraNome) _remover(i->dir, nome);
        else i->remover(nome);

    }

    //Percorrer elemento na Arvore pesquisando por nome
    bool _pesquisar(NoArvore* i, string nome) {
        bool resp = false;
        if(i != NULL && nome[0] < i->primeiraLetraNome) resp = _pesquisar(i->esq, nome);
        else if(nome[0] > i->primeiraLetraNome) resp = _pesquisar(i->esq, nome);
        else {
            resp = i->pesquisar(nome);
            return resp;

        }
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