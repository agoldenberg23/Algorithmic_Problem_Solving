import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Problem_A
{
	public static void main(String[] args) throws IOException
	{
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out, "ASCII"), 4096);
		Scanner sc = new Scanner(System.in);
	
		int testNum = sc.nextInt();
		
		for(int test = 1; test <= testNum; test++)
		{
			int stopNum = sc.nextInt();
			
			int sum = 0;
			int start = 0;
			int sumBest = 0;
			int startBest = 0;
			int endBest = 0;
			for(int stop = 0; stop < stopNum; stop++)
			{
				sum = sum + sc.nextInt();
				
				//System.out.println("Stop=" + stop + " sum=" + sum);
				
				//No longer a good sum
				if(sum < 0)
				{
					//System.out.println("Reseting sum (not good), start=" + (stop+1));
					start = stop + 1;
					sum = 0;
				}
				
				//Found better sum
				if(sum > sumBest || ((sum == sumBest) && (stop - start > endBest - startBest)))
				{
					sumBest = sum;
					startBest = start;
					endBest = stop;
					//System.out.println("Pev best=" + sumBest + " now changed, endBest=" + endBest + " startBest=" + startBest);
				}
			}
			//System.out.println("\n\n");
			if(sumBest <= 0)
			{
				output.write("Route " + test + " has no nice parts\n");
			}
			else
			{
				output.write("The nicest part of route " + test + " is between stops " + (startBest+1) + " and " + (endBest+2) + "\n");
			}
		}
		
		sc.close();
		output.close();
	}
}
