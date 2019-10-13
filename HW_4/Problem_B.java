import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Problem_B
{
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out, "ASCII"), 4096);
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	
		//First fill the shive of primes
		boolean[] primes = new boolean[1000001];
		Arrays.fill(primes, true);
		primes[0] = false;
		primes[1] = false;
		
		for(int i = 2; (i * i) < primes.length; i++)
		{
			if(primes[i] == true)
			{
				for(int j = i+i; j < primes.length; j = j + i)
				{
					primes[j] = false;
				}
			}
		}
		
		/*for(int i = 0; i < primes.length; i++)
		{
			if(primes[i] == true)
			{
				System.out.println(i);
			}
		}*/
		
		/*primes[2] = true;
		
		//Set unknown to true
		for(int i = 3; i <= 1000000; i = i+2)
		{
			primes[i] = true;
		}
		
		//Remove non primes
		for(int i = 3; i < 1000; i=i+2)
		{
			if(primes[i] == true)
			{
				for(int j = i + i; j <= 1000000; j = j + i)
				{
					primes[j] = false;
				}
			}
		}*/
			
		//Now run the tests
		int testNum = Integer.parseInt(sc.readLine());
		
		for(int tests = 0; tests < testNum; tests++)
		{
			String[] splited = sc.readLine().split(" ");
			int lower = Integer.parseInt(splited[0]);
			int higher = Integer.parseInt(splited[1]);
			
			/*for(int i = lower; i <= higher; i++)
			{
				if(primes[i] == true)
				{
					System.out.print(i + ", ");
				}
			}
			System.out.println();*/
			
			//An array of the distances
			int[] dist = new int[150];
			
			//Get the first prime in the range
			int lastPrime = -1;
			for(int i = lower; i <= higher; i++)
			{
				if(primes[i] == true)
				{
					lastPrime = i;
					break;
				}
			}
			
			//System.out.println("First prime is " + lastPrime);
			
			//No primes in the range
			if(lastPrime == -1)
			{
				output.write("No jumping champion" + "\n");
				continue;
			}
			
			//Find the distances
			for(int i = lastPrime+1; i <= higher; i++)
			{
				if(primes[i] == true)
				{
					dist[i - lastPrime]++;
					//System.out.println("Count for len " + (i - lastPrime) + " is now " + dist[i - lastPrime]);
					lastPrime = i;
				}
			}
			
			//Find the champ
			int champ = 0;
			boolean nonEqual = false;
			for(int i = 1; i < dist.length; i++)
			{
				if(dist[i] > dist[champ])
				{
					//System.out.println("Champ is now " + i + " with len " + dist[i]);
					champ = i;
					nonEqual = false;
				}
				else if(dist[i] == dist[champ])
				{
					//System.out.println("Found an equal at " + i + " with len " + dist[i]);
					nonEqual = true;
				}
			}
			
			//Print the results
			if(nonEqual == true)
			{
				output.write("No jumping champion" + "\n");
			}
			else
			{
				output.write("The jumping champion is " + champ + "\n");
			}
		}
		
		sc.close();
		output.close();
	}
}
