class Parenteses {
    public static void main(String[] args){
        String entrada = MyIO.readLine();
        
        while(!ehFim(entrada)){

            if(numParenteses(entrada)) MyIO.println("correto");
            else MyIO.println("incorreto");

            entrada = MyIO.readLine();
        }
    }

    public static boolean ehFim(String entrada){  //Verificar se esta no final do arquivo
        boolean fim = true;
        if(entrada.charAt(0) != 'F' || entrada.charAt(1) != 'I' || entrada.charAt(2) != 'M') fim = false;
        return fim;
    }

    public static boolean numParenteses(String entrada){  //Verificar se o numero de parenteses esta correto
        //Fazer usando metodo de pilha
        boolean saida = true;
        Pilha pilha = new Pilha();

        for(int i = 0; i < entrada.length(); i++){
            
            if(entrada.charAt(i) == '(') pilha.inserir();
            if(entrada.charAt(i) == ')') pilha.remover();
            if(pilha.valor < 0) saida = false; 

        }
        if(pilha.valor != 0) saida = false;

        return saida;
    }
}


class Pilha{
    int valor;

    public void inserir(){
        this.valor++;
    }

    public void remover(){
        this.valor--;
    }

}