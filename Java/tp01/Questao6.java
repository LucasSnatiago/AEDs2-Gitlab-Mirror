class Questao6{
    public static void main(String[] args){        
        String entrada = MyIO.readLine();

        while(!ehFim(entrada)){

            escrever(entrada);

            entrada = MyIO.readLine();
        }

    }

    public static void escrever(String entrada){
        if(soVogais(entrada)){
            System.out.print("SIM ");
        }else{
            System.out.print("NAO ");
        }

        if(soConsoantes(entrada)){
            System.out.print("SIM ");
        }else{
            System.out.print("NAO ");
        }

        if(soInteiro(entrada)){
            System.out.print("SIM ");
        }else{
            System.out.print("NAO ");
        }

        if(soReal(entrada)){
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

    public static boolean soVogais(String entrada){  //Funcao que retorna true se houver apenas vogais em uma frase
        boolean soVogais = true;

        for(int i = 0; i < entrada.length(); i++){
            if(!ehVogal(entrada.charAt(i))) {
                soVogais = false;
                i = entrada.length();
            }
        }

        return soVogais;
    }

    public static boolean soConsoantes(String entrada){  //Funcao que retorna true se houver apenas consoantes em uma frase
        boolean consoantes = true;

        for(int i = 0; i < entrada.length(); i++){
            if(ehVogal(entrada.charAt(i)) || (entrada.charAt(i) >= '1' && entrada.charAt(i) <= '9')){
                consoantes = false;
                i = entrada.length();
            }
        }

        return consoantes;
    }

    public static boolean soInteiro(String entrada){  //Retorna true caso a String seja um numero inteiro
        boolean soInteiro = true;

        for(int i = 0; i < entrada.length(); i++){
            if(entrada.charAt(i) >= 'a' && entrada.charAt(i) <= 'z' || entrada.charAt(i) >= 'A' && entrada.charAt(i) <= 'Z'){
                soInteiro = false;
                i = entrada.length();
            }
            else if(entrada.charAt(i) == '.' || entrada.charAt(i) == ','){
                soInteiro = false;
                i = entrada.length();
            }
        }

        return soInteiro;
    }

    public static boolean soReal(String entrada){  //Retorna true caso a String seja um numero real
        boolean numero = true;
        int pontuacao = 0;

        for(int i = 0; i < entrada.length(); i++){
            if(entrada.charAt(i) >= 'a' && entrada.charAt(i) <= 'z' || entrada.charAt(i) >= 'A' && entrada.charAt(i) <= 'Z'){
                numero = false;
                i = entrada.length();
            }
            else if(entrada.charAt(i) == '.' || entrada.charAt(i) == ','){
                pontuacao++;
            }
        }

        boolean real;

        if(numero && pontuacao < 2){
            real = true;
        }
        else{
            real = false;
        }

        return real;
    }
}