//Trocar dois itens de posicao
void _swapQuick(Ordenador* lista, int pos1, int pos2){
    Personagens* tmp = lista->ordem[pos1];
    lista->ordem[pos1] = lista->ordem[pos2];
    lista->ordem[pos2] = tmp;
}


void _quicksortRec (Ordenador* lista, int esq, int dir, int * numComparacoes) {
   int i = esq, j = dir;
   Personagens* pivo = lista->ordem[(dir+esq)/2];

   while (i <= j) {

        while (strcmp(lista->ordem[i]->corDoCabelo->string, pivo->corDoCabelo->string) < 0){
                
            i++;
            numComparacoes++;

        }

        while (strcmp(lista->ordem[j]->corDoCabelo->string, pivo->corDoCabelo->string) > 0){
            
            j--;
            numComparacoes++;

        }

        if (i <= j) {
            _swapQuick(lista, i, j);
            i++;
            j--;
        }

    }

   if (esq < j)  _quicksortRec(lista, esq, j, numComparacoes);
   if (i < dir)  _quicksortRec(lista, i, dir, numComparacoes);
}


//Quicksort
void quicksort(Ordenador* lista, int* numComparacoes){
    _quicksortRec(lista, 0, lista->numElementos-1, numComparacoes);
}

