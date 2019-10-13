import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Problem_C_Old
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
			
			Queue<String> que = new PriorityQueue<>(new Comparator<String>()
			{
				public int compare(String a, String b)
				{
					//System.out.println("Comparing " + a + " and " + b);
					int aLen = 0;
					int bLen = 0;
					
					while(aLen < a.length() && bLen < b.length())
					{
						//System.out.println("Char a = " + a.charAt(aLen) + " Char b = " + b.charAt(bLen));
						if(a.charAt(aLen) > b.charAt(bLen))
						{
							//System.out.println(a + " is larger");
							return -1;
						}
						else if(b.charAt(bLen) > a.charAt(aLen))
						{
							//System.out.println(b + " is larger");
							return 1;
						}
						
						aLen++;
						bLen++;
					}
					
					if(a.length() < b.length())
					{
						return -1;
					}
					else if(b.length() < a.length())
					{
						return 1;
					}
					
					return 0;
				}
			});
			
			String[] split = sc.readLine().split(" ");
			for(int i = 0; i < num; i++)
			{
				//System.out.println("Adding " + split[i]);
				que.add(split[i]);
			}
			
			//System.out.println("\nStarting to remove\n");
			
			/*while(que.isEmpty() == false)
			{
				System.out.println("Removing " + que.remove());
			}*/
			
			StringBuilder sb = new StringBuilder();
			
			while(que.isEmpty() == false)
			{
				sb.append(que.remove());
			}
			
			output.write(sb.toString() + "\n");
		}
		
		sc.close();
		output.close();
	}
}
