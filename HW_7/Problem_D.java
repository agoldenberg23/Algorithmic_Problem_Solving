import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Problem_D 
{
	//Checks how far off we are from even
	static int error(int[] in)
	{
		return Math.abs(in[0] - in[1]);
	}
	
    static int[] DFS(int val[], int n, int nMax, int[] have) 
    {
        // Base Case
    	if(n == nMax-1) 
    	{	
    		return have;
    	}
    	
    	//Include the element in group 1
    	int[] haveA = {-1, -1, -1, -1};
    	if(have[2] <= have[3] + 1)		//Checks for group 1 being at most 1 more element
    	{
    		haveA = have.clone();
    		haveA[0] = haveA[0] + val[n+1];
    		haveA[2] = haveA[2] + 1;
    		haveA = DFS(val, n+1, nMax, haveA);
    	}
    		
    	//Include the element in group 2
    	int[] haveB = {-1, -1, -1, -1};
    	if(have[3] <= have[2] + 1)		//Checks for group 2 being at most 1 more element
    	{
    		haveB = have.clone();
    		haveB[1] = haveB[1] + val[n+1];
    		haveB[3] = haveB[3] + 1;
    		haveB = DFS(val, n+1, nMax, haveB);
    	}
    	
    	//Did not run one of the cases
    	if(haveA[0] == -1)
    	{
    		return haveB;
    	}
    	else if(haveB[0] == -1)
    	{
    		return haveA;
    	}
    	
    	//Pick best of the two options
    	if(error(haveA) <= error(haveB))
    	{
    		return haveA;
    	}
    	else
    	{
    		return haveB;
    	}
   } 
  
	public static void main(String[] args) throws IOException
	{
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out, "ASCII"), 4096);
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	
		int testNum = Integer.parseInt(sc.readLine());
		
		for(int test = 0; test < testNum; test++)
		{
			if(test != 0)
			{
				output.write("\n");
			}
			
			sc.readLine();
			int pep = Integer.parseInt(sc.readLine());
			int[] pepList = new int[pep];
			
			for(int i = 0; i < pep; i++)
			{
				pepList[i] = Integer.parseInt(sc.readLine());
			}
			
			int[] have = {0, 0, 0, 0};
			have = DFS(pepList, -1, pep, have);
			
			if(have[0] <= have[1])
			{
				output.write(have[0] + " " + have[1] + "\n");
			}
			else
			{
				output.write(have[1] + " " + have[0] + "\n");
			}
		}
		
		sc.close();
		output.close();
	}
}