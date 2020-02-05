import java.util.Random;

public class Questao4{
    public static void main(String[] args){
        String entrada = MyIO.readLine();

        Random gerador = new Random();
        gerador.setSeed(4);

        while(!ehFim(entrada)){

            System.out.println();

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


    public static boolean vogais(String entrada){  //Verificar se sao 

    }


    public static boolean soVogais(String entrada){  //Verificar se uma frase so contem vogais
        boolean vogais = true;

        for(int i = 0; i < entrada.length(); i++){
            if(entrada.charAt(i) != )
        }

        return vogais;
    }


    public static boolean soConsoantes(String entrada){  //Verificar se uma frase so contem consoantes

    }   


}
