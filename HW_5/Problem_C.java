import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Problem_C
{
	public static int comp(String a, String b)
	{
		//System.out.println("Comp " + a + " to " + b + " is " + (a+b).compareTo(b+a));
		return (a+b).compareTo(b+a);
	}
	
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
			
			String[] list = sc.readLine().split(" ");
			
			for(int i = 0; i < num; i++)
			{
				for(int c = i+1; c < num; c++)
				{
					if(list[c].charAt(0) > list[i].charAt(0))
					{
						String temp = list[i];
						list[i] = list[c];
						list[c] = temp;
					}
					else if(list[c].charAt(0) == list[i].charAt(0))
					{
						if(comp(list[c], list[i]) > 0)
						{
							String temp = list[i];
							list[i] = list[c];
							list[c] = temp;
						}
					}
				}
			}
			
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < list.length; i++)
			{
				sb.append(list[i]);
			}
			
			output.write(sb.toString() + "\n");
		}
		sc.close();
		output.close();
	}
}
