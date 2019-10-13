import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Problem_C
{
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out, "ASCII"), 4096);
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	
		int testNum = Integer.parseInt(sc.readLine());
		
		for(int tests = 0; tests < testNum; tests++)
		{
			//Input the book and scribe num
			String[] strSplit = sc.readLine().split(" ");
			int bookNum = Integer.parseInt(strSplit[0]);
			int scribeNum = Integer.parseInt(strSplit[1]);
			
			//Get the book lengths
			long[] books = new long[bookNum];
			strSplit = sc.readLine().split(" ");
			long largestBook = 0;
			long pageTot = 0;
			for(int i = 0; i < bookNum; i++)
			{
				books[i] = Long.parseLong(strSplit[i]);
				pageTot = pageTot + books[i];
				if(books[i] > largestBook)
				{
					largestBook = books[i];
				}
			}
			
			//Find the split
			//Binary search with largest book = lower bound, pageTot = upper bound
			long lowBound = largestBook;
			long upBound = pageTot;
			while(lowBound < upBound)
			{
				long middle = (lowBound + upBound) / 2;
				//Middle represents the max amount for each scribe
				
				//Check if this is too small or too large
				int splitNum = 0;
				long sum = 0;
				boolean scribesLeft = true;
				for(int i = bookNum-1; i >= 0; i--)
				{
					if(sum + books[i] <= middle)	//Do not split yet
					{
						sum = sum + books[i];
					}
					else	//Found next split point
					{
						sum = books[i];
						splitNum++;
						if(splitNum >= scribeNum)
						{
							//Used up all of the scribes before the end
							scribesLeft = false;
							break;
						}
					}
				}
				
				//output.write("low=" + lowBound + " up=" + upBound + " mid=" + middle + "\n");
				
				if(scribesLeft == true)	//Check lower half
				{
					upBound = middle;
				}
				else	//Check upper half
				{
					lowBound = middle + 1;
				}
			}
			
			//output.write("Last bound = " + lowBound + "\n");
			
			//With the middle get and print the split
			String out = "";
			long sum = 0;
			int scribe = scribeNum-2;
			for(int i = bookNum-1; i >= 0; i--)
			{
				//output.write("scribe=" + scribe + " i=" + i + "\n");
				
				if(scribe == i)
				{
					//output.write("scribe=i\n");
					scribe--;
					sum = books[i];
					out = Long.toString(books[i]) + " /" + out;
					if(i != 0)
					{
						out = " " + out;
					}
				}
				else if(sum + books[i] <= lowBound)
				{
					//output.write("still adding\n");
					sum = sum + books[i];
					out = Long.toString(books[i]) + out;
					if(i != 0)
					{
						out = " " + out;
					}
				}
				else
				{
					//output.write("splitting");
					scribe--;
					sum = books[i];
					out = Long.toString(books[i]) + " /" + out;
					if(i != 0)
					{
						out = " " + out;
					}
				}
			}
			
			output.write(out + "\n");
		}
		
		sc.close();
		output.close();
	}
}
