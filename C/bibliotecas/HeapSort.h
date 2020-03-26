//Inverter a posicao de 2 elementos da lista
void swap(Ordenador* lista, int pos1, int pos2){
    Personagens* tmp = lista->ordem[pos1];
    lista->ordem[pos1] = lista->ordem[pos2];
    lista->ordem[pos2] = tmp;
}


void constroi(Ordenador* lista, int tamHeap){
    for(int i = tamHeap; i > 1 && lista->ordem[i]->altura > lista->ordem[i/2]->altura; i /= 2){
        swap(lista, i, i/2);
    }
}

void reconstroi(Ordenador* lista, int tamHeap){
   int i = 1, filho;
   while(i <= (tamHeap/2)){

        if (lista->ordem[2*i]->altura > lista->ordem[2*i+1]->altura || 2*i == tamHeap){
            filho = 2*i;
        } else {
            filho = 2*i + 1;
        }

        if(lista->ordem[i]->altura < lista->ordem[filho]->altura){
            swap(lista, i, filho);
            i = filho;
        }else{
            i = tamHeap;
        }
    }
}



// Algoritmo de ordenacao
void heapsort(Ordenador* lista) {
   //Alterar o vetor ignorando a posicao zero
   for(int i = lista->numElementos; i > 0; i--){
        lista->ordem[i] = lista->ordem[i-1];
   }

   //Contrucao do heap
   for(int tamHeap = 1; tamHeap <= lista->numElementos; tamHeap++){
        constroi(lista, tamHeap);
   }

   //Ordenacao propriamente dita
   int tamHeap = lista->numElementos;
   while(tamHeap > 1){
        swap(lista, 1, tamHeap--);
        reconstroi(lista, tamHeap);
    }

   //Alterar o vetor para voltar a posicao zero
   for(int i = 0; i < lista->numElementos; i++){
        lista->ordem[i] = lista->ordem[i+1];
   }
}
