import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class Problem_E
{
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out, "ASCII"), 4096);
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	
		int testNum = Integer.parseInt(sc.readLine());
		
		//Run the tests
		for(int tests = 0; tests < testNum; tests++)
		{
			long snowNum = Integer.parseInt(sc.readLine());
			
			//output.write("Test " + tests + " snowNum = " + snowNum + "\n");
			
			//Variables needed
			HashMap<Long, Long> map = new HashMap<Long, Long>();
			long max = 0;
			long start = 0;
			
			//For each input num
			for(long i = 0; i < snowNum; i++)
			{
				long num = Integer.parseInt(sc.readLine().trim());
				
				//Hash has seen this
				if(map.containsKey(num))
				{
					long ind = map.get(num);
					if(ind >= start)
					{
						start = ind+1;
					}
				}
				
				if(i - start+1 > max)
				{
					max = i - start+1;
				}
				map.put(num, i);
				
				//output.write(num + " ");
			}
			//output.write("\n");
			
			output.write(max + "\n");
		}
		
		sc.close();
		output.close();
	}
}
