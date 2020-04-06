class Pilha extends Celula {
    protected Celula topo;
    protected int numElementos;

    public PilhaCNoCabes(){
        this.topo = new Celula();
        this.numElementos = 0;
    }

    public void inserir(int x){
        Celula tmp = new Celula();
        tmp.prox = this.topo;
        this.topo = tmp;
        tmp = null;
        this.numElementos++;
    }

    public int remover() throws Exception {
        if(topo.prox == null) throw new Exception("Erro ao remover! Pilha vazia!");

        int removido = topo.elemento;
		Celula tmp = topo;
		topo = topo.prox;
		tmp.prox = null;
		tmp = null;
        return removido;
	}
	
	public void mostrar(){
		Celula tmp = this.topo;
	  
		MyIO.print("[ ");
		while(tmp.prox != null){ 
			MyIO.print(tmp.elemento + " ");
			tmp = tmp.prox;
		}
		MyIO.println("]");
		
	}
	  
}