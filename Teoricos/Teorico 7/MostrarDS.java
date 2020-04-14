public void mostrarDiagonalSecundaria() {
    if(this.linha == this.coluna) {
        Celula i = this.inicio;
        while(i.prox != null) i = i.prox;
        System.out.print("[ ");
        _mostrarDiagonalSecundaria(i);
        System.out.println("]");
        i = null;
    }
}

private void _mostrarDiagonalSecundaria(Celula i) {
    if(i != null){
        System.out.print(i.elemento + " ");
        _mostrarDiagonalSecundaria(i.ant.inf);
    }
}
