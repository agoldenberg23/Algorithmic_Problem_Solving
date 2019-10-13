import java.util.Scanner;

public class Problem_5_old
{
	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		
		while(true)
		{	
			String str = sc.nextLine();
			if(str.isEmpty() || str == null)
			{
				break;
			}
			String[] splited = str.split("\\s+");
			
			//Wanted info
			int partisipants = Integer.parseInt(splited[0]);
			System.out.println("Partisipants: " + partisipants);
			int budget = Integer.parseInt(splited[1]);
			System.out.println("budget: " + budget);
			int hotels = Integer.parseInt(splited[2]);
			System.out.println("Hotels: " + hotels);
			int weeks = Integer.parseInt(splited[3]);
			System.out.println("Weeks: " + weeks);
		
			int minCost = -1;
		
			//Hotel info
			for(int i = 0; i < hotels; i++)
			{
				int price = Integer.parseInt(sc.nextLine()) * partisipants;
				System.out.println("Price: " + price);
			
				//Check price
				if(price > budget) //Out of budget
				{
					System.out.println("Out of budget");
					for(int c = 0; c < weeks; c++)
					{
						sc.nextInt();
						//System.out.println(c);
					}
				}
				else if(price < minCost || minCost == -1)	//In budget and cheaper (or valid)
				{
					System.out.println("In budget");
					//Check occupancy
					for(int c = 0; c < weeks; c++)
					{
						int occ = sc.nextInt();
						if(occ >= partisipants)
						{
							minCost = price;
							System.out.println("minCost: " + minCost);
						}
					}
				}
			}
		
			//Print result
			if(minCost == -1)
			{
				System.out.println("stay home");
			}
			else
			{
				System.out.println(minCost);
			}
		}
			
		sc.close();
	}
}
