import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Problem_B
{
	static boolean debug = false;
	static int bestSum = 0;
	static int[] best;
	
	//Have should be init to 0, -1, -1... (0th position is the sum)
	public static int[] DFS(int[] numbers, int numbersLoc, int[] have, int haveLoc, int target, int numbersAmount)
	{
		if(debug)
		{
			System.out.println("DFS: BestSum=" + bestSum + " numberLoc=" + numbersLoc + " haveLoc=" + haveLoc + " ");
			for(int i = 0; i < have.length; i++)
			{
					System.out.print(have[i] + " ");
			}
			System.out.println();
		}
			
		//Base cases 	Sum went over is checked before adding
		//Found a solution
		if(have[0] == target)
		{
			best = have.clone();
			return have;
		}
		
		//Went past the numbers array bound
		if(numbersLoc >= numbersAmount)
		{
			if(debug)
				System.out.println("Hit end of numbers");
			
			return have;
		}
		
		//Found the answar
		if((have[0] + numbers[numbersLoc]) == target)
		{
			if(debug)
				System.out.println("Found answar");
			
			have[0] = have[0] + numbers[numbersLoc];
			have[haveLoc] = numbers[numbersLoc];
			bestSum = target;
			return have;
		}
		
		//Add the next number
		if((have[0] + numbers[numbersLoc]) < target)
		{
			if(debug)
				System.out.println("Adding number " + numbers[numbersLoc]);
			
			int[] haveTemp = have.clone();
			haveTemp[0] = have[0] + numbers[numbersLoc];
			haveTemp[haveLoc] = numbers[numbersLoc];
			if(haveTemp[0] > bestSum)
			{
				bestSum = haveTemp[0];
				best = haveTemp.clone();
			}
			haveTemp = DFS(numbers, numbersLoc+1, haveTemp, haveLoc+1, target, numbersAmount);
			
			//Found an answar
			if(haveTemp[0] == target)
			{
				best = haveTemp.clone();
				return haveTemp;
			}
		}
		else if(debug)
		{
			System.out.println("Not going to add");
		}
		
		//Skip the next number
		if(debug)
			System.out.println("Skiping number");
		
		int[] haveTemp = have.clone();
		haveTemp = DFS(numbers, numbersLoc+1, haveTemp, haveLoc, target, numbersAmount);
		
		//Check for answar
		if(haveTemp[0] == target)
		{
			best = haveTemp.clone();
			return haveTemp;
		}
		
		if(debug)
			System.out.println("Send out have uneddited");
		
		return have;
	}
	
	public static void main(String[] args) throws IOException
	{
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out, "ASCII"), 4096);
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	
		while(true)
		{
			String line = sc.readLine();
			
			if(line == null || line == "" || line == null)
			{
				break;
			}
			
			String[] split = line.split(" ");
			
			int sum = Integer.parseInt(split[0]);
			int numbers = Integer.parseInt(split[1]);
			if(debug)
				System.out.println("target=" + sum + " numbers=" + numbers);
			
			int[] num = new int[numbers];
			int[] have = new int[numbers+1];
			best = new int[numbers+1];
			have[0] = 0;
			best[0] = 0;
			
			for(int i = 0; i < numbers; i++)
			{
				num[i] = Integer.parseInt(split[i+2]);
				have[i+1] = -1;
				best[i+1] = -1;
				//System.out.print(num[i] + " ");
			}
			//System.out.println();
			
			//run the DFS
			bestSum = 0;
			DFS(num, 0, have, 1, sum, numbers);
			
			for(int i = 1; i < numbers+1; i++)
			{
				if(best[i] == -1)
				{
					break;
				}
				System.out.print(best[i] + " ");
			}
			System.out.println("sum:" + best[0]);
		}
		
		sc.close();
		output.close();
	}
}
