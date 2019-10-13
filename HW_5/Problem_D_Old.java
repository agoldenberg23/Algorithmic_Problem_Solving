import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

public class Problem_D_Old
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
			List<Integer> years = new LinkedList<Integer>();
			
			String[] split = sc.readLine().split(" ");
			int display = Integer.parseInt(split[0]);
			int dispInst = Integer.parseInt(split[1]);
			int error = Integer.parseInt(split[2]);
			
			int y = display;
			for(int i = 1; y < 10000; i++)
			{
				//System.out.println("Flag");
				y = display + ((error - dispInst)*i);
				
				years.add(y);
			}
			
			//System.out.println("\n\n Next Case");
			//System.out.println(years);
			
			//The next removes from the list
			for(int comp = 1; comp < compNum; comp++)
			{
				split = sc.readLine().split(" ");
				display = Integer.parseInt(split[0]);
				dispInst = Integer.parseInt(split[1]);
				error = Integer.parseInt(split[2]);
				
				List<Integer> years2 = new LinkedList<Integer>();
				
				y = display;
				for(int i = 1; y < 10000; i++)
				{
					y = display + ((error - dispInst)*i);
					
					years2.add(y);					//.remove((Integer) y);
				}
				
				years.retainAll(years2);
				
				//System.out.println(years);
			}
			
			if(years.isEmpty() == true)
			{
				output.write("Case #" + caseNum + ":\n" + "Unknown bugs detected.\n\n");
			}
			else
			{
				output.write("Case #" + caseNum + ":\n" + "The actual year is " + years.get(0) + ".\n\n");
			}
			
			caseNum++;
		}
		
		sc.close();
		output.close();
	}
}
