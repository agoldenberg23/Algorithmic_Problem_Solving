import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

public class Problem_E
{
	public static void main(String[] args) throws IOException
	{
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out, "ASCII"), 4096);
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		
		BigInteger big0 = new BigInteger("0");
		BigInteger big1 = new BigInteger("1");
		
		while(true)
		{
			//Get the input
			String line = sc.readLine().trim();
			
			if(line.equals("-1"))
			{
				break;
			}
			
			//Special case of num = 0 or 1
			if(line.equals("0"))
			{
				output.write("10\n");
				continue;
			}
			else if(line.equals("1"))
			{
				output.write("11\n");
				continue;
			}
			
			BigInteger num = new BigInteger(line);
			
			//Get the multiples
			long[] mult = new long[10];
			
			for(int i = 9; i > 1; i--)
			{
				BigInteger temp = new BigInteger(Integer.toString(i));
				
				while(num.remainder(temp).equals(big0) && ((num.compareTo(temp) == 1) || (num.compareTo(temp) == 0)))
				{
					mult[i]++;
					num = num.divide(temp);
				}
			}
			
			//Case where num != 0 means no good number
			if(num.equals(big0) == false && num.equals(big1) == false)
			{
				output.write("There is no such number.\n");
				continue;
			}
			
			//Create the output
			String ret = "";
			
			for(int i = 1; i <= 9; i++)	//Which part of the mult array
			{
				//Add to ret for each in array
				for(long c = mult[i]; c > 0; c--)
				{
					ret = ret + Integer.toString(i);
				}
			}
			
			//String not long enogh
			if(ret.length() <= 1)
			{
				ret = "1" + ret;
			}
			
			output.write(ret + "\n");
		}
		
		sc.close();
		output.close();
	}
}
