//Insertion
void insertionSortA(Ordenador* lista, int n){
    int i, j;
    Personagens* key;
    for (i = 1; i < n; i++) {
        key = lista->ordem[i];
        j = i - 1;
        while (j >= 0 && strcmp(lista->ordem[j]->anoNascimento->string, key->anoNascimento->string) > 0) {
            lista->ordem[j + 1] = lista->ordem[j];
            j = j - 1;
        }
        lista->ordem[j + 1] = key;
    }
}