#ifndef BUBBLESORT_H
#define BUBBLESORT_H
void bubbleSort(Ordenador* lista, int* numComparacoes, int* numMovimentacoes) {
    for (int i = (lista->numElementos - 1); i > 0; i--) {
        for (int j = 0; j < i; j++) {
            if (compararAlfabeto(lista->ordem[j]->anoNascimento->string, lista->ordem[j + 1]->anoNascimento->string) == 1) {
                Personagens* auxiliar = lista->ordem[j + 1];
                lista->ordem[j + 1] = lista->ordem[j];
                lista->ordem[j] = auxiliar;
                numMovimentacoes+=3;
            }
            numComparacoes++;
        }
    }
}
#endif
