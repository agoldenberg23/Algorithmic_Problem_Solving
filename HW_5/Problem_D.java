import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Problem_D
{
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out, "ASCII"), 4096);
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	
		while(true)
		{
			int caseNum = 1;
			
			int compNum = Integer.parseInt(sc.readLine());
			
			if(compNum == 0)
			{
				break;
			}
			
			//The first computer fills the list
			boolean[] years = new boolean[10000];
			Arrays.fill(years, true);
			
			//The next removes from the list
			for(int comp = 0; comp < compNum; comp++)
			{
				String[] split = sc.readLine().split(" ");
				int display = Integer.parseInt(split[0]);
				int dispInst = Integer.parseInt(split[1]);
				int error = Integer.parseInt(split[2]);
				
				//Fill before display year false
				for(int i = 0; i < display; i++)
				{
					years[i] = false;
				}
				
				for(int i = display; i < 10000; i++)
				{
					//I is mult of error amount and after display
					if((i != display) && (i - display) % (error - dispInst) != 0)
					{
						years[i] = false;
					}
				}
			}
			
			output.write("Case #" + caseNum + ":\n");
			//Get the output
			boolean find = false;
			for(int i = 0; i < 10000; i++)
			{
				if(years[i] == true)
				{
					find = true;
					output.write("The actual year is " + i + ".\n");
					break;
				}
			}
			
			if(find == false)
			{
				output.write("Unknown bugs detected.\n");
			}
			
			output.write("\n");
			
			caseNum++;
		}
		
		sc.close();
		output.close();
	}
}
