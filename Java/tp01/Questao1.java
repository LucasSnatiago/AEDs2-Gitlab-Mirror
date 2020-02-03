class Questao1{
    public static void main(String[] args){        
        String entrada = MyIO.readLine();

        while(!ehFim(entrada)){

            if(ehPalindromo(entrada)){
                System.out.println("SIM");
            }else{
                System.out.println("NAO");
            }

            entrada = MyIO.readLine();
        }

    }

    public static boolean ehFim(String entrada) {  //Descobrir se eh o fim do arquivo
        boolean fim = true;
        if(entrada.charAt(0) != 'F' || entrada.charAt(1) != 'I' || entrada.charAt(2) != 'M'){
            fim = false;
        }
        return fim;
    } 


    public static boolean ehPalindromo(String entrada){
        boolean ehPalindromo = true;
        int posFinal = entrada.length();

        for(int i = 0; i < entrada.length()/2; i++){
            if(entrada.charAt(i) != entrada.charAt(posFinal-1)){
                ehPalindromo = false;
            }
            posFinal--;
        }

        return ehPalindromo;
    }

}