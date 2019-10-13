import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Problem_D
{
	public static void main(String[] args) throws IOException
	{
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out, "ASCII"), 4096);
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	
		while(true)
		{
			//Get the input
			String[] split = sc.readLine().split(" ");
			
			int[] boxes = new int[7];
			for(int i = 1; i <= 6; i++)
			{
				boxes[i] = Integer.parseInt(split[i-1]);
			}
			
			if(boxes[1] == 0 && boxes[2] == 0 && boxes[3] == 0 
					&& boxes[4] == 0 && boxes[5] == 0 && boxes[6] == 0)
			{
				break;
			}
			
			//Solve the problem
			//Boxes of size >=4 need a new box
			int ans = boxes[4] + boxes[5] + boxes[6];
			
			//Each size 5 box can have at most 11 size 1 boxes and non others
			boxes[1] = boxes[1] - (boxes[5] * 11);
			
			//Each size 4 box can have at most 5 size 2 boxes
			boxes[2] = boxes[2] - (boxes[4] * 5);
			//Over used size 2 boxes, fill with size 1 boxes
			if(boxes[2] < 0)
			{
				boxes[1] = boxes[1] + (boxes[2] * 4);
			}
			
			//Handle boxes filled with size 3 boxes
			ans = ans + (boxes[3] / 4) + 1;
			//Handle partial filled box
			if(boxes[3] % 4 == 0)			//Leftover box is empty
			{
				ans--;
			}
			else if(boxes[3] % 4 == 1)		//Leftover has 1 of size 3
			{
				//Fill with size 2 boxes
				if(boxes[2] > 0)
				{
					boxes[2] = boxes[2] - 5;
					
					//Fill leftover size 2 with size 1
					if(boxes[2] < 0)
					{
						boxes[1] = boxes[1] + (boxes[2] * 4);
						//boxes[2] = 0;
					}
					
					//Fill in size 1
					boxes[1] = boxes[1] - 7;
				}
				else	//Fill only with size 1
				{
					boxes[1] = boxes[1] - 27;
				}
			}
			else if(boxes[3] % 4 == 2)		//Leftover has 2 of size 3
			{
				//Fill with size 2 boxes
				if(boxes[2] > 0)
				{
					boxes[2] = boxes[2] - 3;
					
					//Fill leftover size 2 with size 1
					if(boxes[2] < 0)
					{
						boxes[1] = boxes[1] + (boxes[2] * 4);
					}
					
					//Fill in size 1
					boxes[1] = boxes[1] - 6;
				}
				else	//Fill with only size 1
				{
					boxes[1] = boxes[1] - 18;
				}
			}
			else if(boxes[3] % 4 == 3)		//Leftover has 3 of size 3
			{
				//Fill with size 2 boxes
				if(boxes[2] > 0)
				{
					boxes[2] = boxes[2] - 1;
					boxes[1] = boxes[1] - 5;
				}
				else	//Fill with only size 1
				{
					boxes[1] = boxes[1] - 9;
				}
			}
			
			//Handle cases of size 2 leftover
			if(boxes[2] > 0)
			{
				ans = ans + (boxes[2] / 9);
				boxes[2] = boxes[2] % 9;
				
				//Case of leftover
				if(boxes[2] > 0)
				{
					ans++;
					boxes[1] = boxes[1] - ((9 - boxes[2]) * 4);
				}
			}
			
			//Handle only size 1 left
			if(boxes[1] > 0)
			{
				ans = ans + (boxes[1] / 36) + 1;
				if(boxes[1] % 36 == 0)
				{
					ans--;
				}
			}
			
			//Output
			output.write(ans + "\n");
		}
		
		sc.close();
		output.close();
	}
}
