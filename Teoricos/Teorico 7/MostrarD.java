public void mostrarDiagonalPrincipal() {
    if(this.linha == this.coluna) {
        Celula tmp = this.inicio;
        System.out.print("[ ");
        _mostrarDiagonalPrincipal(tmp);
        System.out.println("]");
        tmp = null;
    }
}

private void _mostrarDiagonalPrincipal(Celula i) {
    if(i != null){
        System.out.print(i.elemento + " ");
        _mostrarDiagonalPrincipal(i.prox.inf);
    }
}
