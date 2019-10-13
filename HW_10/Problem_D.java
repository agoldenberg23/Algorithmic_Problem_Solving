import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

public class Problem_D
{
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out, "ASCII"), 4096);
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	
		while(true)
		{
			//Get the input
			String line = sc.readLine();
			
			if(line == null || line.equals(""))
			{
				break;
			}
			
			String[] split = line.split(" ");
			int len = split.length-1;
			BigInteger[] num = new BigInteger[len];
			for(int i = 0; i < len; i++)
			{
				num[i] = new BigInteger(split[i]);
			}
			
			//Get the max len
			BigInteger mult = BigInteger.ONE;
			BigInteger best = num[0];
			
			for(int i = 0; i < len; i++)
			{
				mult = BigInteger.ONE;
				for(int c = i; c < len; c++)
				{
					mult = mult.multiply(num[c]);
					
					//Check is better max
					best = best.max(mult);
				}
			}
			
			//output
			output.write(best.toString() + "\n");
		}
		
		sc.close();
		output.close();
	}
}
