//Funcao que retorna a soma de todos os inteiros de uma arvore
public int somarElementos() {
    return _somarElementos(this.raiz);
}

//Metodo privado que percorre a arvore e soma os elementos
private int _somarElementos(No i) {
    int soma = 0;

    if(i.esq != null) soma += _somarElementos(i.esq);
    if(i.dir != null) soma += _somarElementos(i.dir);

    soma += i.elemento;

    return soma;
}