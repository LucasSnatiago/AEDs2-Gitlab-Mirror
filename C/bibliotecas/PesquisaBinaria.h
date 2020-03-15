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


//Pesquisa sequencial de elementos
bool pesquisaSequencial(Ordenador* lista, String* nome){
    

    for(int i = 0; i < lista->numElementos; i++){
        printf("%d", compararAlfabeto(lista->ordem[i]->nome->string, nome->string));
    }

    return 1;
}


bool pesquisar(Ordenador* lista, String* nome, int* numComparacoes){  //Funcao para fazer uma pesquisa binaria dentro de uma lista
    bool achou = false;

    int posI = 0;
    int posF = lista->numElementos-1;

    int pos;

    while(posI <= posF){

        pos = (posI + posF) / 2;

        if(strcmp(lista->ordem[pos]->nome->string, nome->string) < 0){

            posI = pos+1;
            numComparacoes++;

        }else if(strcmp(lista->ordem[pos]->nome->string, nome->string) > 0){

            posF = pos-1;
            numComparacoes++;

        }else if(strcmp(lista->ordem[pos]->nome->string, nome->string) == 0){

            posI = lista->numElementos;
            numComparacoes++;
            achou = true;

        }

    }

    return achou;
}
