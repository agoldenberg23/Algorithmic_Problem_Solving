import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Problem_D_2
{
	public static class job implements Comparable<job>
	{
		int jobNum;	//Job id number
		int timeA;	//Time for A to complete
		int timeB;	//Time for B to complete
		//char curentWork = '-';	//Says if A or B is working on this (last part)
		
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
			
			if(line.equals("") || line == null)
			{
				break;
			}
			
			int jobs = Integer.parseInt(line);
			
			//Create the jobs
			job[] jobList = new job[jobs];
			job[] jobSort = new job[jobs];
			String[] split = sc.readLine().split(" ");
			String[] split2 = sc.readLine().split(" ");
			for(int i = 0; i < jobs; i++)
			{
				jobList[i] = new job(i, Integer.parseInt(split[i]), Integer.parseInt(split2[i]));
				jobSort[i] = jobList[i];
			}
			
			//Sort the list
			Arrays.sort(jobSort);
			
			/*for(int i = 0; i < jobList.length; i++)
			{
				System.out.println(jobSort[i].toString());
			}*/
			
			//Set the order
			int[] list = new int[jobs];
			int left = 0;
			int right = list.length-1;
			
			for(int i = 0; i < jobList.length; i++)
			{
				job next = jobList[i];
				
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
			
			/*for(int i = 0; i < list.length; i++)
			{
				System.out.print(list[i] + " ");
			}
			System.out.println();*/
			
			//run the jobs to get the min
			int taskA = 0;
			int taskB = 0;
			int Afin = 0;
			
			//Add in the first task to A manualy
			Afin = jobList[list[taskA]].timeA;
			//System.out.println("Add task A at time 0" + " Afin=" + Afin + " task=" + list[taskA]);
			taskA++;
			
			int time = Afin;
			
			Queue<Integer> BTaskList = new LinkedList<Integer>();
			while(true)
			{
				//System.out.println("Time = " + time);
				
				//jobList[list[taskA]].timeA
				if(time == Afin && taskA <= jobs)
				{
					//System.out.println("Finish task A at time " + time);
					
					//Send to next task
					if(taskA < jobs)
					{
						Afin = time + jobList[list[taskA]].timeA;
						//System.out.println("Add task A at time " + time + " Afin=" + Afin + " task=" + list[taskA]);
					}
					
					//Add to task B list
					BTaskList.add(time + jobList[list[taskA-1]].timeB);
					//System.out.println("Add task B at time " + time + " Bfin= " + (time + jobList[list[taskA-1]].timeB) +" task=" + list[taskA-1]);
					
					taskA++;
				}
				
				//If B can, work
				if(BTaskList.isEmpty() == false && time == BTaskList.peek())
				{
					//System.out.println("Finish task B at time " + time + " task=" + list[taskB]);
					
					BTaskList.poll();
					
					taskB++;
				}
				
				//Finished
				if(taskB == jobs)
				{
					break;
				}
				
				if(BTaskList.isEmpty() == false)
				{
					if(taskA > jobs)
					{
						//System.out.println("Time incrament to (A fin, B not): " + BTaskList.peek());
						time = BTaskList.peek();
					}
					else
					{
						//System.out.println("Time incrament to (A and B): " + Math.min(BTaskList.peek(), Afin) + " taskA=" + taskA);
						time = Math.min(BTaskList.peek(), Afin);
					}
				}
				else
				{
					//System.out.println("Time incrament to (A only work): " + Afin);
					time = Afin;
				}
			}
			System.out.println(time);
		}
		
		sc.close();
		output.close();
	}
}
