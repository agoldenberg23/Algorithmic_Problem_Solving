import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;

public class Problem_c_Fin
{
	public static void main(String[] args) throws IOException
	{
		//Scanner sc = new Scanner(System.in);
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out, "ASCII"), 4096);
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		
		boolean first = true;
		while(true)
		{	
			int dayNum = Integer.parseInt(sc.readLine());
			//int dayNum = sc.nextInt();
		
			if(dayNum == 0)
			{
				break;
			}
		
			if(first == false)
			{
				//output.write("\n");
			}
			
			//Make a priority que to use
			LinkedList<Integer> urn = new LinkedList<Integer>();
			
			//Count the amount spent
			long spent = 0;
			
			//For each day
			for(int day = 0; day < dayNum; day++)
			{
				//Insert the bills into the urn
				//int billNum = sc.nextInt();
				//System.out.println(sc.nextLine());
				//System.out.println(line);
				String[] splited = sc.readLine().split(" ");
				int billNum = Integer.parseInt(splited[0]);
				for(int i = 1; i <= billNum; i++)
				{
					urn.add(Integer.parseInt(splited[i]));
				}
				
				//Sort the list
				if(billNum > 0)
				{
					Collections.sort(urn);
				}
				
				//Get the highest and lowest
				int min = urn.pollFirst();		//remove(0);
				int max = urn.pollLast();		//remove(urn.size()-1);
				spent = spent + (max - min);
			}
			
			output.write(Long.toString(spent) + "\n");
			first = false;
		}
		
		sc.close();
		output.close();
	}
}
