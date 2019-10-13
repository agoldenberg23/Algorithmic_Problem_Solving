import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Problem_E
{
	public static int palMake(int leftInd, int rightInd, int[][] table, String input)
	{
		//Base case - table already filled here
		if(table[leftInd][rightInd] != -1)
		{
			return table[leftInd][rightInd];
		}
		
		//Base case - no more string to check
		if(leftInd >= rightInd)
		{
			return 0;
		}
		
		//Do not need to alter the elements looked at, go to next pair
		if(input.charAt(leftInd) == input.charAt(rightInd))
		{
			table[leftInd][rightInd] = palMake(leftInd+1, rightInd-1, table, input);
			return table[leftInd][rightInd];
		}
		else //Need to alter, change to match first or change to match last
		{
			table[leftInd][rightInd] = Math.min(palMake(leftInd+1, rightInd, table, input), 
					palMake(leftInd, rightInd-1, table, input)) + 1;
			
			return table[leftInd][rightInd];
		}
	}
	
	public static String getPalStr(int[][] table, int len, String input)
	{
		int leftInd = 0;
		int rightInd = len-1;
		String outL = "";
		String outR = "";
		
		while(leftInd < rightInd)
		{
			//Nothing needs to be done
			if(input.charAt(leftInd) == input.charAt(rightInd))
			{
				outL = outL + input.substring(leftInd, leftInd+1);
				outR = input.substring(rightInd, rightInd+1) + outR;
				leftInd++;
				rightInd--;
			}
			else //Need to insert a char
			{
				if(table[leftInd+1][rightInd] <= table[leftInd][rightInd-1])	//Go down in table
				{
					String toAdd = input.substring(leftInd, leftInd+1);
					outL = outL + toAdd;
					outR = toAdd + outR;
					leftInd++;
				}
				else	//Go left in table
				{
					String toAdd = input.substring(rightInd, rightInd+1);
					outL = outL + toAdd;
					outR = toAdd + outR;
					rightInd--;
				}
			}
		}
		if(leftInd == rightInd)
		{
			outL = outL + input.substring(leftInd, leftInd+1);
		}
		
		return outL + outR;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out, "ASCII"), 4096);
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	
		while(true)
		{
			//Get input
			String line = sc.readLine().trim();
			
			if(line == null || line.equals(""))
			{
				break;
			}
			
			//Create and initalize variables
			int len = line.length();
			int[][] table = new int[len][len];
			for(int i = 0; i < len; i++)
			{
				table[0][i] = -1;
			}
			for(int i = 1; i < len; i++)
			{
				table[i] = table[0].clone();
			}
			
			//find the number of char needed and fill table
			int changeNum = palMake(0, len-1, table, line);
			
			for(int i = 0; i < len; i++)
			{
				for(int c = 0; c < len; c++)
				{
					System.out.print(table[i][c] + " ");
				}
				System.out.println();
			}
			
			//Make the output string
			output.write(Integer.toString(changeNum) + " " 
					+ getPalStr(table, len, line) + "\n");
			
		}
		
		sc.close();
		output.close();
	}
}
