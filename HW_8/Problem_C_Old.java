import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Stack;

public class Problem_C_Old
{
	//Flips a pancake from the specified start
	public static int[] flip(int[] pan, int start)
	{
		//Copy the section
		int end = pan.length;
		int[] coppy = new int[end];
		for(int i = start; i < end; i++)
		{
			coppy[i] = pan[i];
		}
		
		//Move over the pan
		for(int i = (end-1), ind = start; i >= start; i--, ind++)
		{
			pan[ind] = coppy[i];
		}
		
		return pan;
	}
	
	//Sorts an array by flips
	public static Stack<Integer> flipSort(int[] pan)
	{
		//The list of flips
		Stack<Integer> flipsDone = new Stack<Integer>();
		
		//An already sorted stack
		int[] sortStack = pan.clone();
		Arrays.sort(sortStack);
		
		for(int i = 0; i < pan.length-1; i++)
		{
			//Skip already in corect place
			if(pan[i] == sortStack[i])
			{
				continue;
			}
			
			//Find where it should be
			int ind = -1;
			for(int c = i+1; c < pan.length; c++)
			{
				if(pan[c] == sortStack[i])
				{
					ind = c;
					break;
				}
			}
			
			if(ind != pan.length-1)
			{
				flipsDone.push(ind+1);
				pan = flip(pan, ind);
			}
			
			flipsDone.push(i+1);
			pan = flip(pan, i);
		}
		
		return flipsDone;
	}
	
	public static void main(String[] args) throws IOException
	{
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out, "ASCII"), 4096);
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	
		while(true)
		{
			String line = sc.readLine();
			
			if(line == null || line.equals("") || line == null)
			{
				break;
			}
			
			String[] split = line.split(" ");
			int[] pancakes = new int[split.length];
			
			for(int i = 0; i < pancakes.length; i++)
			{
				pancakes[i] = Integer.parseInt(split[i]);
			}
			
			Stack<Integer> flipsDone = flipSort(pancakes.clone());
			
			//Print the output
			for(int i = 0; i < pancakes.length-1; i++)
			{
				output.write(pancakes[i] + " ");
			}
			output.write(pancakes[pancakes.length-1] + "\n");
			
			while(flipsDone.isEmpty() == false)
			{
				output.write(flipsDone.pop() + " ");
			}
			output.write("0\n");
		}
		
		sc.close();
		output.close();
	}
}
