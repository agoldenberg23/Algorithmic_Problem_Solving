import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;

public class Problem_C
{
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out, "ASCII"), 4096);
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	
		//First fill the shive of primes
		boolean[] primes = new boolean[1000001];
		Arrays.fill(primes, true);
		primes[0] = false;
				
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
		
		//boolean first = false;
		while(true)
		{
			String line = sc.readLine();
			
			if(line == null)
			{
				break;
			}
			
			/*if(first == true)
			{
				output.write("\n");
			}
			
			first = true;*/
			
			String[] splited = line.split(" ");
			int maxPrime = Integer.parseInt(splited[0]);
			int priNum = Integer.parseInt(splited[1]);
			int priWant = priNum * 2;
			int totPrimes = 0;
			LinkedList<Integer> priPrint = new LinkedList<Integer>();
			
			for(int i = 1; i <= maxPrime; i++)
			{
				if(primes[i] == true)
				{
					priPrint.add(i);
					totPrimes++;
				}
			}
			
			//System.out.println("Tot primes = " + totPrimes);
			
			if(totPrimes % 2 != 0)
			{
				priWant = priWant - 1;
			}
			
			//System.out.println("priWant is " + priWant);
			
			//If the amount we want is more then the amount
			if(priWant > totPrimes)
			{
				priWant = totPrimes;
			}
			
			//Remove untill we get what to print
			while(totPrimes > priWant)
			{
				//System.out.println("Removing : " + priPrint.removeFirst());
				//System.out.println("Removing: " + priPrint.removeLast());
				priPrint.removeFirst();
				priPrint.removeLast();
				totPrimes = totPrimes - 2;
			}
			
			//Print the output
			output.write(maxPrime + " " + priNum + ":");
			while(priPrint.isEmpty() == false)
			{
				output.write(" " + priPrint.removeFirst());
			}
			output.write("\n\n");
			
			//output.flush();
		}
		
		sc.close();
		output.close();
	}
}
