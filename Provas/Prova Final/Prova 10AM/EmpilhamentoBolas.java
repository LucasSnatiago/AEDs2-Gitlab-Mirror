
public class EmpilhamentoBolas
{
	public static void main(String[] args) 
	{
		int quant = MyIO.readInt();

		while(quant != 0)
		{
			int count = 0;

			for(int i = 0; i < quant; i++)
			{
				count += i;
			}
			
			int[] array = new int[count];
			int j = 0;

			while(j < count)
			{
				array[j] = MyIO.readInt();
				j++;
			}
			
			for(int k = 0; k < count; k++)
			{
				System.out.print(array[k] + " ");
			}

			quant = MyIO.readInt();
		}
	}
}