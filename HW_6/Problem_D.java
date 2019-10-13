import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Problem_D
{
	public static class job
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
			
			//Create a que to sort the lists
			Queue<job> que = new PriorityQueue<>(new Comparator<job>()
			{
				public int compare(job jobA, job jobB)
				{
					int timeA = jobA.shortestTime();
					int timeB = jobB.shortestTime();
					
					/*
					System.out.println("For job A " + jobA.toString() + " short=" + timeA);
					System.out.println("For job B " + jobB.toString() + " short=" + timeB);
					System.out.println("Compare is " + Integer.compare(timeA, timeB));
					*/
					
					if(Integer.compare(timeA, timeB) == 0)
					{
						timeA = jobA.longestTime();
						timeB = jobB.longestTime();
					}
					
					return Integer.compare(timeA, timeB);
				}
			});
			
			//Create the jobs
			job[] jobList = new job[jobs];
			String[] split = sc.readLine().split(" ");
			for(int i = 0; i < jobs; i++)
			{
				jobList[i] = new job(i, Integer.parseInt(split[i]));
			}
			split = sc.readLine().split(" ");
			for(int i = 0; i < jobs; i++)
			{
				jobList[i].timeB = Integer.parseInt(split[i]);
				que.add(jobList[i]);
			}
			
			//Set the order
			int[] list = new int[jobs];
			int left = 0;
			int right = list.length-1;
			while(que.isEmpty() == false)
			{
				job next = que.poll();
				
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
			
			//run the jobs to get the min time
			int taskA = 0;
			int taskB = 0;
			int Afin = 0;
			int time = 1;
			
			//Add in the first task to A manualy
			Afin = jobList[list[taskA]].timeA;
			//System.out.println("Add task A at time 0" + " Afin=" + Afin + " task=" + list[taskA]);
			taskA++;
			
			Queue<Integer> BTaskList = new LinkedList<Integer>(); 
			//Queue<Integer> timeQue = new PriorityQueue<>(); 
			while(true)
			{
				//jobList[list[taskA]].timeA
				if(time == Afin && taskA <= jobs)
				{
					//System.out.println("Finish task A at time " + time);
					
					//Send to next task
					if(taskA < jobs)
					{
						//System.out.println("Add task A at time " + time + " Afin=" + Afin + " task=" + list[taskA]);
						Afin = time + jobList[list[taskA]].timeA;
					}
					
					//Add to task B list
					//System.out.println("Add task B at time " + time + " Afin=" + Afin + " task=" + list[taskA-1]);
					BTaskList.add(time + jobList[list[taskA-1]].timeB);
					
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
				
				time++;
			}
			System.out.println(time);
		}
		
		sc.close();
		output.close();
	}
}
