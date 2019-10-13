import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Problem_C
{
	public static void main(String[] args) throws IOException
	{
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out, "ASCII"), 4096);
		Scanner sc = new Scanner(System.in);
	
		while(true)
		{
			int inNum = sc.nextInt();
			
			if(inNum == 0)
			{
				break;
			}
			
			//Get the input
			int[] bets = new int[inNum + 10];
			for(int i = 0; i < inNum; i++)
			{
				bets[i] = sc.nextInt();
			}
			
			int sumBest = 0;
			int sum = 0;
			
			//For each starting point
			for(int start = 0; start < inNum; start++)
			{
				sum = 0;
				
				//For each end point
				for(int end = start; end < inNum; end++)
				{
					sum = sum + bets[end];
					
					if(sum < 0)
					{
						start = end;
						break;
					}
					
					if(sum > sumBest)
					{
						sumBest = sum;
					}
				}
			}
			
			if(sumBest <= 0)
			{
				output.write("Losing streak.\n");
			}
			else
			{
				output.write("The maximum winning streak is " + sumBest + ".\n");
			}
			
		}
	
		sc.close();
		output.close();
	}
}
