class Celula {
    protected int elemento;
    protected Celula prox;

    public Celula(int x){
        this.elemento = x;
        this.prox = null;
    }

    public Celula(){
        this(0);
    }
}