import java.util.Scanner;

class AtividadeRecursivo{
    public static void main(String[] args){
        Scanner leitor = new Scanner(System.in);

        String texto = leitor.nextLine();

        while(!ehFim(texto)){

            System.out.println(quantMaiusculas(texto, 0));

            texto = leitor.nextLine();
        }


        // Desligar o programa
        leitor.close();
    }

    public static boolean ehFim(String entrada) {  //Descobrir se eh o fim do arquivo
        boolean fim = true;
        if(entrada.charAt(0) != 'F' || entrada.charAt(1) != 'I' || entrada.charAt(2) != 'M'){
            fim = false;
        }
        return fim;
    } 


    public static int quantMaiusculas(String entrada, int pos){  //Contar o numero de letras maisculas em uma String
        int maiusculas = 0;

        if(pos < entrada.length()){
            maiusculas = quantMaiusculas(entrada, pos+1);
            if(entrada.charAt(pos) >= 'A' && entrada.charAt(pos) <= 'Z'){
                maiusculas++;
            }
        }

        return maiusculas;
    }
}