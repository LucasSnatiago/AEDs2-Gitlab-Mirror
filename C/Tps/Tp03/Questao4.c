#include <stdio.h>
#include <stdlib.h>

#define bool short
#define true 1
#define false 0

#define DEBUGGING 0
#define DEBUGGING_COMPLETO 0

//Minhas Bibliotecas
#include "../../bibliotecas/String.h"
#include "../../bibliotecas/lerArquivos.h"
#include "../../bibliotecas/padroes.h"
#include "../../bibliotecas/Personagens.h"
#include "../../bibliotecas/Celula.h"
#include "../../bibliotecas/ListaFlexivel.h"

//Criar um personagem a partir de uma path
Personagens* personagemCarregado(String* entrada) {
    return carregarPersonagem(lerArquivo(entrada));
}

int main(void){

    ListaFlexivel* lista = new_ListaFlexivel();
    String *entrada = readString();

    while(!ehFimString(entrada)){

        inserir(lista, personagemCarregado(entrada));
        entrada = readString();

    }

    quicksort(lista);
    quicksort(lista);
    insertsortCordoCabelo(lista);
    mostrarListaFlexivel(lista);

    return 0;
}
