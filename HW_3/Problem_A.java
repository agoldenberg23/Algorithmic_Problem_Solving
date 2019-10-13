import java.util.Scanner;

public class Problem_A
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int testNum = sc.nextInt();
		
		//One run per test
		for(int tests = 0; tests < testNum; tests++)
		{
			//Input parameters
			int dayNum = sc.nextInt();
			int partyNum = sc.nextInt();
			
			//The hartal values
			int[] hart = new int[partyNum];
			
			for(int i = 0; i < partyNum; i++)
			{
				hart[i] = sc.nextInt();
				//System.out.print(hart[i] + " ");
			}
			
			//Var for the run
			int count = 0;
			int curDay = -1;
			
			for(int day = 1; day <= dayNum; day++)
			{
				curDay = (curDay + 1) % 7;
				
				//Skip firday and saturday
				//if(day % 6 == 0 || day % 7 == 0)
				if(curDay == 5 || curDay == 6)
				{
					//System.out.println("Skip day " + day);
					continue;
				}
				
				//Check each party for a skip
				for(int i = 0; i < partyNum; i++)
				{
					if(day % hart[i] == 0)
					{
						//System.out.println("Counting day = " + day + ", for i = " + i + " curDay = " + curDay);
						count++;
						break;
					}
				}
			}
			
			System.out.println(count);
		}
			
		sc.close();
	}
}
