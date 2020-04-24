class questao5 {

}

class Matriz extends Celula {
    public Celula inicio;
    public int tamLinha, tamColuna;

    public Matriz() {
        this.inicio = null;
        this.tamLinha = 0;
        this.tamLinha = 0;
    }

    public Matriz(int tamLinha, int tamColuna) {
        this.inicio = null;
        this.tamLinha = tamLinha;
        this.tamColuna = tamColuna;
    }

    public void inserir(int[] insert) {
        
        Celula tmp = null;
        Celula anterior = null;
        int contador = 0;
        for(int j : insert) {
            tmp = new Celula(j);
            anterior.prox = tmp;
            tmp.ant = anterior;
            contador++;
            if(contador == this.tamLinha) {
                
                contador = 0;
            }
        }

    }

    public Celula remover() {

    }

    public void mostrarDiagonalPrincipal() {
        if(this.tamLinha == this.tamColuna) {
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
        if(this.tamLinha == this.tamColuna) {
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
        Matriz saida = null;

        if(this.tamLinha == this.tamColuna && matriz.tamLinha == matriz.tamColuna && this.tamLinha == matriz.tamLinha) {
          saida = new Matriz(this.tamLinha, this.tamColuna);

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
      Matriz saida = null;

      if(this.tamLinha == this.tamColuna && matriz.tamLinha == matriz.tamColuna && this.tamLinha == matriz.tamLinha) {
        saida = new Matriz(this.tamLinha, this.tamColuna);

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

class Celula {
    protected Celula prox, ant;
    protected Celula sup, inf;
    protected int elemento;

    public Celula() {
        this(0);
    }

    public Celula(int elemento) {
        this.prox = this.ant = null;
        this.sup = this. inf = null;
        this.elemento = elemento;
    }
}