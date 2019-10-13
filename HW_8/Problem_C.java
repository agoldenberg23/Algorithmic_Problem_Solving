import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Problem_C
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
	
	 //Records the flips done
	static Stack<Integer> flipsDone = new Stack<Integer>();
	
	//Sorts an array by flips
	public static int[] flipSort(int[] pan, int start)
	{
		//System.out.println("FlipSort for start=" + start);
		
		//Base case, no more to sort
		if(start >= pan.length-1)
		{
			return pan;
		}
		
		//search for the largest
		int smallestLoc = start;
		int last = -1;
		boolean flag = false;
		for(int i = start; i < pan.length; i++)
		{
			if(pan[i] <= last)
			{
				flag = true;
			}
			last = pan[i];
			
			if(pan[i] < pan[smallestLoc])
			{
				smallestLoc = i;
			}
		}
		
		//already sorted
		if(flag == false)
		{
			//System.out.println("Already sorted");
			return pan;
		}
		
		//System.out.println("smallestLoc=" + smallestLoc);
		
		if(smallestLoc != start)
		{
			//System.out.println("Need to flip");
			//Flip the smallest to the bottom of the stack (end of array)
			if(smallestLoc != pan.length-1)
			{
				flipsDone.push(smallestLoc+1);
				flip(pan, smallestLoc);
			}
			//Then flip to the best position
			flipsDone.push(start+1);
			flip(pan, start);
		}
		
		//Recursively call
		pan = flipSort(pan, start+1);
		
		return pan;
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
				//System.out.print(pancakes[i] + " ");
			}
			//System.out.println();
			
			while(flipsDone.isEmpty() == false)
			{
				flipsDone.pop();
			}
			
			int[] coppy = pancakes.clone();
			pancakes = flipSort(pancakes, 0);
			
			/*int last = -1;
			for(int i = 0; i < pancakes.length; i++)
			{
				if(pancakes[i] < last)
				{
					System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Error on input:");
					for(int c = 0; c < pancakes.length; c++)
					{
						System.out.print(pancakes[c] + " ");
					}
					last = -10;
					break;
				}
				//System.out.print(pancakes[i] + " ");
			}
			System.out.println();
			if(last == -10)
			{
				pancakes[1000000] = 1;
				break;
			}*/
			
			//Print the output
			for(int i = 0; i < pancakes.length-1; i++)
			{
				output.write(coppy[i] + " ");
			}
			output.write(coppy[pancakes.length-1] + "\n");
			
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
