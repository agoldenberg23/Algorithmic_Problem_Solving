import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Problem_E
{
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out, "ASCII"), 4096);
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	
		Set<String> dic = new TreeSet<String>();
		
		while(true)
		{
			String line = sc.readLine();
			
			if(line.equals("!!!"))
			{
				break;
			}
			
			if(line == null)
			{
				break;
			}
			
			String sub = "";
			
			for(int i = 0; i < line.length(); i++)
			{
				char a = line.charAt(i);
				//Upper case
				if(a >= 'A' && a <= 'Z')
				{
					sub = sub + (char)(a + 32);
					//System.out.println("Adding " + a + " to string from upper");
				}
				//Lower case
				else if(a >= 'a' && a <= 'z')
				{
					sub = sub + a;
					//System.out.println("Adding " + a + " to string");
				}
				else
				{
					if(sub.isEmpty() == false)
					{
						//System.out.println("Sub add " + sub);
						dic.add(sub);
					}
					sub = "";
				}
			}
			
			if(sub.isEmpty() == false)
			{
				dic.add(sub);
			}
			
		}
		
		//System.out.println(dic);
		//Print the output
		Iterator<String> itr = dic.iterator();
		while(itr.hasNext())
		{
			output.write(itr.next() + "\n");
		}
		output.flush();
		
		sc.close();
		output.close();
	}
}
