public class Questao2{
    public static void main(String[] args){        
        String entrada = MyIO.readLine();

        while(!ehFim(entrada)){

            String cifrada = cifraCesar(entrada, 3);    

            System.out.println(cifrada);

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


    public static String cifraCesar(String entrada, int chave){
        String cifrada = "";
        char letra;

        for(int i = 0; i < entrada.length(); i++){
            letra = (char) ((int) entrada.charAt(i) + chave);
            cifrada += letra;
        }

        return cifrada;
    }

}