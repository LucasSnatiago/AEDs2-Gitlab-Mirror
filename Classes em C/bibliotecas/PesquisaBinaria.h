/*
    CRIACAO DA ESTRUTURA DE PESQUISA BINARIA EM C
    Criado por Lucas Santiago
    Data de criacao: 11/03/20
    Versao: 1.0.0
*/

#define bool short
#define true 1
#define false 0

#define DEBUGGING 0
#define DEBUGGING_COMPLETO 0

#ifndef DEBUGGING
    #define DEBUGGING 0
#endif

#ifndef DEBUGGING_COMPLETO
    #define DEBUGGING_COMPLETO 0
#endif

//Debug de codigo
#if DEBUGGING == 1
    #define debug printf 
#else
    #define debug //
#endif

#if DEBUGGING_COMPLETO == 1
    #define debugcompleto printf
#else
    #define debugcompleto //
#endif



bool pesquisar(Ordenador* lista, String* nome, int* numComparacoes){  //Funcao para fazer uma pesquisa binaria dentro de uma lista
    bool achou = false;

    int pos = lista->numElementos / 2;
    int tamElementos = lista->numElementos;
    int flagSaida = false;

    while(!flagSaida){

        if(compararAlfabeto(lista->ordem[pos]->nome, nome) == 1){

            pos /= 2;
            tamElementos /= 2;
            numComparacoes++;

        }else if(compararAlfabeto(lista->ordem[pos]->nome, nome) == 2){

            pos *= 2;
            tamElementos /= 2;
            numComparacoes++;

        }else if(compararAlfabeto(lista->ordem[pos]->nome, nome) == 0){

            numComparacoes++;
            achou = true;
            flagSaida = true;

        }else if(tamElementos == 1) {

            flagSaida = true;

        }

    }

    return achou;
}
