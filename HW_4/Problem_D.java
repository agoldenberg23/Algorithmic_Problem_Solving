import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Problem_D
{
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out, "ASCII"), 4096);
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	
		while(true)
		{
			output.flush();
			
			int numbers = Integer.parseInt(sc.readLine());
			//int[] num = new int[numbers];
			PriorityQueue<Integer> num = new PriorityQueue<Integer>(); 
			
			if(numbers == 0)
			{
				break;
			}
			
			String[] split = sc.readLine().split(" ");
			for(int i = 0; i < numbers; i++)
			{
				num.add(Integer.parseInt(split[i]));
				//num[i] = Integer.parseInt(split[i]);
			}
			
			//Arrays.sort(num);
			
			/*for(int i = 0; i < num.length; i++)
			{
				System.out.println(num[i]);
			}*/
			
			if(num.size() < 2)
			{
				output.write(num.remove() + "\n");
				continue;
			}
			
			int sum = 0;
			int cost = 0;
			
			while(num.size() > 1)
			{
				sum = num.remove() + num.remove();
				cost = cost + sum;
				num.add(sum);
			}
			
			output.write(cost + "\n");
		}
		
		sc.close();
		output.close();
	}
}
