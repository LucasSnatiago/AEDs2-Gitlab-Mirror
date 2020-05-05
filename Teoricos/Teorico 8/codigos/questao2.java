//Funcao que retorna o numero de elementos pares presentes na arvore
public int numElementosPares() {
    return _numElementosPares(this.raiz);
}

//Funcao que percorre a arvore somando os elementos presentes nela
private int _numElementosPares(No i) {
    int numPares = 0;

    if(i.esq != null) numPares += _somarElementos(i.esq);
    if(i.dir != null) numPares += _somarElementos(i.dir);

    if(i.elemento % 2 == 0) numPares++;

    return numPares;
}