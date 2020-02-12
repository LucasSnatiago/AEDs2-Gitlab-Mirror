public class Combinator {
    public static void main(String[] args){
        String entrada1 = MyIO.readString();
        String entrada2 = MyIO.readString();

        while(!ehFim(entrada1)){

            MyIO.println(combinator(entrada1, entrada2));
            

            entrada1 = MyIO.readString();
            if(!ehFim(entrada1)) entrada2 = MyIO.readString();
        }

    }


    public static String combinator(String entrada1, String entrada2){  //Combinar dois textos
        String combinada = "";
        int tam1 = entrada1.length();
        int tam2 = entrada2.length();

        int maior = tam1;
        if(tam2 > tam1) maior = tam2;

        for(int i = 0; i < maior; i++){

            if(i < tam1) combinada += entrada1.charAt(i);
            if (i < tam2) combinada += entrada2.charAt(i);

        }

        return combinada;
    }

    public static boolean ehFim(String entrada) {  //Descobrir se eh o fim do arquivo
        boolean fim = true;
        if(entrada.charAt(0) != 'F' || entrada.charAt(1) != 'I' || entrada.charAt(2) != 'M'){
            fim = false;
        }
        return fim;
    } 
}