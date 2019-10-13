import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Problem_E_V2
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
	
	public static String getPalStr(int[][] table, int len, int change, String input)
	{	
		String output = "";
		int leftInd = 0;
		int rightInd = len-1;
		char[] out = new char[len + change];
		int outL = 0;
		int outR = len + change -1;
		
		try {
		
		while(leftInd < rightInd)
		{
			//Nothing needs to be done
			if(input.charAt(leftInd) == input.charAt(rightInd))
			{
				out[outL] = input.charAt(leftInd);
				out[outR] = input.charAt(rightInd);
				leftInd++;
				rightInd--;
				outR--;
				outL++;
			}
			else //Need to insert a char
			{
				if(table[leftInd+1][rightInd] <= table[leftInd][rightInd-1])	//Go down in table
				{
					char toAdd = input.charAt(leftInd);
					out[outL] = toAdd;
					out[outR] = toAdd;
					leftInd++;
					outL++;
					outR--;
				}
				else	//Go left in table
				{
					char toAdd = input.charAt(rightInd);
					out[outL] = toAdd;
					out[outR] = toAdd;
					rightInd--;
					outL++;
					outR--;
				}
			}
		}
		if(leftInd == rightInd)
		{
			out[outL] = input.charAt(leftInd);
		}
		
		//Convert into a string
		output = String.valueOf(out);
		
		}catch(Exception e)
		{
			  System.out.println("ERROR");
		}
		
		return output;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out, "ASCII"), 4096);
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	
		while(true)
		{
			//Get input
			String line = sc.readLine();
			
			if(line == null || line.equals(""))
			{
				break;
			}
			
			line = line.trim();
			
			//Create and initalize variables
			int len = line.length();
			int[][] table = new int[len][len];
			for(int i = 0; i < len; i++)
			{
				for(int c = 0; c < len; c++)
				{
					table[i][c] = -1;
				}
			}
			
			try{
			//find the number of char needed and fill table
			int changeNum = palMake(0, len-1, table, line);
			
			String out = getPalStr(table, len, changeNum, line);

			//Make the output string
			output.write(Integer.toString(changeNum) + " "  + out + "\n");
			
			}catch(Exception e)
			{
				System.out.println("ERROR");
			}
			
		}
		
		sc.close();
		output.close();
	}
}
