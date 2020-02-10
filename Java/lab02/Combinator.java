public class Combinator {
    public static void main(String[] args){
        String entrada1 = MyIO.readLine();
        String entrada2 = MyIO.readLine();

        while(!ehFim(entrada1)){

            MyIO.println(combinator(entrada1, entrada2));
            

            entrada1 = MyIO.readLine();
            if(!ehFim(entrada1)) entrada2 = MyIO.readLine();
        }

    }


    public static String combinator(String texto1, String texto2){  //Funcao para misturar dois textos
        int tamanho1 = texto1.length();
        int tamanho2 = texto2.length();
        String resp = "";

        int pos1 = 0;
        int pos2 = 0;
        for(int i = 0; i != 1;){
            if(tamanho1 > 0){
                resp += texto1.charAt(pos1);
                pos1++;
                tamanho1--;
            }
            else if(tamanho2 > 0){
                resp += texto2.charAt(pos2);
                pos2++;
                tamanho2--;
            }
            else{
                i = 1;
            }
            MyIO.println(resp);

        }
        
        return resp;
    }


    public static boolean ehFim(String entrada) {  //Descobrir se eh o fim do arquivo
        boolean fim = true;
        if(entrada.charAt(0) != 'F' || entrada.charAt(1) != 'I' || entrada.charAt(2) != 'M'){
            fim = false;
        }
        return fim;
    } 
}