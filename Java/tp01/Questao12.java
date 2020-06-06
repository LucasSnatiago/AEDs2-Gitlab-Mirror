public class Questao12 {
    public static void main(String[] args){
       // MyIO.setCharset("ISO-8859-1");        
        String entrada = MyIO.readLine();

        while(!ehFim(entrada)){

            String cifrada = cifraCesar(entrada, 3);    

            MyIO.println(cifrada);

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


    public static String cifraCesar(String entrada, int chave){  //Cifra de Cesar
        return cifraCesar(entrada, chave, entrada.length()-1);
    }


    public static String cifraCesar(String entrada, int chave, int pos){ //Funcao recursiva para cifrar um texto
        String textoCifrado = "";

        if(pos > 0) textoCifrado = cifraCesar(entrada, chave, pos-1);
        textoCifrado += (char) (entrada.charAt(pos) + chave);

        return textoCifrado;
    }

}
