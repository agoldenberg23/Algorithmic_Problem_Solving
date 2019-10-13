import java.util.Scanner;

public class Problem_E
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
	
		int testNum = sc.nextInt();
		
		for(int tests = 0; tests < testNum; tests++)
		{
			//Test variables
			int matches = sc.nextInt();
			int peopTot = 1 << matches; 	//Calculate the actual number of people
			int withDrawNum = sc.nextInt();
			
			int counter = 0;
			
			//0 = match not finished
			//-1 = withdrawn
			
			int[][] withDraw = new int[peopTot][matches+1];
			
			for(int i = 0; i < withDrawNum; i++)
			{
				withDraw[sc.nextInt()-1][0] = -1;
			}
			
			//Run all of the matches checking
			int div = 2;
			for(int matchTeir = 0; matchTeir < matches; matchTeir++, div+=2)
			{
				//System.out.println("Running match tire " + matchTeir);
				int matchNum = peopTot / div;
				//System.out.println("match num = " + matchNum);
				for(int match = 0; match < matchNum*2; match+=2)
				{
					//System.out.println(match);
					//Both have conceded
					if(withDraw[match][matchTeir] == -1 && withDraw[match+1][matchTeir] == -1)
					{
						//System.out.println("Both have conceded");
						withDraw[match/2][matchTeir+1] = -1;
					}
					//Nighter have conceded
					else if(withDraw[match][matchTeir] >= 0 && withDraw[match+1][matchTeir] >= 0)
					{
						//System.out.println("Match is fully fine");
						//withDraw[match/2][matchTeir+1] = 0;
					}
					//Only one has conceded
					else
					{
						//System.out.println("One has conceded");
						counter++;
						//withDraw[match/2][matchTeir+1] = 0;
					}
				}
			}
			
			System.out.println(counter);
			
		}
		
		sc.close();
	}
}
