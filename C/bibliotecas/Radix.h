void radixsort(Ordenador* vetor, int* numComparacoes, int* numMovimentacoes) {
    int tamanho = vetor->numElementos;
    int i;
    int maior = vetor->ordem[0]->altura;
    int exp = 1;

    Ordenador* aux = criarLista();

    for (i = 0; i < tamanho; i++) {
        *numComparacoes++;
        if (vetor->ordem[i]->altura > maior){
    	    maior = vetor->ordem[i]->altura;
            *numMovimentacoes++;
        }
    }

    while (maior/exp > 0) {
        int bucket[10] = { 0 };
        *numComparacoes++;
    	for (i = 0; i < tamanho; i++){
    	    bucket[(vetor->ordem[i]->altura / exp) % 10]++;
            *numComparacoes+=1;
        }
    	for (i = 1; i < 10; i++){
    	    bucket[i] += bucket[i - 1];
            *numMovimentacoes++;
        }
    	for (i = tamanho - 1; i >= 0; i--){
    	    aux->ordem[--bucket[(vetor->ordem[i]->altura / exp) % 10]] = vetor->ordem[i];
            *numMovimentacoes++;
        }
    	for (i = 0; i < tamanho; i++){
    	    vetor->ordem[i] = aux->ordem[i];
            *numMovimentacoes++;
        }
    	exp *= 10;
    }

    free(aux);
}
