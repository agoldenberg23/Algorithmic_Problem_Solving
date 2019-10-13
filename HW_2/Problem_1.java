import java.util.Scanner;

public class Problem_1
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int set = 0;
		while(true)
		{
			int stackNum = sc.nextInt();
			if(stackNum == 0)
			{
				break;
			}
			set++;
			
			int[] stack = new int[stackNum];
			int hight = 0;
			for(int i = 0; i < stackNum; i++)
			{
				stack[i] = sc.nextInt();
				hight = hight + stack[i];
			}
			hight = hight / stackNum;
			
			//System.out.println(hight);
			
			//End taking in input
			int moves = 0;
			for(int i = 0; i < stackNum; i++)
			{
				if(stack[i] < hight)
				{
					moves = moves + Math.abs(stack[i] - hight);
				}
			}
			
			System.out.println("Set #" + set);
			System.out.println("The minimum number of moves is " + moves + ".");
			System.out.println();
		}
		
		sc.close();
	}
}
