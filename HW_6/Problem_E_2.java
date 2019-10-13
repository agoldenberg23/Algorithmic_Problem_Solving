import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Problem_E_2
{
	public static class job implements Comparable<job>
	{
		int id;
		int debreaf;
		int taskLength;
		
		public job(int id, int deb, int taskLen)
		{
			this.id = id;
			this.debreaf = deb;
			this.taskLength = taskLen;
		}
		
		public String toString()
		{
			return "id=" + this.id + " debreaf=" + this.debreaf + " taskLength=" + this.taskLength;
		}

		@Override
		public int compareTo(job arg)
		{
			return Integer.compare(arg.taskLength, this.taskLength);
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out, "ASCII"), 4096);
		Scanner sc = new Scanner(System.in);
	
		int testCase = 0;
		while(true)
		{
			testCase++;
			//Read N, the number of solgers
			int solNum = sc.nextInt();
			if(solNum == 0)
			{
				break;
			}
			
			//Create the jobs needed
			job[] jobList = new job[solNum];
			
			//Input the values
			for(int sol = 0; sol < solNum; sol++)
			{
				//Read N, the number of solgers
				int deb = sc.nextInt();
				int task = sc.nextInt();
				jobList[sol] = new job(sol, deb, task);
			}
			
			//Sort the list
			Arrays.sort(jobList);
			
			/*for(int i = 0; i < solNum; i++)
			{
				System.out.println(jobList[i].toString());
			}
			System.out.println();*/
			
			//Get the total time
			int time = 0;
			int ans = 0;
			for(int i = 0; i < solNum; i++)
			{
				time = time + jobList[i].debreaf;
				ans = Math.max(ans, time + jobList[i].taskLength);
			}
			
			//Output
			output.write("Case " + testCase + ": " + Integer.toString(ans) + "\n");
		}
		
		sc.close();
		output.close();
	}
}
