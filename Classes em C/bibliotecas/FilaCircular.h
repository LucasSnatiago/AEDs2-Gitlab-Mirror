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
}FilaCircular;


FilaCircular* new_FilaCircular(int tamFilaCircular){  //Criador de FilaCirculars
    FilaCircular* resp;

    resp = (FilaCircular*) malloc(sizeof(FilaCircular));
    Personagens** mlista = (Personagens**) malloc(sizeof(Personagens) * tamFilaCircular);
    resp->lista = mlista;
    resp->tam = tamFilaCircular;
    resp->posI = 0;
    resp->posF = 0;

    return resp;
} 

void printarPeso(Personagens* personagem){  //Printar o peso de um personagem

    printf("%d\n", (int)personagem->peso);

}

void printarRemocao(Personagens* personagem){  //Printar um personagem que foi removido

    printf("(R) ");
    escreverString(personagem->nome);
    freePersonagem(&personagem);

}

void inserirFC(FilaCircular* filacircular, Personagens* personagem){  //Adicionar um elemento no final de uma FilaCircular

    int posFinal = ((filacircular->posF+1) % filacircular->tam);
    int posPersonagemPrintar = (filacircular->posF) % filacircular->tam;
    printf("%d\n", posPersonagemPrintar);
    Personagens* personagemPrintar = filacircular->lista[posPersonagemPrintar];
    if(personagemPrintar == NULL) printf("Esse ojeto nao existe!\n");

    if(posFinal == filacircular->posI){
        printarRemocao(personagemPrintar);
        filacircular->posF = (filacircular->posF+1) % filacircular->tam;
        filacircular->lista[filacircular->posF] = personagem;
    }else{
        filacircular->posF++;
        filacircular->lista[filacircular->posF] = personagem;
        printarPeso(filacircular->lista[filacircular->posF]);
    }

}


void removerFC(FilaCircular* filacircular){  //Remover personagem do final de uma lista

    if(filacircular->posI == filacircular->posF){
        
        printf("Erro na remocao da fila circular");
    
    }else{

        printarRemocao(filacircular->lista[filacircular->posI]);
        filacircular->posI++;

    }

    

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

