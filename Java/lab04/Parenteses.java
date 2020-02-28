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
        boolean saida = true;
        int tamanhoEntrada = entrada.length();
        int numeroParentesEsq = 0;
        int numeroParentesDir = 0;

        int esq = 0;
        int dir = tamanhoEntrada-1;
        boolean achouEsq;
        boolean achouDir;
        while(esq != tamanhoEntrada-1 && dir > -1) {

            achouEsq = false;
            achouDir = false;

            while(esq < tamanhoEntrada && entrada.charAt(esq) != '('){ 
                if(entrada.charAt(esq) == '(') achouEsq = true;
                esq++;
                numeroParentesEsq++;
            }

            while(dir >= 0 && entrada.charAt(dir) != ')'){ 
                if(entrada.charAt(dir) == ')') achouDir = true;
                dir--;
                numeroParentesDir++; 
            }

            if(achouEsq != achouDir) saida = false;
            if(achouEsq && achouDir && esq > dir) saida = false;

            esq++;
            dir--;
        }
        if (numeroParentesEsq != numeroParentesDir) saida = false;

        return saida;
    }
}