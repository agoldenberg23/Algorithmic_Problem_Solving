import java.util.Scanner;

public class Problem_1_old 
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
			int needed = 0;
			for(int i = 0; i < stackNum; i++)
			{
				if(stack[i] > hight)
				{
					needed = needed - Math.abs(stack[i] - hight);
				}
				else if(stack[i] < hight)
				{
					moves = moves + Math.abs(stack[i] - hight);
					needed = needed + Math.abs(stack[i] - hight);
				}
			}
			
			if(needed != 0)
			{
				System.out.println("ERROR - a stack still needs/has too many blocks");
			}
			
			System.out.print("Set #" + set);
			System.out.println("The minimum number of moves is " + moves + ".");
		}
		
		sc.close();
	}
}
