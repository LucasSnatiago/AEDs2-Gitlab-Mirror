public class Espelho {
    public static void main(String[] args){
        String num1 = MyIO.readString();
        String num2 = MyIO.readString();
        

        while(!ehFim(num1)){

            MyIO.println(espelhar(num1, num2));

            num1 = MyIO.readString();
            if(!ehFim(num1)) num2 = MyIO.readString();
        }        
    }


    public static String espelhar(String num1, String num2){  //Funcao para espelhar um conjunto de numeros
        String saida = "";

        int numero1 = Integer.parseInt(num1);
        int numero2 = Integer.parseInt(num2);

        for(int i = numero1; i <= numero2; i++){
            saida += i;
        }

        for(int i = saida.length()-1; i >= 0; i--){
            saida += saida.charAt(i);
        }

        return saida;
    }


    public static boolean ehFim(String entrada) {  //Descobrir se eh o fim do arquivo
        boolean fim = true;
        if(entrada.charAt(0) != 'F' || entrada.charAt(1) != 'I' || entrada.charAt(2) != 'M'){
            fim = false;
        }
        return fim;
    } 
}