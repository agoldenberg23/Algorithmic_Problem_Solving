import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Problem_4
{
	public static class car
	{
		int arrLoc;	//Array location
		int timeArive;	//The time of arrival
		int loc = 0;	//Right or left the car arrives at
		int leaveTime = -1;	//The time the car leaves the ferry
		
		public car(int time, String loc, int arrLoc)
		{
			this.arrLoc = arrLoc;
			this.timeArive = time;
			if(loc.equals("left"))
			{
				this.loc = -1;
			}
			else
			{
				this.loc = 1;
			}
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
	
		int testNum = sc.nextInt();
		
		//Run all of the tests
		for(int tests = 0; tests < testNum; tests++)
		{
			if(tests != 0)
			{
				System.out.println();
			}
			
			//Test numbers
			int capacity = sc.nextInt();
			int travelTime = sc.nextInt();
			int carNum = sc.nextInt();
			int ferryLoc = -1;	//Starts left
			
			Queue<car> left = new LinkedList<>();
			Queue<car> right = new LinkedList<>();
			Queue<car> ferry = new LinkedList<>();
			car[] carList = new car[carNum];
			
			//Take in the input
			for(int i = 0; i < carNum; i++)
			{
				carList[i] = new car(sc.nextInt(), sc.next(), i);
				//System.out.println("For i = " + i + " time: " + carList[i].timeArive + " loc: " + carList[i].loc);
			}
			
			//Run the simulation
			int curCar = 0;
			int time = 0;
			while(curCar < carNum || !left.isEmpty() || !right.isEmpty())
			{
				//Check if any new cars should be added
				while(curCar < carNum && carList[curCar].timeArive <= time)
				{
					if(carList[curCar].loc == -1)
					{
						left.add(carList[curCar]);
					}
					else
					{
						right.add(carList[curCar]);
					}
					curCar++;
				}
				
				//Add cars to the ferry
				if(ferryLoc == -1)	//Ferry at the left
				{
					for(int i = 0; i < capacity; i++)
					{
						if(left.isEmpty())
						{
							break;
						}
						
						ferry.add(left.poll());
					}
				}
				else	//At the right
				{
					for(int i = 0; i < capacity; i++)
					{
						if(right.isEmpty())
						{
							break;
						}
						
						ferry.add(right.poll());
					}
				}
				
				//Move the ferry to the other side and set cars as done
				//There are no current cars, just increment time
				if(ferry.isEmpty() && left.isEmpty() && right.isEmpty())
				{
					time = carList[curCar].timeArive;
				}
				
				//Cars so we move
				else
				{
					time = time + travelTime;
					
					if(ferryLoc == -1)
					{
						ferryLoc = 1;
					}
					else
					{
						ferryLoc = -1;
					}
					
					//Cars on ferry
					while(ferry.isEmpty() == false)
					{
						car a = ferry.poll();
						a.leaveTime = time;
					}	
					//No cars on ferry then do nothing
				}
			}
			
			//Print out the times
			for(int i = 0; i < carNum; i++)
			{
				System.out.println(carList[i].leaveTime);
			}
			
		}
		
		sc.close();
	}
}
