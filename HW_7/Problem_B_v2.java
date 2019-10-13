import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Problem_B_v2 
{ 
    static int[] DFS(int W, int val[], int n, int nMax, int[] have, int haveInd)
    { 
        // Base Case (ran through all numbers or wanted = 0)
    	if (n == nMax-1 || W == 0)
    	{
    		return have;
    	}
    		
    	// Skip values that would bring us over the wanted value
    	if (val[n+1] > W)
    	{	
    		return DFS(W, val, n+1, nMax, have, haveInd);
    	}
       
    	//Include the current element
    	int[] haveInc = have.clone();
    	haveInc[haveInd] = val[n+1];
    	haveInc[0] = haveInc[0] + val[n+1];
    	int[] include = DFS(W-val[n+1], val, n+1, nMax, haveInc, haveInd+1);
    	
    	//Skip the current element
    	int[] skip = DFS(W, val, n+1, nMax, have, haveInd);
    	
    	//Pick the better of the two
    	if(include[0] >= skip[0])
    	{
    		return include;
    	}
    	else
    	{
    		return skip;
    	}
   } 
  
	public static void main(String[] args) throws IOException
	{
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out, "ASCII"), 4096);
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	
		while(true)
		{
			//Get input
			String line = sc.readLine();
			
			if(line == null || line.equals("") || line == null)
			{
				break;
			}
			
			String[] split = line.split(" ");
			
			int sum = Integer.parseInt(split[0]);
			int numbers = Integer.parseInt(split[1]);
			int[] num = new int[numbers];
			int[] used = new int[numbers+1];
			used[0] = 0;
			
			for(int i = 0; i < numbers; i++)
			{
				num[i] = Integer.parseInt(split[i+2]);
				used[i+1] = -1;
			}
			
			//Run DFS
			used = DFS(sum, num, -1, numbers, used, 1);
			
			//Print output
			for(int i = 1; i < numbers+1; i++)
			{
				if(used[i] == -1)
				{
					break;
				}
				output.write(used[i] + " ");
			}
			output.write("sum:" + used[0] + "\n");
		}
		
		sc.close();
		output.close();
	}
}