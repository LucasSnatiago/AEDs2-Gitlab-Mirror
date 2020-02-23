import java.util.Random;

public class Questao4{
    public static void main(String[] args){

    }

    public static boolean ehFim(String entrada) {  //Descobrir se eh o fim do arquivo
        boolean fim = true;
        if(entrada.charAt(0) != 'F' || entrada.charAt(1) != 'I' || entrada.charAt(2) != 'M'){
            fim = false;
        }
        return fim;
    
}
