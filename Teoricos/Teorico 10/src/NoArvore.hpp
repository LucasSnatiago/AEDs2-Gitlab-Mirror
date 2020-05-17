#ifndef NoArvore_hpp
#define NoArvore_hpp

#include <iostream>
#include "NoContato.hpp"
using namespace std;

class NoArvore {
public:
    char primeiraLetraNome;
    NoContato *inicio, *fim;
    NoArvore *esq, *dir;

    //Inicializando um No da Arvore da Agenda
    NoArvore() {
        this->esq = this->dir = NULL;
        this->inicio = this->fim = NULL;
    }

    //Criando um no de Arvore com seed
    NoArvore(char seed) {
        this->esq = this->dir = NULL;
        this->inicio = this->fim = NULL;
        this->primeiraLetraNome = seed;
    }

    //Inserir novos elementos na fila de contatos
    void inserir(NoContato *inserir) {
        if(this->inicio == NULL) { 
            this->inicio = this->fim = inserir;
            this->primeiraLetraNome = inserir->contato->nome[0];

        } else {
            this->fim->prox = inserir;
            this->fim = this->fim->prox;

        }
    }

    //Pesquisar elemento pelo nome
    bool pesquisar(string nome) {
        return _pesquisar(this->inicio, nome);
    }

    //Remover elemento da Agenda
    void remover(string nome) {
        _remover(this->inicio, nome);
    }

    //Mostrar elementos presentes na lista de contatos
    void mostrar() {
        _mostrar(this->inicio);
    }

private:
    //Remover elemento da lista
    void _remover(NoContato *i, string nome) {
        if(i == NULL) cerr << "Elemento nao encontrado!" << endl;
        else if(i->prox->contato->nome.compare(nome) == 0) {
            NoContato *hold = i->prox->prox;
            free(i->prox);
            i->prox = hold;

        } 
    }

    //Percorrer a Lista procurando um elemento pelo nome
    bool _pesquisar(NoContato *i, string nome) {
        bool resp = false;
        
        if(i != NULL) resp = _pesquisar(i->prox, nome);
        else if(i->contato->nome.compare(nome) == 0) resp = true;
    
        return resp;
    }

    //Percorrer todos os contatos da lista mostrando na tela
    void _mostrar(NoContato *i) {
        if(i != NULL) {
            i->contato->mostrar();
            _mostrar(i->prox);
        }
    }
};

#endif