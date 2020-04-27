class questao5 {
  public static void main(String[] args) {
    Matriz matriz1 = null;
    Matriz matriz2 = null;
    Matriz mult = null;
    int[] valores = new int[50];

    int entrada = MyIO.readInt();

    int lado1;
    int lado2;
    for (int i = 0; i < entrada; i++) {
      lado1 = MyIO.readInt();
      lado2 = MyIO.readInt();
      matriz1 = new Matriz(lado1, lado2);

      for(int j = 0; j < lado1*lado2; j++) {
        valores[j] = MyIO.readInt();
      }
      matriz1.inserir(valores);
	  
      lado1 = MyIO.readInt();
      lado2 = MyIO.readInt();
      matriz2 = new Matriz(lado1, lado2);

      for(int j = 0; j < lado1*lado2; j++) {
        valores[j] = MyIO.readInt();
      }
      matriz2.inserir(valores);


    //Prints finais da matriz
    matriz1.mostrarDiagonalPrincipal();
    matriz1.mostrarDiagonalSecundaria();
    matriz1.somar(matriz2).mostrarTudo();
    try{
      mult = multiplicacao(matriz1, matriz2);
    } catch (Exception err) {
      MyIO.println("Erro ao multiplicar!");
    }
    mult.mostrarTudo();
    }

  }

  //Multiplicar duas matrizes
  public static Matriz multiplicacao(Matriz m1, Matriz m2) throws Exception{

    if(m1.tamColuna != m2.tamColuna || m1.tamColuna != m2.tamLinha) 
        throw new Exception("Erro! As duas matrizes são de tamanhos diferentes");
            
    Matriz resp = new Matriz(m1.tamLinha,m1.tamColuna);    
    
    for (Celula i = m1.inicio, x = resp.inicio ; x != null ; i = i.inf, x = x.inf ) {
        for (Celula l = m2.inicio, y = x ; y != null ; l = l.prox, y = y.prox) {
            Celula j = l;
            Celula h = i;
            while( j != null ) {
                y.elemento += j.elemento * h.elemento;
                j = j.inf;    
                h = h.prox;
            }
        }
    }        
    return resp;
  }
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
      criarMatriz();
    }

    private void criarMatriz() {
      Celula hold = new Celula();
      this.inicio = hold;
      criarLinha(hold, this.tamLinha-1);
      criarColuna(hold);
      linkarCelulas(hold);
    }

    //Criar linhas
    private void criarLinha(Celula hold, int tamLinha) {
      Celula tmp = hold;

      for(int i = 0; i < tamLinha; i++) {
        tmp.prox = new Celula();
        tmp.prox.ant = tmp;
        tmp = tmp.prox;
      }

    }

    //Criar as colunas
    private void criarColuna(Celula hold) {
      Celula tmp = hold;

      for(int i = 0; i < this.tamColuna-1; i++) {
        tmp.inf = new Celula();
        criarLinha(tmp.inf, this.tamLinha-1);
        tmp.inf.sup = tmp;
        tmp = tmp.inf;
      }

    }

    //Linkar as celulas superiores e inferiores
    private void linkarCelulas(Celula hold) {
      Celula tmp = hold;

      for(int i = 0; i < this.tamColuna-1; i++) {
        _linkarLinha(tmp);
        tmp = tmp.inf;
      }
    }

    //Linkar a linha da matriz
    private void _linkarLinha(Celula hold) {
      Celula tmp = hold;

      for(int i = 0; i < this.tamLinha-1; i++) {
        tmp.prox.inf = tmp.inf.prox;
        tmp.inf.prox.sup = tmp.prox;
      }
    }

    public void inserir(int[] insert) {
      Celula tmp = this.inicio;
      Celula atual = tmp;

      int contador = 0;
      for(int i = 0; i < this.tamColuna; i++) {
        for(int j = 0; j < this.tamLinha; j++) {
          atual.elemento = insert[contador];
          contador++;
          atual = atual.prox;
        }
        tmp = tmp.inf;
        atual = tmp;
      }
    }

    public void mostrarDiagonalPrincipal() {
        if(this.tamLinha == this.tamColuna) {
            Celula tmp = this.inicio;
            _mostrarDiagonalPrincipal(tmp);
        }
        MyIO.println("");
    }

    private void _mostrarDiagonalPrincipal(Celula i) {
        if(i != null){
          System.out.print(i.elemento + " ");
          if(i.prox != null) _mostrarDiagonalPrincipal(i.prox.inf);
        }
    }

    public void mostrarDiagonalSecundaria() {
        if(this.tamLinha == this.tamColuna) {
            Celula i = this.inicio;
            while(i.prox != null) i = i.prox;
            _mostrarDiagonalSecundaria(i);
        }
        MyIO.println("");
    }

    private void _mostrarDiagonalSecundaria(Celula i) {
        if(i != null){
          System.out.print(i.elemento + " ");
          if(i.ant != null && i.ant.inf != null) _mostrarDiagonalSecundaria(i.ant.inf);
        }
    }

    public void mostrarTudo() {
      Celula  i = this.inicio;
      Celula linha = i;


      for (int j = 0; j < this.tamColuna; j ++) {
        for (int k = 0; k < this.tamLinha; k++) {
          if (i != null) {
            System.out.print(i.elemento + " ");
            i = i.prox;
          } else {
            System.out.print("null ");
          }
        }
        System.out.println();
        linha = linha.inf;
        i = linha;
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
    
    /*public Matriz multiplicacao(Matriz matriz) {
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
      Celula linha = i;
      Celula coluna = j;
      int soma = 0;

      while(i != null && j != null) {
        soma += i.elemento * j.elemento;
        i.prox;
        j.inf;
      }
    }*/
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