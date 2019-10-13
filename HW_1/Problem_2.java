import java.util.Scanner;

public class Problem_2
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNext())
		{
			int runs = sc.nextInt();
			
			if(runs <= 0)
			{
				break;
			}
			
			int xdiv = sc.nextInt();
			int ydiv = sc.nextInt();
			
			for(int i = 0; i < runs; i++)
			{
				int x = sc.nextInt();
				int y = sc.nextInt();
				
				if(x == xdiv || y == ydiv)
				{
					System.out.println("divisa");
				}
				else if(x > xdiv && y > ydiv)
				{
					System.out.println("NE");
				}
				else if(x > xdiv && y < ydiv)
				{
					System.out.println("SE");
				}
				else if(x < xdiv && y < ydiv)
				{
					System.out.println("SO");
				}
				else if(x < xdiv && y > ydiv)
				{
					System.out.println("NO");
				}
			}
		}
		
		sc.close();
	}
}
