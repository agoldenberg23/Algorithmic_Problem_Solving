import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Problem_B
{
	public static int[] setSide(int[] side, int carNum, int[] cars, int Lweight, int Rweight)
	{
		//System.out.println("For chose=" + side[0] + " car=" + cars[side[0]] + " Lwe=" + Lweight + " Rwe=" + Rweight);
		
		//Chose a position for all of the cars or last car was not placed
		if(side[0] >= carNum)
		{
			//System.out.println("Base case ret");
			return side;
		}
		
		int[] sideL = null;
		if(cars[side[0]] <= Lweight)	//Check if possible to add left
		{
			//System.out.println("Checking left");
			//Set the side with car added to the left
			sideL = side.clone();
			sideL[sideL[0]+1] = -1;
			sideL[0] = side[0] + 1;
			
			//Continue adding if posible
			sideL = setSide(sideL, carNum, cars, Lweight - cars[side[0]], Rweight);
			
			if(sideL[0] >= carNum)
			{
				return sideL;
			}
		}
		
		//System.out.println("For chose=" + side[0] + " car=" + cars[side[0]] + " Lwe=" + Lweight + " Rwe=" + Rweight + " after left");
		
		int[] sideR = null;
		if(cars[side[0]] <= Rweight)	//Check if possible to add right
		{
			//System.out.println("Checking right");
			//Set the side with car added to the right
			sideR = side.clone();
			sideR[sideR[0]+1] = 1;
			sideR[0] = side[0] + 1;
			
			//Continue adding if posible
			sideR = setSide(sideR, carNum, cars, Lweight, Rweight - cars[side[0]]);
			
			if(sideR[0] >= carNum)
			{
				return sideR;
			}
		}
		
		//System.out.println("For chose=" + side[0] + " car=" + cars[side[0]] + " Lwe=" + Lweight + " Rwe=" + Rweight + " after right");
		
		//Check for the answar
		if(sideR == null && sideL == null)
		{
			//System.out.println("Did not change");
			//Did not set any side
			return side;
		}
		else if(sideR == null && sideL[0] > side[0])
		{
			//System.out.println("right invalid, ret left");
			//Right side invalid
			return sideL;
		}
		else if(sideL == null && sideR[0] > side[0])
		{
			//System.out.println("left invalid, re right");
			//Left side invalid
			return sideR;
		}
		
		if(sideL[0] >= sideR[0])
		{
			//System.out.println("left is better, ret");
			//Side left is equal or better
			return sideL;
		}
		else
		{
			//System.out.println("right is better, ret");
			return sideR;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out, "ASCII"), 4096);
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	
		int testNum = Integer.parseInt(sc.readLine());
		
		for(int test = 0; test < testNum; test++)
		{
			//Get the input
			sc.readLine();
			int boatLen = Integer.parseInt(sc.readLine()) * 100;
			
			int[] cars = new int[210];
			int carNum = 0;
			int carLenTotNow = 0;
			
			while(true)
			{
				int next = Integer.parseInt(sc.readLine());
				if(next == 0)
				{
					break;
				}
				
				//Check if it is not possible to add the next car
				carLenTotNow = carLenTotNow + next;
				if(carLenTotNow >= boatLen*2)
				{
					continue;
				}
				
				//Add the car to the input list if possible to add to ferry
				cars[carNum] = next;
				carNum++;
			}
			
			//System.out.println("boatLen=" + boatLen + " carNum=" + carNum);
			/*for(int i = 0; i < carNum; i++)
			{
				System.out.println(cars[i]);
			}*/
			
			
			//Set the sides
			int[] side = new int[carNum+1];
			side = setSide(side, carNum, cars, boatLen, boatLen);
			
			//Output
			if(test != 0)
			{
				output.write("\n");
			}
			
			output.write((side[0]) + "\n");
			for(int i = 1; i <= side[0]; i++)
			{
				if(side[i] == -1)
				{
					output.write("port\n");
				}
				else
				{
					output.write("starboard\n");
				}
			}
		}
		
		sc.close();
		output.close();
	}
}
