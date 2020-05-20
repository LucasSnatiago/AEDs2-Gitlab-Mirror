import java.io.BufferedReader;
import java.io.InputStreamReader;

class ListaTelefonica {
    public static void main(String[] args){
        ListaDupla lista = new ListaDupla();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String repeticoes = null;

        try{
            repeticoes = bf.readLine();
        }catch(Exception err){}

        while(repeticoes != null) {
            String valor = "-1";

            for(int i = 0; i < Integer.parseInt(repeticoes); i++){
                try{
                    valor = bf.readLine();
                }catch(Exception err){}
                lista.inserir(Integer.parseInt(valor));
            }

            MyIO.println(executar(lista));
            lista.limparLista();
            try{
                repeticoes = bf.readLine();
            }catch(Exception err){}
        }
    }


    public static int executar(ListaDupla lista){        
        lista.insertion();
        return numEconomizados(lista);
    }

    public static int numEconomizados(ListaDupla lista){
        int numEconomizados = 0;
        int valor1 = 0;
        int valor2 = 0;

        for(int i = 0; i < lista.numElementos-1; i++){
            try{
                valor1 = lista.retornarElementoPos(i);
                valor2 = lista.retornarElementoPos(i+1);
            }catch(Exception err) {}
        
            numEconomizados += economia(valor1, valor2);
        }

        return numEconomizados;
    }

    private static int economia(int valor1, int valor2){
        int economia = 0;
        String val1 = valor1 + "";
        String val2 = valor2 + "";

        for(int i = 0; i < val1.length(); i++){
            if(val1.charAt(i) == val2.charAt(i)) economia++;
            else i = val1.length();
        }
        
        return economia;
    }

}


class ListaDupla extends Celula {
    protected Celula inicio;
    protected Celula fim;
    public int numElementos;

    public ListaDupla(){
        this.inicio = new Celula();
        this.fim = inicio;
        this.numElementos = 0;
    }

    public void limparLista(){
        this.inicio = this.fim = null;
        this.inicio = new Celula();
        this.fim = inicio;
        this.numElementos = 0;
    }

    void inserir(int x){
        this.fim.prox = new Celula(x);
        Celula tmp = this.fim.prox;
        tmp.ant = this.fim;
        this.fim = tmp;
        tmp = null;
        this.numElementos++;
    }

    int remover() throws Exception{
        if(this.inicio == this.fim)
            throw new Exception("Erro ao remover! Lista vazia!");
        
        int removido = this.fim.elemento;
        Celula tmp = this.fim;
        this.fim = this.fim.ant;
        this.fim.prox = null;
        tmp.ant = null;
        tmp.prox = null;
        return removido;
    }

    void mostrar(){
        Celula tmp = this.inicio.prox;
        MyIO.print("[ ");
        while(tmp != null){
            MyIO.print(tmp.elemento + " ");
            tmp = tmp.prox;
        }
        MyIO.println("]");
    }

    public int retornarElementoPos(int pos) throws Exception{
        Celula tmp = this.inicio.prox;

        for(int i = 0; i < pos; i++){
            if(tmp != null) tmp = tmp.prox;
            else i = pos;
        }

        if(tmp == null)
            throw new Exception("Posição inválida!");

        int elemento = tmp.elemento;

        return elemento;
    }

    public void insertion(){
        Celula atual = this.inicio.prox;
        
        Celula tmp;
        Celula menor;
        while(atual != null){
            
            menor = atual;
            tmp = atual;
            while(tmp != null){
                if(tmp.elemento < menor.elemento) menor = tmp;
                tmp = tmp.prox;
            }
            swapInsertion(menor, atual);
            

            atual = atual.prox;
        }

    }

    private void swapInsertion(Celula i, Celula j){
        int tmp = i.elemento;
        i.elemento = j.elemento;
        j.elemento = tmp;
    }
}

class Celula {
    protected Celula ant;
    protected Celula prox;
    protected int elemento;

    public Celula(){
        this(-1);
    }

    public Celula(int x){
        this.elemento = x;
    }
}