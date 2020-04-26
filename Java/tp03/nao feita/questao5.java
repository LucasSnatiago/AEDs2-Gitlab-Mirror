class questao5 {
  public static void main(String[] args) {
    Matriz matriz1 = null;
	Matriz matriz2 = null;
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
	  matriz1.multiplicacao(matriz2).mostrarTudo();
    }

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
            tmp = null;
        }
    }

    private void _mostrarDiagonalPrincipal(Celula i) {
        if(i != null){
            System.out.print(i.elemento + " ");
            if (i.inf != null)
              _mostrarDiagonalPrincipal(i.inf.prox);
        }
    }

    public void mostrarDiagonalSecundaria() {
        if(this.tamLinha == this.tamColuna) {
            Celula i = this.inicio;
            while(i.prox != null) i = i.prox;
            _mostrarDiagonalSecundaria(i);
            i = null;
        }
    }

    private void _mostrarDiagonalSecundaria(Celula i) {
        if(i != null){
            System.out.print(i.elemento + " ");
            if(i.ant != null)
              _mostrarDiagonalSecundaria(i.ant.inf);
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

    public Matriz multiplicacao (Matriz m) {
	
      Matriz resp = new Matriz(m.tamLinha, m.tamColuna);
      resp.inicio = new Celula();
      Celula resp_cel = resp.inicio;
      Celula m1_cel = this.inicio;
      Celula m2_cel = m.inicio;
      Celula cima = resp.inicio;
      Celula aux = m1_cel;
      Celula aux2 = m2_cel;
      int soma;
    
    
      for(int i = 0; i < m.tamLinha; i++)
      {
        m1_cel    =  this.inicio;
        m2_cel    =  m.inicio;
        resp_cel  =  resp.inicio;
    
        
        if(i > 0)
        {
          for(int k = i; k > 1; k--)
          {
            m1_cel    =  m1_cel.inf;
            resp_cel  =  resp_cel.inf;
          }
    
          m1_cel        =  m1_cel.inf;
          m2_cel        =  m.inicio;
          resp_cel.inf  =  new Celula();
          resp_cel      =  resp_cel.inf;
        }
        
        aux   =  m1_cel;
        aux2  =  m2_cel;
    
        for(int j = 0; j < this.tamColuna; j++)
        {
    
          soma = 0;
          
          for(int k = 0; k < this.tamLinha; k++)
          {
            soma += m1_cel.elemento * m2_cel.elemento;
    
            m1_cel  =  m1_cel.prox;
            m2_cel  =  m2_cel.inf;
          }
    
          resp_cel.elemento = soma;
    
          resp_cel.prox      =  new Celula();
          resp_cel.prox.ant  =  resp_cel;
          resp_cel          =  resp_cel.prox;
          m1_cel            =  aux;
          aux2              =  aux2.prox;
          m2_cel            =  aux2;
    
          if(i > 0)
          {
            cima = resp.inicio;
    
            for(int k = i; k > 1; k--)
              cima = cima.inf;
              
            for(int k = j; k >= 0; k--)
              cima = cima.prox;
    
            resp_cel.sup  =  cima;
            cima.inf      =  resp_cel;  
          }
        }
      }
    
    
      return resp;
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