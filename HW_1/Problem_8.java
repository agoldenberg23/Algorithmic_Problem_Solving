import java.util.Scanner;

public class Problem_8
{
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
			
			//Variable to hold the angle
			String curent = "+x";
			
			for(int i = 0; i < bends.length; i++)
			{
				if(bends[i].equals("+z"))
				{
					if(curent.equals("+x"))
					{
						curent = "+z";
					}
					else if(curent.equals("-x"))
					{
						curent = "-z";
					}
					else if(curent.equals("+z"))
					{
						curent = "-x";
					}
					else if(curent.equals("-z"))
					{
						curent = "+x";
					}
				}
				else if(bends[i].equals("-z"))
				{
					if(curent.equals("+x"))
					{
						curent = "-z";
					}
					else if(curent.equals("-x"))
					{
						curent = "+z";
					}
					else if(curent.equals("+z"))
					{
						curent = "+x";
					}
					else if(curent.equals("-z"))
					{
						curent = "-x";
					}
				}
				else if(bends[i].equals("+y"))
				{
					if(curent.equals("+x"))
					{
						curent = "+y";
					}
					else if(curent.equals("-x"))
					{
						curent = "-y";
					}
					else if(curent.equals("+y"))
					{
						curent = "-x";
					}
					else if(curent.equals("-y"))
					{
						curent = "+x";
					}
				}
				else if(bends[i].equals("-y"))
				{
					if(curent.equals("+x"))
					{
						curent = "-y";
					}
					else if(curent.equals("-x"))
					{
						curent = "+y";
					}
					else if(curent.equals("+y"))
					{
						curent = "+x";
					}
					else if(curent.equals("-y"))
					{
						curent = "-x";
					}
				}
				//Otherwise do nothing
			}
			
			//Print final
			System.out.println(curent);
		}
		
		sc.close();
	}
}