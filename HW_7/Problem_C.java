import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.BitSet;

public class Problem_C
{
	//Sets a bit and returns if the bit was set before
	public static boolean setBit(BitSet bs, int ind)
	{
		boolean ret = bs.get(ind);
		bs.set(ind);
		return ret;
	}
	
	//Sets the bits in a range, stops and returns true if a bit was already set
	public static boolean setBitRange(BitSet bs, int startInd, int endInd)
	{
		for(int i = startInd; i < endInd; i++)
		{			
			if(setBit(bs, i))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) throws IOException
	{
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out, "ASCII"), 4096);
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	
		while(true)
		{
			String[] split = sc.readLine().split(" ");
			int oneTimeTaskNumber = Integer.parseInt(split[0]);
			int repeatingTaskNumber = Integer.parseInt(split[1]);
			
			if(oneTimeTaskNumber == 0 && repeatingTaskNumber == 0)
			{
				break;
			}
			
			int tasksLeft = oneTimeTaskNumber + repeatingTaskNumber;
			BitSet bs = new BitSet(1000010);
			boolean conflictFlag = false;
			
			//Add the one time tasks
			for(int i = 0; i < oneTimeTaskNumber; i++)
			{
				tasksLeft--;
				split = sc.readLine().split(" ");
				
				if(setBitRange(bs, Integer.parseInt(split[0]), Integer.parseInt(split[1])))
				{
					conflictFlag = true;
					break;
				}
			}
			
			//Add the multi time tasks
			if(conflictFlag == false)
			{
				for(int i = 0; i < repeatingTaskNumber; i++)
				{
					tasksLeft--;
					split = sc.readLine().split(" ");
					int start = Integer.parseInt(split[0]);
					int end = Integer.parseInt(split[1]);
					int rep = Integer.parseInt(split[2]);
					
					//Do the first manualy
					if(setBitRange(bs, start, end))
					{
						conflictFlag = true;
						break;
					}
					
					//Do the rest of the repetitions
					while(true)
					{
						start = start + rep;
						end = end + rep;
						
						if(end > 1000000)
						{
							break;
						}
						
						if(setBitRange(bs, start, end))
						{
							conflictFlag = true;
							break;
						}
					}
				}
			}
			
			//write the output
			if(conflictFlag == true)
			{
				//Skip over unused lines
				while(tasksLeft > 0)
				{
					sc.readLine();
					tasksLeft--;
				}
				
				output.write("CONFLICT\n");
			}
			else
			{
				output.write("NO CONFLICT\n");
			}
		}
		
		sc.close();
		output.close();
	}
}
