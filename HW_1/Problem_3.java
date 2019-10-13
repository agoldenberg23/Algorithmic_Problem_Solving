import java.util.Scanner;

public class Problem_3
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int runs = sc.nextInt();
		
		for(int i = 1; i <= runs; i++)
		{
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			
			//a is target
			if(a > b && a < c)
			{
				System.out.println("Case " + i + ": " + a);
			}
			else if(a < b && a > c)
			{
				System.out.println("Case " + i + ": " + a);
			}
			
			//b is target
			else if(b > a && b < c)
			{
				System.out.println("Case " + i + ": " + b);
			}
			else if(b < a && b > c)
			{
				System.out.println("Case " + i + ": " + b);
			}
			
			//c is target
			else if(c > a && c < b)
			{
				System.out.println("Case " + i + ": " + c);
			}
			else if(c < a && c > b)
			{
				System.out.println("Case " + i + ": " + c);
			}
		}
	}
}
