import java.io.BufferedReader;
import java.io.InputStreamReader;

class Questao1 
{
    public static void main(String[] args) 
    {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String entrada = null;
        Dicionario dicionario = new Dicionario();
        String[] separada = null;
    
        try
        {
            entrada = bf.readLine();
        } catch(Exception err) { System.err.println(err); }
    
        while(entrada != null)
        {

            separada = entrada.toLowerCase().replaceAll("[^a-z]", " ").replaceAll("\n", "").split(" ");

            for(String i : separada) dicionario.inserir(i);
            
            try
            {
                entrada = bf.readLine();
            } catch(Exception err) { System.err.println(err); }
        }
        
        dicionario.sort();
        dicionario.mostrar();
    }
}

class Celula
{
    protected String palavra;
    protected Celula prox;

    public Celula(String palavra)
    {
        this.palavra = palavra;
    }
}

class Dicionario
{
    public Celula raiz;

    public Dicionario()
    {
        this.raiz = null;
    }

    public void inserir(String palavra)
    {
        if(this.raiz == null) this.raiz = new Celula(palavra);
        else inserir(this.raiz, palavra);
    }

    private void inserir(Celula i, String palavra)
    {
        if(i.palavra.equals(palavra));
        else if(i.prox != null) inserir(i.prox, palavra);
        else if(i.prox == null) i.prox = new Celula(palavra);
    }

    public void sort()
    {
        Celula i = this.raiz;
        Celula j = this.raiz;
        Celula tmpJ = null;
        String menor;

        while(i.prox != null)
        {
            menor = i.palavra;
            tmpJ = i;

            while(j != null)
            {
                if(menor.compareTo(j.palavra) > 0)
                {
                    menor = j.palavra;
                    tmpJ = j;
                }

                j = j.prox;
            }

            // Swap dos elementos
            String tmp = tmpJ.palavra;
            tmpJ.palavra = i.palavra;
            i.palavra = tmp;
        

            i = i.prox;
            j = i.prox;
        }
    }

    void mostrar()
    {
        Celula tmp = this.raiz.prox;

       while(tmp != null)
        {
            System.out.println(tmp.palavra);
            tmp = tmp.prox;
        }
    }
}