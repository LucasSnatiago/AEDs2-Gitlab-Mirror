class Arvore {
    public static void main(String[] args) {
        String entrada = MyIO.readLine();
        String[] textos;
        ArvoreBinaria arvore = new ArvoreBinaria();

        int repeticoes = Integer.parseInt(entrada);

        for(int i = 0; i < repeticoes; i++) {

            entrada = MyIO.readLine();
            textos = entrada.split(" ");

            char[] raiz = textos[1].toCharArray();
            arvore.inserir("".toCharArray(), raiz[0], false);

            boolean ladoEsq = true;
            String fraseEsq = "";

            for (char letra : textos[2].toCharArray()) {
                if(letra == raiz[0]){ 
                    arvore.inserir(fraseEsq.toCharArray(), letra, ladoEsq);
                    ladoEsq = false;

                }
                if(ladoEsq) fraseEsq += letra;
                if(!ladoEsq) arvore.inserir(fraseEsq.toCharArray(), letra, ladoEsq);
             
            }
            arvore.mostrar(raiz[0]);

            boolean chegouRaiz = false;
            char raizdir = ' ';
            for(int j = 0 ; j < textos[1].length(); j++) {
                if(chegouRaiz && textos[1].charAt(j) != raizdir) MyIO.print(textos[1].charAt(j));
                if(textos[2].charAt(j) == raiz[0]){
                    chegouRaiz = true;
                    if(j+1 < textos[1].length()-1) raizdir = textos[1].charAt(j+1);
                }
            }

            if(raizdir == ' ') MyIO.println(raiz[0]);
            else MyIO.println(raizdir + "" + raiz[0]);
            
            arvore = null;
            arvore = new ArvoreBinaria();
        }

    }
}

//Criacao de uma arvore binaria de letras
class ArvoreBinaria {
    public No raiz;

    //Criacao de uma Arvore Binaria
    public ArvoreBinaria() {
        this.raiz = null;
    }

    //Insercao em arvore binaria
    public void inserir(char[] frase, char letra, boolean esq) {
        if(this.raiz == null)
            this.raiz = new No(letra);

        else if(esq)
            this.raiz.esq = _inserirEsq(frase, frase.length-1);

        /*else {
            if(this.raiz.dir == null) 
                this.raiz.dir = new No(letra);

            else _inserir(this.raiz.dir, letra);

        }*/

    }

    //Mostrar a Arvore pos-fixa
    public void mostrar(char raiz) {
        _mostrar(this.raiz, raiz);
        MyIO.print("");
    }

    //Inserir percorrendo a Arvore
    private void _inserir(No i, char letra) {
        if(letra < i.letra) {
            if(i.esq == null) i.esq = new No(letra);
            else _inserir(i.esq, letra);

        } else if(letra > i.letra) {
            if(i.dir == null) i.dir = new No(letra);
            else _inserir(i.dir, letra);

        }
    }

    //Inserir percorrendo o lado esquerdo da Arvore
    private No _inserirEsq(char[] letra, int pos) {
        No saida = null;
        if(pos >= 0) saida = new No(letra[pos]);

        if(pos >= 0) saida.esq = _inserirEsq(letra, pos-1);
        return saida;
    }

    //Percorrer a Arvore escrevendo os elementos
    private void _mostrar(No i, char raiz) {
        if(i != null) {
            _mostrar(i.esq, raiz);
            _mostrar(i.dir, raiz);
            if(i.letra != raiz) MyIO.print(i.letra);

        }
    }
}

//No da Arvore binaria
class No {
    protected char letra;
    protected No esq, dir;

    public No(char letra) {
        this.dir = this.esq = null;
        this.letra = letra;
    }
}