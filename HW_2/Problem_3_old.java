import java.util.Arrays;
import java.util.Scanner;

public class Problem_3_old
{
	//Find the first alive solger to the right of the ind or -1
	public static int aliveR(boolean[] sol, int ind)
	{
		int find = -1;
		
		for(int i = ind; i < sol.length; i++)
		{
			if(sol[i] == true)
			{
				find = i;
				break;
			}
		}
		
		return find;
	}
	
	//Find the first alive solger to the left of the ind or -1 
	public static int aliveL(boolean[] sol, int ind)
	{
		int find = -1;
		
		for(int i = ind; i > 0; i--)
		{
			if(sol[i] == true)
			{
				find = i;
				break;
			}
		}
		
		return find;
	}
	
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		
		while(true)
		{
			int solNum = sc.nextInt() + 1;
			int losNum = sc.nextInt();
			
			if(solNum == 1)
			{
				break;
			}
			
			boolean[] sol = new boolean[solNum];
			Arrays.fill(sol, Boolean.TRUE);
			for(int i = 0; i < losNum; i++)
			{
				int left = sc.nextInt();
				int right = sc.nextInt();
				for(int c = left; c <= right; c++)
				{
					sol[c] = false;
				}
				
				left = aliveL(sol, left);
				right = aliveR(sol, right);
				
				if(left <= 0)
				{
					System.out.print("* ");
				}
				else
				{
					System.out.print(left + " ");
				}
				
				if(right <= 0)
				{
					System.out.println("*");
				}
				else
				{
					System.out.println(right);
				}
			}
			
			System.out.println("-");
		}
		
		sc.close();
	}
}
