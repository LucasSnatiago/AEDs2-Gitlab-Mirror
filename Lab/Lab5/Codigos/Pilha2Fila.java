//Transforma uma pilha em uma fila
public Celula toFila(Celula topo){
    Celula saida = toFilaRecur(topo.prox, topo);
    topo = null;
    return saida;
}

private Celula toFilaRecur(Celula i, Celula anterior){
    Celula ultima = null;

    if(i.prox != null){
        ultima = toFilaRecur(i.prox, i);
        i.prox = anterior;
    }
    else
        ultima = i;
    
    return ultima;
}