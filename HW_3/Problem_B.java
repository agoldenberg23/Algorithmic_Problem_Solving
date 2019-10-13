import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Problem_B
{
	public static class person implements Comparable<person>
	{
		int id;
		int arrTime;
		int officeNum;
		Queue<Integer> office;
		
		public person(int id, int time, int officeNum, Queue<Integer> office)
		{
			this.id = id;
			this.arrTime = time;
			this.officeNum = officeNum;
			this.office = office;
		}
		
		public int getOffice()
		{
			return this.office.poll();
		}
		
		public String toString()
		{
			return "id: " + id + " queLength: " + office.size();
		}

		@Override
		public int compareTo(person arg)
		{
			if(this.id == arg.id)
			{
				return 0;
			}
			else if(this.id > arg.id)
			{
				return 1;
			}
			return -1;
			//return Integer.compare(this.id, arg.id);
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out, "ASCII"), 4096);
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	
		int caseNum = Integer.parseInt(sc.readLine());
		
		//For each cases
		for(int cases = 0; cases < caseNum; cases++)
		{
			//Input data
			String[] splited = sc.readLine().split(" ");
			int visNum = Integer.parseInt(splited[0]);
			int docNum = Integer.parseInt(splited[1]);
			
			//Input the visitors
			List<person> vis = new ArrayList<person>();
			
			for(int i = 0; i < visNum; i++)
			{
				splited = sc.readLine().split(" ");
				int timeStart = Integer.parseInt(splited[0]);
				int officeNum = Integer.parseInt(splited[1]);
				Queue<Integer> off = new LinkedList<>();
				for(int c = 0; c < officeNum; c++)
				{
					off.add(Integer.parseInt(splited[c+2]));
				}
				vis.add(new person(i, timeStart, officeNum, off));
				//System.out.println(vis[i].toString());
			}
			
			//System.out.println(vis.get(0).compareTo(vis.get(1)));
			
			//Create the doctor room queues in a list
			List<Queue<person>> doc = new ArrayList<Queue<person>>();
			List<Queue<person>> line = new ArrayList<Queue<person>>();
			
			for(int i = 0; i < docNum; i++)
			{
				doc.add(new LinkedList<>());
				line.add(new PriorityQueue<person>());
			}
			
			/*
			line.get(2).add(vis[3]);
			line.get(1).add(vis[1]);
			System.out.println(line.get(1).poll().toString());
			System.out.println(line.get(2).poll().toString());
			*/
			
			//Run the simulation
			int time = 0;
			boolean fin = false;
			while(fin == false)
			{
				//System.out.println("TIME = " + time);
				
				fin = true;
				
				if(vis.isEmpty() == false)
				{
					fin = false;
				}
				
				//Have new people enter the hospital line
				Iterator<person> itr = vis.iterator();
				while(itr.hasNext())
				{
					//System.out.println(itr.next());
					person a = itr.next();
					if(a.arrTime == time)
					{
						int docNeed = a.office.poll();
						//System.out.println("Enter " + a.toString() + " to the list at " + (docNeed));
						line.get(docNeed-1).add(a);
						itr.remove();
					}
				}
				
				//System.out.println("Time: " + time + " " + vis.size());
				
				//Add people from waiting to the doctor que
				Iterator<Queue<person>> itr3 = line.iterator();
				int itr3Count = -1;
				while(itr3.hasNext())
				{
					itr3Count++;
					Queue<person> a = itr3.next();
					while(a.isEmpty() == false)
					{
						fin = false;
						person pac = a.poll();
						//System.out.println("Adding " + pac.toString() + " to doc at " + (itr3Count+1));
						doc.get(itr3Count).add(pac);
						//System.out.println(doc.get(itr3Count).toString());
					}
				}
				
				//Handle the doctors offices and send pacents to lines
				Iterator<Queue<person>> itr2 = doc.iterator();
				while(itr2.hasNext())
				{
					Queue<person> a = itr2.next();
					if(a.isEmpty() == false)
					{
						fin = false;
						person pac = a.poll();
						if(pac.office.peek() != null)
						{
							int docNeed = pac.office.poll();
							//System.out.println("Doc move " + pac.toString() + " to the list at " + (docNeed));
							line.get(docNeed-1).add(pac);
						}
						/*else
						{
							System.out.println("Removing " + pac.toString());
						}*/
					}
				}
				
				
				
				
				time++;
			}
			
			output.write(Integer.toString(time-1) + "\n");
			//System.out.println(time-1);
			
		}
		
		output.close();
		sc.close();
	}
}
