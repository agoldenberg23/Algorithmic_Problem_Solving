import java.util.Scanner;

public class Problem_4
{	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int monthsTot = 100;
		
		while(true)
		{
			//Get loan info
			monthsTot = sc.nextInt();
			float downPay = sc.nextFloat();
			float loan = sc.nextFloat();
			int depNum = sc.nextInt();
			
			if(monthsTot <= 0)
			{
				break;
			}
			
			//Derived info
			float carValue = loan + downPay;
			float monthPayment = loan / (float)monthsTot;

			//Fill the depreciation arr given
			float[] dep = new float[monthsTot + 1];
			for(int i = 0; i < dep.length; i++)
			{
				dep[i] = -1;
			}
			
			for(int i = 0; i < depNum; i++)
			{
				int ind = sc.nextInt();
				float val = sc.nextFloat();
				dep[ind] = val;
			}
			
			//Fill dep arr gaps
			//System.out.println("loc 0 val " + dep[0]);
			for(int i = 1; i < dep.length; i++)
			{
				if(dep[i] == -1)
				{
					dep[i] = dep[i-1];
				}
				//System.out.println("loc " + i + " val " + dep[i]);
			}
			
			//Go through month by month
			carValue = carValue - carValue * dep[0];	//The first month
			//System.out.println("After 0 carValue: " + carValue + " loan: " + loan);
			int i = 1;
			while(carValue < loan)
			{
				//System.out.println("Before " + i + " carValue: " + carValue + " loan: " + loan);
				
				if(i > monthsTot)
				{
					System.out.println("ERROR - Over the number of months");
				}
				
				carValue = carValue - carValue * dep[i];
				loan = loan - monthPayment;
				
				i++;
				//System.out.println("After " + i + " carValue: " + carValue + " loan: " + loan);
			}
			
			i--;
			
			if(i == 1)
			{
				System.out.println("1 month");
			}
			else
			{
				System.out.println(i + " months");
			}
		}
		
		sc.close();
	}
}
