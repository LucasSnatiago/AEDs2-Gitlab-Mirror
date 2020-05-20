class Fila extends Celula {
    public Celula inicio;
    public Celula fim;
    

    public Fila(){
        this.inicio = null;
        this.fim = null;
    }

    public void inserir(int x){

        if(this.inicio == this.fim){
            this.inicio = new Celula(x);
            this.fim = this.inicio;
        }else{
            this.fim.prox = new Celula(x);
            this.fim = this.fim.prox;
        }
    }

    public int remover() throws Exception{
        
        if(this.inicio == null)
            throw new Exception("Nao e possivel remover um item de uma fila vazia!");

        int removido = this.inicio.elemento;
        this.inicio = this.inicio.prox;
        return removido;
    }
}