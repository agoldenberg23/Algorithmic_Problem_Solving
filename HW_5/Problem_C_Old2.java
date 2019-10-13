import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Problem_C_Old2
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
					long num = Long.parseLong(a + b);
					long numRev = Long.parseLong(b + a);
					
					if(numRev > num)
					{
						return 1;
					}
							
					return -1;
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
		
		//output.flush();
		sc.close();
		output.close();
	}
}
