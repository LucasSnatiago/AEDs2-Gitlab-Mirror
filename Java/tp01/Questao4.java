import java.util.Random;

public class Questao4{
    public static void main(String[] args){
        String entrada = MyIO.readLine();

        Random gerador = new Random();
        gerador.setSeed(4);        

        while(!ehFim(entrada)){

            System.out.println(aleatorio(entrada, gerador));

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


    public static String aleatorio(String entrada, Random gerador){  //Escolhe duas letras aleatoriamente e substitui a primeira pela segunda
        char[] texto = new char[entrada.length()];

        char letra1 = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
        char letra2 = (char)('a' + (Math.abs(gerador.nextInt()) % 26));

        for(int i = 0; i < entrada.length(); i++){
            if(entrada.charAt(i) == letra1){
                texto[i] = letra2;
            }else{
                texto[i] = entrada.charAt(i);
            }
        }

        String saida = new String(texto);
        return saida;
    }


}
