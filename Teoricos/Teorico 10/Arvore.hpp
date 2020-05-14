#ifndef Arvore_hpp
#define Arvore_hpp

#include <iostream>
#include "NoContato.hpp"
using namespace std;

class Arvore {
    NoContato *inicio, *fim;

    //Criacao de uma Arvore 
    Arvore() {
        this->inicio = this->fim = NULL;
    }

    //Criacao de uma Arvore usando seed
    Arvore(char seed[]) {
        this->inicio = this->fim = NULL;
        for(char i : seed) _seed(i, this->inicio);
    }

private:
    //Criacao de uma arvore a partir de uma seed
    void _seed(char semente, NoContato *i) {
        if(this->inicio == NULL) {
            this->inicio = new NoContato();
            this->inicio->semente = semente; 

        } else {
            if(i->elemento < semente) {
                

            } else if(i.elemento > semente) {


            } else cerr << "Erro ao inserir semente repetida: " << semente << endl;

        }
    }
};

#endif