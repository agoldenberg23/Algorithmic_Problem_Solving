import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Problem_B
{
	public static class sprink implements Comparable<sprink>
	{
		int pos;
		int rad;
		double right = -1.0;
		double left = -1.0;
		
		public sprink(int pos, int rad, double grassWidth)
		{
			this.pos = pos;
			this.rad = rad;
			
			//Set right and left coverage dependent on width
			if(rad > grassWidth)
			{
				double covLen = Math.sqrt(((double)rad * (double)rad) 
					- (grassWidth * grassWidth));
				
				this.right = (double)pos + covLen;
				this.left = (double)pos - covLen;
			}
			//If rad is smaller, want to remove the sprinkler
		}
		
		public String toString()
		{
			return "pos=" + this.pos + " rad=" + this.rad + " left=" + this.left + " right=" + this.right;
		}
		
		public int compareTo(sprink compare)
		{
			return Double.compare(this.left, compare.left);
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out, "ASCII"), 4096);
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	
		while(true)
		{
			String line = sc.readLine();
			
			if(line == null || line.equals("") || line == null)
			{
				break;
			}
			
			String[] split = line.split(" ");
			int sprNum = Integer.parseInt(split[0]);
			int len = Integer.parseInt(split[1]);
			double width = (double)Integer.parseInt(split[2]) / 2.0;
			
			sprink[] list = new sprink[sprNum];
			
			//Input the sprinklers
			for(int i = 0; i < sprNum; i++)
			{
				split = sc.readLine().split(" ");
				list[i] = new sprink(Integer.parseInt(split[0]), Integer.parseInt(split[1]), width);
			}
			
			Arrays.sort(list);
		
			/*System.out.println("sprNum=" + sprNum + " len=" + len + " width=" + width);
			for(int i = 0; i < sprNum; i++)
			{
				System.out.println(list[i].toString());
			}*/
			
			//Get the list of used sprinklers
			double rightMost = 0.0;
			int sprCount = 0;
			for(int i = 0; i < sprNum; i++)
			{
				//No sprinkler will work
				if(list[i].left > rightMost || i > 10000)
				{
					break;
				}
				
				//Search for the next valid
				for(int c = i+1; c < sprNum && list[c].left <= rightMost; c++)
				{
					if(list[c].right > list[i].right)
					{
						i = c;
					}
				}
				
				sprCount++;
				rightMost = list[i].right;
				
				//Covered the grass
				if(rightMost >= len)
				{
					break;
				}
			}
			
			//Output
			if(rightMost >= len)
			{
				output.write(sprCount + "\n");
			}
			else
			{
				output.write("-1\n");
			}
		}
		
		sc.close();
		output.close();
	}
}
