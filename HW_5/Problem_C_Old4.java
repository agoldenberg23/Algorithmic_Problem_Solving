import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Problem_C_Old4
{
	public static boolean compare(String a, String b)
	{
		//System.out.println("Comparing " + a + " and " + b);
		int aLen = 0;
		int bLen = 0;
		
		while(aLen < a.length() && bLen < b.length())
		{
			//System.out.println("Char a = " + a.charAt(aLen) + " Char b = " + b.charAt(bLen));
			if(a.charAt(aLen) > b.charAt(bLen))
			{
				//System.out.println(a + " is larger");
				return true;
			}
			else if(b.charAt(bLen) > a.charAt(aLen))
			{
				//System.out.println(b + " is larger");
				return false;
			}
			
			aLen++;
			bLen++;
		}
		
		return false;
	}
	
	public static String[] sort(String[] list)
	{
		int n = list.length;
		for(int i = 0; i < n-1; i++)
		{
			for(int j = 0; j < n-i-1; j++)
			{
				if(compare(list[j+1], list[j]))
				{
					String temp = list[j];
					list[j] = list[j+1];
					list[j+1] = temp;
				}
			}
		}
		
		return list;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out, "ASCII"), 4096);
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	
		while(true)
		{
			int num = Integer.parseInt(sc.readLine());
			
			if(num == 0)
			{
				break;
			}
			
			String[] list = sc.readLine().split(" ");
			
			list = sort(list);
			
			//System.out.println(list.toString());
			
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < list.length; i++)
			{
				sb.append(list[i]);
			}
			
			System.out.println(sb.toString());
		}
		sc.close();
		output.close();
	}
}
