class Questao10{
    public static void main(String[] args){
        MyIO.setCharset("ISO-8859-1");        
        String entrada = MyIO.readLine();
        //MyIO.println(entrada);

        while(!ehFim(entrada)){

            if(ehPalindromoRecursivo(entrada)){
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


    public static boolean ehPalindromoRecursivo(String entrada){  //Descobrir se uma frase é palindromo
        return ehPalindromoRecursivo(entrada, 0, entrada.length()-1);
    }


    public static boolean ehPalindromoRecursivo(String entrada, int pos1, int pos2){ //Descobrir se uma frase é palindromo
        boolean ehPalindromo = true;
    
        if(pos1 < pos2){
            ehPalindromo = ehPalindromoRecursivo(entrada, pos1+1, pos2-1);
        }
        if(entrada.charAt(pos1) != entrada.charAt(pos2)) ehPalindromo = false;
        
        return ehPalindromo;
    }

}