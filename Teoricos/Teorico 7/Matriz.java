class Matriz extends Celula {
    public int linha;
    public int coluna;
    public Celula inicio;

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

    public Matriz somar(Matriz matriz) {
        Matriz saida;

        if(this.linha == this.coluna && matriz.linha == matriz.coluna && this.linha == matriz.linha) {
          saida = new Matriz(this.linha, this.coluna);

          Celula i = this.inicio;
          Celula j = matriz.inicio;
          Celula resp = saida.inicio;

          do {
            _somar(i, j, resp);
            i = i.inf;
            j = j.inf;
            resp = resp.inf;
          } while(i != null);
        }

        return saida;
    }

    private void _somar(Celula i, Celula j, Celula resp) {
      if(i != null){
        resp.elemento = i.elemento + j.elemento;
        _somar(i.prox, j.prox, resp.prox);
      }
    }

    public Matriz multiplicacao(Matriz matriz) {
      Matriz saida;

      if(this.linha == this.coluna && matriz.linha == matriz.coluna && this.linha == matriz.linha) {
        saida = new Matriz(this.linha, this.coluna);

        Celula i = this.inicio;
        Celula j = matriz.inicio;
        Celula resp = saida.inicio;

        do {
          _multiplicar(i, j, resp);
          i = i.inf;
          j = j.inf;
          resp = resp.inf;
        } while(i != null);
      }

      return saida;
    }

    private void _multiplicar(Celula i, Celula j, Celula resp) {
      if(i != null){
        resp.elemento = i.elemento * j.elemento;
        _multiplicar(i.prox, j.prox, resp.prox);
      }
    }

}
