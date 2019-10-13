import java.util.Scanner;

public class Problem_7
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int reqNum = 100;
		int prop;
		int rfpNum = 0;
		
		while(true)
		{	
			//Get the required information
			reqNum = sc.nextInt();
			prop = sc.nextInt();
			sc.nextLine();
			
			if(reqNum <= 0)
			{
				break;
			}
			else if(rfpNum > 0)
			{
				System.out.println();
			}
			
			rfpNum++;
			
			String[] req = new String[reqNum];
			for(int i = 0; i < req.length; i++)
			{
				req[i] = sc.nextLine();
				//System.out.println(req[i]);
			}
			
			//Wanted things
			String supplyerBest = "";
			float priceBest = -1;
			float complienceBest = -1;
			
			//Get the proposal info
			for(int i = 0; i < prop; i++)
			{
				String propName = sc.nextLine();
			
				//System.out.println("Company = " + propName);
				
				float price = sc.nextFloat();
				int reqMet = sc.nextInt();
			
				sc.nextLine();
			
				for(int c = 0; c < reqMet; c++)	//Skip what they have
				{
					sc.nextLine();
				}
			
				//System.out.println("Complience = " + (float)reqMet / reqNum);
				//System.out.println("price = " + price);
				
				//Check compliance better
				if((float)reqMet / reqNum > complienceBest)
				{
					//System.out.println("Better Complience = " + (float)reqMet / reqNum);
					supplyerBest = propName;
					priceBest = price;
					complienceBest = (float)reqMet/reqNum;
				}
				//Check price with matching complience
				else if((float)reqMet / reqNum == complienceBest)
				{
					if(price < priceBest || priceBest == -1)
					{
						//System.out.println("Better price = " + price);
						supplyerBest = propName;
						priceBest = price;
						complienceBest = (float)reqMet/reqNum;
					}
				}
			}
		
			System.out.println("RFP #" + rfpNum);
			System.out.println(supplyerBest);
		}
		
		sc.close();
	}
}
