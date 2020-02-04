import java.util.Random;

public class Questao4{
    public static void main(String[] args){        
        String entrada = MyIO.readLine();

        while(!ehFim(entrada)){

            

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


    public static String aleatorio(String entrada){  //Escolhe duas letras aleatoriamente e substitui a primeira pela segunda
        String final = "";

        Random gerador = new Random();
        gerador.setSeed(4);

        char letra;
        for(int i = 0; i < entrada.length(); i++){
            letra = ((char) entrada.charAt(i) + ())
        }

        return final;
    }
   

}