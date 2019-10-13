import java.util.Scanner;

public class Problem_8_old
{
	public static int changeAngle(int toChange, int change)
	{
		toChange = toChange + change;
		
		if(toChange >= 360)
		{
			toChange = toChange - 360;
		}
		
		return toChange;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		while(true)
		{
			int length = sc.nextInt();	//The length
			sc.nextLine();
			
			if(length < 2)
			{
				break;
			}
			
			//The bends
			String[] bends = sc.nextLine().split("\\s+");
			
			//Variables to hold the angle
			int xy = 0;
			int xz = 0;
			
			for(int i = 0; i < bends.length; i++)
			{
				if(bends[i].equals("+y"))
				{
					xy = changeAngle(xy, 90);
				}
				else if(bends[i].equals("-y"))
				{
					xy = changeAngle(xy, -90);
				}
				else if(bends[i].equals("+z"))
				{
					xz = changeAngle(xz, 90);
				}
				else if(bends[i].equals("-z"))
				{
					xz = changeAngle(xz, -90);
				}
				//Otherwise do nothing
				
				System.out.println("Iteration: " + i + " xy = " + xy + ", xz = " + xz);
			}
			
			//Get final angle
			
		}
		
		sc.close();
	}
}
