public class Questao2 {
    public static void main(String[] args)
    {
        int repeticoes = MyIO.readInt();
        Empilhamento bolas = new Empilhamento();

        while(repeticoes != 0)
        {
            bolas = new Empilhamento();
            for(int i = 0; i < repeticoes; i++)
            {
                String[] entrada = MyIO.readLine().split(" ");
                int[] valores = new int[entrada.length];
                int pos = 0;
                for(String palavra : entrada) 
                {
                    valores[pos] = Integer.parseInt(palavra);
                    pos++;
                }
    
                bolas.inserir(valores);
            }

            int resp = bolas.jogar();
            if(resp > 10) resp = resp % 10;
            else resp = resp*2;

            System.out.println(resp);
            bolas = null;
            repeticoes = MyIO.readInt();
        }

    }
}

class No
{
    public No prox;
    public int altura;
    public int[] elementos;

    public No(int[] num, int altura)
    {
        this.prox = null;
        this.altura = altura;
        this.elementos = num;
    }

    public void mostrar()
    {
        for(int i : elementos)
            System.out.print(i + " ");

        System.out.println("");
    }
}

class Empilhamento
{
    public No raiz;

    public Empilhamento()
    {
        this.raiz = null;
    }

    public void inserir(int[] elementos)
    {
        if(this.raiz == null) this.raiz = new No(elementos, 1);
        else inserir(this.raiz, elementos, 2); 
    }

    public int jogar()
    {
        int resp = 0;
        No tmp = this.raiz;
        int altura = 1;

        while(tmp != null)
        {
            if (resp+maiorSomaLinha(altura) < resp) 
            {
                resp = 0;
                altura++;
                tmp = tmp.prox;
                continue;
            }
            resp = Math.max(resp, resp+maiorSomaLinha(altura));
            altura++;
            tmp = tmp.prox;
        }

        return resp;
    }

    public int maiorSomaLinha(int altura)
    {
        No tmp = this.raiz;

        while(tmp.altura < altura) tmp = tmp.prox;
        
        int soma = 0;
        for(int i : tmp.elementos)
            soma += i;

        return soma;
    }

    private void inserir(No i, int[] elementos, int altura)
    {
        if(i.prox != null) inserir(i.prox, elementos, altura+1);
        else i.prox = new No(elementos, altura);
    }

    public void mostrar()
    {
        No tmp = this.raiz;

        while(tmp != null)
        {
            tmp.mostrar();
            tmp = tmp.prox;
        }
    }
}