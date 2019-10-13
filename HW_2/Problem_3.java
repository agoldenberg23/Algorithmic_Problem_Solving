import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Problem_3
{
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out, "ASCII"), 4096);
		
		while(true)
		{
			int solNum = sc.nextInt() + 1;
			int losNum = sc.nextInt();
			
			if(solNum == 1)
			{
				break;
			}
		
			//Is the closest buddy
			int[] aliveL = new int[solNum+1];
			int[] aliveR = new int[solNum+1];
			
			//Initalize the variables
			for(int i = 1; i < solNum; i++)
			{
				aliveL[i] = i - 1;
				aliveR[i] = i + 1;
			}
			
			//Take in the losses
			for(int i = 0; i < losNum; i++)
			{
				int left = sc.nextInt();
				int right = sc.nextInt();
				
				if(aliveL[left] <= 0)
				{
					output.write("* ");
				}
				else
				{
					output.write(aliveL[left] + " ");
				}
				
				if(aliveR[right] > solNum-1)
				{
					output.write("*\n");
				}
				else
				{
					output.write(aliveR[right] + "\n");
				}
				
				/*System.out.print("aliveL: ");
				for(int c = 0; c < aliveL.length; c++)
				{
					System.out.print(aliveL[c] + " ");
				}
				System.out.println();
				System.out.print("aliveR: ");
				for(int c = 0; c < aliveR.length; c++)
				{
					System.out.print(aliveR[c] + " ");
				}
				System.out.println();*/
				
	            aliveL[aliveR[right]] = aliveL[left];
	            aliveR[aliveL[left]] = aliveR[right];
			}
			
			output.write("-\n");
		}
		
		sc.close();
		output.flush();
	}
}
