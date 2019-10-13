import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Problem_C_Old3
{
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out, "ASCII"), 4096);
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	
		while(true)
		{
			int num = Integer.parseInt(sc.readLine());
			
			if(num == 0)
			{
				break;
			}
			
			List<String> list = new ArrayList<String>();
			
			String[] split = sc.readLine().split(" ");
			for(int i = 0; i < num; i++)
			{
				//System.out.println("Adding " + split[i]);
				list.add(split[i]);
			}
			
			Collections.sort(list, new Comparator<String>() 
			{
				public int compare(String a, String b)
				{
					long num = Long.parseLong(a + b);
					long numRev = Long.parseLong(b + a);
					
					if(numRev > num)
					{
						return 1;
					}
							
					return -1;
				}
		    });
			
			StringBuilder sb = new StringBuilder();
			Iterator<String> itr = list.iterator();
			while(itr.hasNext() == true)
			{
				sb.append(itr.next());
			}
			
			output.write(sb.toString() + "\n");
		}
		sc.close();
		output.close();
	}
}
