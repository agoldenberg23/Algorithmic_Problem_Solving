import java.util.Scanner;

public class Problem_5
{
	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		
		String str;
		while(sc.hasNextLine())
		{
			str = sc.nextLine();
			if(str.isEmpty() || str == null)
			{
				break;
			}
			String[] splited = str.split(" ");
			
			//Wanted info
			int partisipants = Integer.parseInt(splited[0]);
			//System.out.println("Partisipants: " + partisipants);
			int budget = Integer.parseInt(splited[1]);
			//System.out.println("budget: " + budget);
			int hotels = Integer.parseInt(splited[2]);
			//System.out.println("Hotels: " + hotels);
			int weeks = Integer.parseInt(splited[3]);
			//System.out.println("Weeks: " + weeks);
		
			int minCost = -1;
		
			//Hotel info
			for(int i = 0; i < hotels; i++)
			{
				int price = Integer.parseInt(sc.nextLine()) * partisipants;
				//System.out.println("Price: " + price);
			
				//Get the occupancy
				str = sc.nextLine();
				String[] occ = str.split(" ");
				
				//In budget and cheaper (or valid for first)
				if(price <= budget && (price < minCost || minCost == -1))
				{
					//System.out.println("In budget");
					//Check occupancy
					for(int c = 0; c < occ.length; c++)
					{
						if(Integer.parseInt(occ[c]) >= partisipants)
						{
							minCost = price;
							//System.out.println("minCost: " + minCost);
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
