/*
    CRIACAO DO TIPO FilaCircular EM C
    Criado por Lucas Santiago
    Data de criacao: 17/02/20
    Versao: 1.0.0 - 17/02/20
    Changelog:
*/

#include <stdio.h>
#include <stdlib.h>

#ifndef DEBUGGING
    #define DEBUGGING 0
#endif

#if DEBUGGING == 1
    #define printf debug
#else
    #define debug //
#endif

typedef struct filacircular{
    Personagens** lista;
    int tam;
    int posI;
    int posF;
    int numElementos;
}FilaCircular;


FilaCircular* new_FilaCircular(int tamFilaCircular){  //Criador de FilaCirculars
    FilaCircular* resp;

    resp = (FilaCircular*) malloc(sizeof(FilaCircular));
    Personagens** mlista = (Personagens**) malloc(sizeof(Personagens) * tamFilaCircular);
    resp->lista = mlista;
    resp->tam = tamFilaCircular;
    resp->posI = 0;
    resp->posF = 0;
    resp->numElementos = 0;

    return resp;
} 

void printarAltura(Personagens* personagem){  //Printar o peso de um personagem

    printf("%d\n", (int)personagem->altura);

}

void escreverFilaCircular(FilaCircular* filacircular){  //escrever todos os itens da lista

    for(int i = 0; i < filacircular->numElementos; i++){
        printf("[%d] ", i);
        escreverPersonagens(filacircular->lista[(filacircular->posI + i) % filacircular->tam]);
    }

}

int mediaAlturaFilaCircular(FilaCircular* filacircular){  //Calcular a media entre as alturas
    int media = 0;

    //escreverFilaCircular(filacircular);

    for(int i = 0; i < filacircular->numElementos; i++)
        media += filacircular->lista[((filacircular->posI + i) % filacircular->tam)]->altura;

    float calc;
    if(filacircular->numElementos != 0) calc = (float)  media / filacircular->numElementos;
    
    int saida = (int) calc;
    if((int)(calc*10) % 10 > 4) saida++; 

    return saida;
}

void printarRemocao(Personagens* personagem){  //Printar um personagem que foi removido

    printf("(R) ");
    escreverString(personagem->nome);
    freePersonagem(&personagem);

}

void inserirFC(FilaCircular* filacircular, Personagens* personagem){  //Adicionar um elemento no final de uma FilaCircular

    if(filacircular->lista[filacircular->posF] != NULL){

        filacircular->lista[filacircular->posF] = personagem;
        //filacircular->posI = (filacircular->posI + 1) % filacircular->tam;

    }else{

        filacircular->lista[filacircular->posF] = personagem;
        filacircular->numElementos++;

    }

    printf("%d\n", mediaAlturaFilaCircular(filacircular));
    filacircular->posF = (filacircular->posF + 1) % filacircular->tam;
    if(filacircular->posF == filacircular->posI) filacircular->posI = (filacircular->posI + 1) % filacircular->tam;

}


void removerFC(FilaCircular* filacircular){  //Remover personagem do final de uma lista

    printarRemocao(filacircular->lista[filacircular->posI]);
    filacircular->lista[filacircular->posI] = NULL;
    filacircular->numElementos--;
    filacircular->posI = (filacircular->posI + 1) % filacircular->tam;

}

void executarAcoes(String** entrada, FilaCircular* lista){  //inserir e remover elementos da lista
    String* inserir = stringBuilder((char*)"I");
    String* remover = stringBuilder((char*)"R");
    Personagens* personagem;
    String* lerArq;
    
    
    if(compararStrings(entrada[0], inserir)){
        lerArq = lerArquivo(entrada[1]);
        personagem = carregarPersonagem(lerArq);
        inserirFC(lista, personagem);
    }
    else if(compararStrings(entrada[0], remover)){
        removerFC(lista);
    }
}

