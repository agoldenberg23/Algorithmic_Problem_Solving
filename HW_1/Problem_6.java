import java.util.Scanner;

public class Problem_6
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		int runs = sc.nextInt();
	
		for(int i = 0; i < runs; i++)
		{
			int values = sc.nextInt();
			
			if(values <= 0)
			{
				continue;
			}
			
			int largest = sc.nextInt();
			
			for(int c = 1; c < values; c++)
			{
				int next = sc.nextInt();
				if(next > largest)
				{
					largest = next;
				}
			}
			
			System.out.println("Case " + (i+1) + ": " + largest);
		}
		
		sc.close();
	}
}
