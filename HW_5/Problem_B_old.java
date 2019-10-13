import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Problem_B_old
{
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out, "ASCII"), 4096);
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	
		List<Long> list = new LinkedList<Long>();
		String line;
		
		while(true)
		{
			line = sc.readLine();
			if(line == null)
			{
				break;
			}
			
			long num = Long.parseLong(line);
			list.add(num);
			Collections.sort(list);
			
			//Even length list
			if(list.size() % 2 == 0)
			{
				output.write(Long.toString(((list.get(list.size()/2) + list.get((list.size()/2)-1)) / 2)) + "\n");
			}
			//Odd length list
			else
			{
				output.write(Long.toString(list.get(list.size()/2)) + "\n");
			}
			//output.flush();
		}
		
		sc.close();
		output.close();
	}
}
