import java.util.Scanner;

public class Problem_1
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		int runs = sc.nextInt();
		
		for(int i = 0; i < runs; i++)
		{
			long a = sc.nextLong();
			long b = sc.nextLong();
			
			//System.out.print("Iteration: " + i + " " + a + " " + b + ": ");
			
			if(a == b)
			{
				System.out.println("=");
			}
			if(a > b)
			{
				System.out.println(">");
			}
			if(a < b)
			{
				System.out.println("<");
			}
		}
		
		sc.close();
	}
}
