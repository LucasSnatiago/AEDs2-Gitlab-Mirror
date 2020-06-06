class Questao15{
    public static void main(String[] args){        
        String entrada = MyIO.readLine();

        while(!ehFim(entrada)){

            escrever(entrada);

            entrada = MyIO.readLine();
        }

    }

    public static void escrever(String entrada){
        if(soVogais(entrada, entrada.length()-1)){
            System.out.print("SIM ");
        }else{
            System.out.print("NAO ");
        }

        if(soConsoantes(entrada, entrada.length()-1)){
            System.out.print("SIM ");
        }else{
            System.out.print("NAO ");
        }

        if(soInteiro(entrada, entrada.length()-1)){
            System.out.print("SIM ");
        }else{
            System.out.print("NAO ");
        }

        if(soReal(entrada, entrada.length()-1, 0, true)){
            System.out.print("SIM");
        }
        else{
            System.out.print("NAO");
        }

        System.out.print("\n");
    }

    public static boolean ehFim(String entrada) {  //Descobrir se eh o fim do arquivo
        boolean fim = true;
        if(entrada.charAt(0) != 'F' || entrada.charAt(1) != 'I' || entrada.charAt(2) != 'M'){
            fim = false;
        }
        return fim;
    } 

    public static boolean ehVogal(char entrada){
        boolean vogal = true;

        if(entrada != 'a' && entrada != 'e' && entrada != 'i' && entrada != 'o' && entrada != 'u'){
            vogal = false;
        }

        return vogal;
    }

    public static boolean soVogais(String entrada, int pos){  //Funcao que retorna true se houver apenas vogais em uma frase
        boolean soVogais = true;


        if(pos > 0) soVogais = soVogais(entrada, pos-1);
        if(!ehVogal(entrada.charAt(pos))) soVogais = false;

        return soVogais;
    }

    public static boolean soConsoantes(String entrada, int pos){  //Funcao que retorna true se houver apenas consoantes em uma frase
        boolean consoantes = true;

        if(pos > 0) consoantes = soConsoantes(entrada, pos-1);
        if(ehVogal(entrada.charAt(pos)) || (entrada.charAt(pos) >= '1' && entrada.charAt(pos) <= '9')) consoantes = false;

        return consoantes;
    }

    public static boolean soInteiro(String entrada, int pos){  //Retorna true caso a String seja um numero inteiro
        boolean soInteiro = true;

        if(pos > 0) soInteiro = soInteiro(entrada, pos-1);

        if(entrada.charAt(pos) >= 'a' && entrada.charAt(pos) <= 'z' || entrada.charAt(pos) >= 'A' && entrada.charAt(pos) <= 'Z') soInteiro = false;
        else if(entrada.charAt(pos) == '.' || entrada.charAt(pos) == ',') soInteiro = false;

        return soInteiro;
    }

    public static boolean soReal(String entrada, int pontuacao, int pos , boolean numero){  //Retorna true caso a String seja um numero real
        boolean real;
    
        if(pos > 0) real = soReal(entrada, pontuacao, pos-1, numero);
        if(entrada.charAt(pos) >= 'a' && entrada.charAt(pos) <= 'z' || entrada.charAt(pos) >= 'A' && entrada.charAt(pos) <= 'Z') numero = false;
        else if(entrada.charAt(pos) == '.' || entrada.charAt(pos) == ',') pontuacao++;


        if(numero && pontuacao < 2) real = true;
        else real = false;

        return real;
    }
}