class ArvoreNivel {
    public static void main(String[] args) {
        Arvore arvore;
        int numTestes = MyIO.readInt();
        int numElementosArvore;
        int qualArvore = 1;
        
        for(int i = 0; i < numTestes; i++) {
            arvore = new Arvore();
            numElementosArvore = MyIO.readInt();

            for(int j = 0; j < numElementosArvore; j++) {
                arvore.inserir(MyIO.readInt());
            }

            arvore.mostrar(qualArvore);
            qualArvore++;
            arvore = null;
        }

    } 
}

// Arvore binaria
class Arvore {
    // Classe No da Arvore
    class No {
        protected int valor;
        protected No esq;
        protected No dir;
        protected int altura;

        public No() {
            this(0, 0);
        }

        // Criacao de um No, ja com o valor
        public No(int valor, int altura) {
            this.valor = valor;
            this.esq = this.dir = null;
            this.altura = altura;
        }
    }

    public No raiz;

    // Criando uma arvore
    public Arvore() {
        this.raiz = null;
    }

    // Inserir elemento em uma Arvore
    public void inserir(int valor) {
        if(this.raiz == null)
            this.raiz = new No(valor, 1);
        
        else _inserir(this.raiz, valor, 2);
    }

    private void _inserir(No i, int valor, int altura) {
        if(i.valor < valor) {
            if(i.esq == null) i.esq = new No(valor, altura);
            else _inserir(i.esq, valor, altura + 1);

        } else if(i.valor > valor) {
            if(i.dir == null) i.dir = new No(valor, altura);
            else _inserir(i.dir, valor, altura + 1);

        } else System.err.println("Valor: " + valor + " já inserido na Árvore!");
    }

    // Tamanho max da folha
    public int maxArvore(No i) {
        int tam = 0;
        
        if(i.esq != null) tam = maxArvore(i.esq);
        if(i.dir != null) tam = maxArvore(i.dir);
        if(i.altura > tam) tam = i.altura;

        return tam;
    }

    // Mostrar resultado da Arvore
    public void mostrar(int caso) {
        System.out.println("Case " + caso + ":");
        System.out.print(this.raiz.valor + " ");

        for(int i = 0; i < maxArvore(this.raiz)+100; i++){
            _mostrar(this.raiz, i);
        }

        System.out.println("");
        System.out.println("");
    }

    private void _mostrar(No i, int altura) {
        if(i.dir != null && altura == i.altura)
            System.out.print(i.dir.valor + " ");

        if(i.esq != null && altura == i.altura) 
            System.out.print(i.esq.valor + " ");

        if(i.dir != null) _mostrar(i.dir, altura);
        if(i.esq != null) _mostrar(i.esq, altura);
    }
}