import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Problem_D_3
{
	public static class job implements Comparable<job>
	{
		int jobNum;	//Job id number
		int timeA;	//Time for A to complete
		int timeB;	//Time for B to complete
		
		public job(int jobNum, int timeA, int timeB)
		{
			this.jobNum = jobNum;
			this.timeA = timeA;
			this.timeB = timeB;
		}
		
		public job(int jobNum, int timeA)
		{
			this.jobNum = jobNum;
			this.timeA = timeA;
		}
		
		public int shortestTime()
		{
			if(this.timeB < this.timeA)
			{
				return this.timeB;
			}
			
			return this.timeA;
		}
		
		public int longestTime()
		{
			if(this.timeB > this.timeA)
			{
				return this.timeB;
			}
			
			return this.timeA;
		}
		
		public boolean isAShortest()
		{
			if(this.timeB < this.timeA)
			{
				return false;
			}
			
			return true;
		}
		
		public String toString()
		{
			return "job= " + this.jobNum + " A=" + this.timeA + " B=" + this.timeB;
		}

		@Override
		public int compareTo(job arg)
		{
			int timeA = this.shortestTime();
			int timeB = arg.shortestTime();
			
			if(Integer.compare(timeA, timeB) == 0)
			{
				timeA = this.longestTime();
				timeB = arg.longestTime();
			}
			
			return Integer.compare(timeA, timeB);
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out, "ASCII"), 4096);
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		
		while(true)
		{
			String line = sc.readLine();
			
			if(line == null)
			{
				break;
			}
			if(line.equals(""))
			{
				break;
			}
			
			int jobs = 0;
			try {
				jobs = Integer.parseInt(line);
			}
			catch (Exception e){
				System.out.println("ERROR");
			}
			
			//Create the jobs
			job[] jobList = new job[jobs];
			job[] jobSort = new job[jobs];
			String[] split = {};
			String[] split2 = {};
			try {
				split = sc.readLine().split(" ");
				split2 = sc.readLine().split(" ");
			}
			catch (Exception e){
				System.out.println("ERROR");
			}
			
			for(int i = 0; i < jobs; i++)
			{
				try {
					jobList[i] = new job(i, Integer.parseInt(split[i]), Integer.parseInt(split2[i]));
				}
				catch (Exception e){
					System.out.println("ERROR");
				}
					
				jobSort[i] = jobList[i];
			}
			
			//Sort the list
			Arrays.sort(jobSort);
			
			//Set the order
			int[] list = new int[jobs];
			int left = 0;
			int right = list.length-1;
			
			for(int i = 0; i < jobList.length; i++)
			{
				job next = jobSort[i];
				
				if(next.isAShortest() == true)
				{
					list[left] = next.jobNum;
					left++;
				}
				else
				{
					list[right] = next.jobNum;
					right--;
				}
			}
			
			//run the jobs to get the min
			int time = 0;
			int time2 = 0;
			
			for(int i = 0; i < jobs; i++)
			{
				time = time + jobList[list[i]].timeA;
				time2 = Math.max(time, time2) + jobList[list[i]].timeB;
			}
			
			System.out.println(time2);
		}
		
		sc.close();
		output.close();
	}
}
